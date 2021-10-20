package com.example.countryapp.repository.impl

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.repository.CountryRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CountryRepositoryImplTest {

    private lateinit var apolloClient: ApolloClient
    private lateinit var countryRepositoryTest: CountryRepository

    @Before
    fun setUp() {
        apolloClient =
            ApolloClient.builder().serverUrl("https://countries.trevorblades.com").build()
        countryRepositoryTest = CountryRepositoryImpl(apolloClient)
    }


    @Test
    fun `test response from graphql`() {
        val listSize = 134
    }
}