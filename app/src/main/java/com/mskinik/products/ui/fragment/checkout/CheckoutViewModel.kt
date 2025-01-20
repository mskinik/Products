package com.mskinik.products.ui.fragment.checkout

import androidx.lifecycle.viewModelScope
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase) :
    BaseViewModel<CheckoutViewEvent, CheckoutViewState, CheckoutViewEffect>() {
    override fun setInitialState(): CheckoutViewState = CheckoutViewState()

    override fun handleEvents(event: CheckoutViewEvent) {
        when (event) {
            CheckoutViewEvent.OnCheckoutClicked -> {
                cleanCheckout()
            }

            is CheckoutViewEvent.OnPhoneTextChanged -> {
                setState {
                    copy(phoneText = event.phone)
                }
            }

            is CheckoutViewEvent.OnMailTextChanged -> {
                setState {
                    copy(mailText = event.mail)
                }
            }

            is CheckoutViewEvent.OnNameTextChanged -> {
                setState {
                    copy(nameText = event.name)
                }
            }

            is  CheckoutViewEvent.OnBackClicked -> {
                setEffect { CheckoutViewEffect.Back }
            }
        }
    }

    private fun cleanCheckout() {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.deleteAllCheckouts().collect {
                setEffect { CheckoutViewEffect.NavigateToHome }
            }
        }
    }
}