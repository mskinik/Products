package com.mskinik.products.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.ui.fragment.checkout.CheckoutViewEvent
import com.mskinik.products.ui.fragment.checkout.CheckoutViewModel
import com.mskinik.products.util.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CheckoutViewModelTest {
    private lateinit var checkoutViewModel: CheckoutViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    lateinit var checkoutUseCase: CheckoutUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        checkoutViewModel = CheckoutViewModel(checkoutUseCase)
    }

    @Test
    fun `isButtonEnable should be true when all item size greater then 2`() = runTest {
        checkoutViewModel.setEvent(CheckoutViewEvent.OnMailTextChanged("mail"))
        checkoutViewModel.setEvent(CheckoutViewEvent.OnNameTextChanged("name"))
        checkoutViewModel.setEvent(CheckoutViewEvent.OnPhoneTextChanged("phone"))
        advanceUntilIdle()
        assertEquals(true, checkoutViewModel.getCurrentState().isButtonEnable)
    }

    @Test
    fun `isButtonEnable should be false when mail is blank`() = runTest {
        checkoutViewModel.setEvent(CheckoutViewEvent.OnMailTextChanged(""))
        checkoutViewModel.setEvent(CheckoutViewEvent.OnNameTextChanged("name"))
        checkoutViewModel.setEvent(CheckoutViewEvent.OnPhoneTextChanged("phone"))
        advanceUntilIdle()
        assertEquals(false, checkoutViewModel.getCurrentState().isButtonEnable)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}