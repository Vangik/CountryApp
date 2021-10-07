package com.example.countryapp.di.modules

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.NormalizedCache
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class GraphQlModule {

    @Provides
    fun provideGraphQlRepository(): ApolloClient {
        val cacheFactory = LruNormalizedCacheFactory(
            EvictionPolicy.builder().maxSizeBytes(10 * 1024 * 1024).build()
        )
        val apolloClient =
            ApolloClient.builder().serverUrl(provideUrl()).normalizedCache(cacheFactory)
                .build()
        val dump = apolloClient.apolloStore.normalizedCache().dump();
        Log.d("apolloLog", NormalizedCache.prettifyDump(dump))
        return apolloClient
    }

    @Provides
    fun provideUrl() = CountryRepositoryImpl.URL

}

