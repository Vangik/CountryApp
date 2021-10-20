package com.example.countryapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.resources.FakeResp
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.observers.TestObserver
import kotlinx.coroutines.runBlocking
import okio.ByteString.Companion.encodeUtf8
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
internal class ChildViewModelTest {
    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: CountryRepository
    private lateinit var childViewModel: ChildViewModel
    private lateinit var testObserver: TestObserver<Response<CountryByIdQuery.Data>>
    private val responseCountry: Response<CountryByIdQuery.Data> =
        CountryByIdQuery("AD").parse(FakeResp.COUNTRY_LIST.encodeUtf8())

    @Mock
    private lateinit var countryObserver: Observer<ViewState<CountryModel>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockRepository = mock(CountryRepository::class.java)
        childViewModel = ChildViewModel(mockRepository)
    }

    @Test
    fun `loading state`() {
        runBlocking {
            childViewModel.getCountry().observeForever(countryObserver)
            childViewModel.getCountry()
            verify(countryObserver).onChanged(ViewState.Loading())
        }
    }

    @Test
    fun `success response form graphql`() {
        val countryResponse = responseCountry.data?.country?.toCountryModel()
        childViewModel.setMutableLiveData(ViewState.Success(countryResponse))
        assertNotNull(childViewModel.getCountry())
        Assert.assertEquals(
            childViewModel.getCountry().value?.value,
            ViewState.Success(countryResponse).value
        )
    }

    @Test
    fun `error response form graphql`() {
        runBlocking {
            childViewModel.getCountry().observeForever(countryObserver)
            `when`(mockRepository.getCountryList()).thenAnswer {
                ViewState.Error("error", countryObserver)
            }
            childViewModel.getCountry()
            assertNotNull(childViewModel.getCountry())
            Assert.assertEquals(ViewState.Error("error", countryObserver), childViewModel.getCountry().value?.value)
        }
    }
    @Test
    fun getCountryDetails() {
        testObserver = mockRepository.getCountryById(FakeResp.COUNTRY_ID).test()
        testObserver
            .assertNoErrors()
            .assertValue(responseCountry)
        verify(mockRepository).getCountryById(FakeResp.COUNTRY_ID)
    }
}