package com.example.countryapp.di.modules

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ApolloLogger
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.NormalizedCache
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.example.countryapp.network.DbImpl.CountryDbImpl
import dagger.Module
import dagger.Provides
import java.util.logging.Logger


@Module
class GraphQlModule {

    @Provides
    fun provideGraphQlRepository(): ApolloClient {
        val cacheFactory = LruNormalizedCacheFactory(
            EvictionPolicy.builder().maxSizeBytes(10 * 1024 * 1024).build()
        )

        val resolver: CacheKeyResolver = object : CacheKeyResolver() {
            override fun fromFieldRecordSet(
                field: ResponseField,
                recordSet: Map<String, Any>
            ): CacheKey {
                // Retrieve the id from the object itself
                return CacheKey.from(recordSet["code"] as String)
            }

            override fun fromFieldArguments(
                field: ResponseField,
                variables: Operation.Variables
            ): CacheKey {
                // Retrieve the id from the field arguments.
                // In the example, this allows to know that `author(id: "author1")` will retrieve `author1`
                // That sounds straightforward but without this, the cache would have no way of finding the id before executing the request on the
                // network which is what we want to avoid
                return CacheKey.from(field.resolveArgument("code", variables) as String)
            }
        }


        val apolloClient =
            ApolloClient.builder().serverUrl(provideUrl()).normalizedCache(cacheFactory)
                .build()

        val dump = apolloClient.getApolloStore().normalizedCache().dump();
        NormalizedCache.prettifyDump(dump)
        Log.d("apolloLog", cacheFactory.toString())
        return apolloClient

    }

    @Provides
    fun provideUrl() = CountryDbImpl.URL


    fun fromFieldRecordSet(field: ResponseField, recordSet: Map<String, Any>): CacheKey {
        val codeProperty = recordSet["code"] as String
        val typePrefix = recordSet["__typename"] as String
        return CacheKey.from("$typePrefix.$codeProperty")
    }
}

