package com.mskinik.products.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mskinik.products.data.TestCheckoutUseCase
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.ui.fragment.basket.BasketViewModel
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
class BasketViewModelTest {
    private lateinit var basketViewModel: BasketViewModel

    private val testCheckoutUseCase = TestCheckoutUseCase()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    lateinit var checkoutUseCase: CheckoutUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { checkoutUseCase.getCheckouts() } returns flowOf(testCheckoutUseCase.testProductDetailList)
        basketViewModel = BasketViewModel(checkoutUseCase)
    }

    @Test
    fun `productDetailList size should be 3 when viewModel initialized`() = runTest {
        advanceUntilIdle()
        assertEquals(3, basketViewModel.getCurrentState().productDetailList?.size)
    }

    @Test
    fun `totalPrice should be 14 when viewModel initialized`() = runTest {
        advanceUntilIdle()
        assertEquals(14.0, basketViewModel.getCurrentState().totalPrice)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}