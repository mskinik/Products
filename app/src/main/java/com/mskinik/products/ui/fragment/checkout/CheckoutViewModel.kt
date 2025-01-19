package com.mskinik.products.ui.fragment.checkout

import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(): BaseViewModel<CheckoutViewEvent,CheckoutViewState,CheckoutViewEffect>() {
    override fun setInitialState(): CheckoutViewState  = CheckoutViewState()

    override fun handleEvents(event: CheckoutViewEvent) {
        when(event){
            else -> {
            }
        }
    }
}