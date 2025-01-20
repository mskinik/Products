package com.mskinik.products.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mskinik.products.data.TestGetProductUseCase
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.usecase.GetProductUseCase
import com.mskinik.products.ui.fragment.home.HomeViewEvent
import com.mskinik.products.ui.fragment.home.HomeViewModel
import com.mskinik.products.ui.fragment.home.Sorting
import com.mskinik.products.util.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    private val testGetProductUseCase = TestGetProductUseCase()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    lateinit var getProductUseCase: GetProductUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { getProductUseCase() } returns flowOf(Resource.Success(testGetProductUseCase.productList))
        homeViewModel = HomeViewModel(getProductUseCase)
    }

    @Test
    fun `productDetailList size should be 1 when viewModel initialized`() = runTest {
        advanceUntilIdle()
        assertEquals(1, homeViewModel.getCurrentState().productList?.size)
    }

    @Test
    fun `sorting should be ascending when OnSortClicked trigger for the first time`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnSortClicked)
        advanceUntilIdle()
        assertEquals(Sorting.ASCENDING, homeViewModel.getCurrentState().sorting)
    }

    @Test
    fun `sorting should be descending when OnSortClicked trigger for the second time`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnSortClicked)
        advanceUntilIdle()
        homeViewModel.setEvent(HomeViewEvent.OnSortClicked)
        advanceUntilIdle()
        assertEquals(Sorting.DESCENDING, homeViewModel.getCurrentState().sorting)
    }

    @Test
    fun `minWeight should be 1 when pass the 1 value with OnWeightFilterChanged`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnWeightFilterChanged(1))
        advanceUntilIdle()
        assertEquals(1, homeViewModel.getCurrentState().minWeight)
    }

    @Test
    fun `minWeight should be null when pass the 0 value with OnWeightFilterChanged`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnWeightFilterChanged(0))
        advanceUntilIdle()
        assertEquals(null, homeViewModel.getCurrentState().minWeight)
    }

    @Test
    fun `minStock should be 1 when pass the 1 value with OnWeightFilterChanged`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnStockFilterChanged(1))
        advanceUntilIdle()
        assertEquals(1, homeViewModel.getCurrentState().minStock)
    }

    @Test
    fun `searchText should be the text value when pass the text value with OnWeightFilterChanged`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnSearchTextChanged("text"))
        advanceUntilIdle()
        assertEquals("text", homeViewModel.getCurrentState().searchText)
    }

    @Test
    fun `minStock should be null when pass the 0 value with OnWeightFilterChanged`() = runTest {
        homeViewModel.setEvent(HomeViewEvent.OnStockFilterChanged(0))
        advanceUntilIdle()
        assertEquals(null, homeViewModel.getCurrentState().minStock)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}