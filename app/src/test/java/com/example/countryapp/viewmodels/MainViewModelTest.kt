package com.example.countryapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryListQuery
import com.example.countryapp.resources.FakeResp
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import kotlinx.coroutines.runBlocking
import okio.ByteString.Companion.encodeUtf8
import org.junit.Assert.assertEquals
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
internal class MainViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: CountryRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var testObserver: TestObserver<Response<CountryListQuery.Data>>
    private val responseCountryList: Response<CountryListQuery.Data> =
        CountryListQuery().parse(FakeResp.COUNTRY_LIST.encodeUtf8())

    @Mock
    private lateinit var countryListObserver: Observer<ViewState<List<CountryModel>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockRepository = mock(CountryRepository::class.java)
        mainViewModel = MainViewModel(mockRepository)
    }

    @Test
    fun `loading state`() {
        runBlocking {
            mainViewModel.getCountryList().observeForever(countryListObserver)
            mainViewModel.getCountryList()
            verify(countryListObserver).onChanged(ViewState.Loading())
        }
    }

    @Test
    fun `success response form graphql`() {
        val listResponse = responseCountryList.data?.countries?.map { it.toCountryModel() }
        mainViewModel.setMutableLiveData(ViewState.Success(listResponse))
        assertNotNull(mainViewModel.getCountryList())
        assertEquals(
            mainViewModel.getCountryList().value?.value,
            ViewState.Success(listResponse).value
        )
    }

    @Test
    fun `error response form graphql`() {
        runBlocking {
            mainViewModel.getCountryList().observeForever(countryListObserver)
            `when`(mockRepository.getCountryList()).thenAnswer {
                ViewState.Error("error", countryListObserver)
            }
            mainViewModel.getCountryList()
            assertNotNull(mainViewModel.getCountryList())
            assertEquals(ViewState.Error("error", countryListObserver), mainViewModel.getCountryList().value?.value)
        }
    }

    @Test
    fun `response test`() {
        `when`(mockRepository.getCountryList()).thenReturn(
            Observable.just(responseCountryList)
        )
        testObserver = mockRepository.getCountryList().test()
        testObserver.assertNoErrors().assertValue(responseCountryList)
        verify(mockRepository).getCountryList()
    }
}