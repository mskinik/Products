package com.mskinik.products.ui.fragment.root

import androidx.lifecycle.viewModelScope
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.ui.base.BaseViewModel
import com.mskinik.util.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase) :
    BaseViewModel<RootEvent, RootViewState, RootEffect>() {
    override fun setInitialState(): RootViewState = RootViewState()

    override fun handleEvents(event: RootEvent) {
        when (event) {
            RootEvent.NavigateToCheckout -> {
                setEffect { RootEffect.NavigateToCheckout }
            }
            is RootEvent.NavigateToProductDetail -> {
                setEffect { RootEffect.NavigateToProductDetail(event.id) }
            }
        }
    }

    init {
        getTotalQuantity()
    }

    private fun getTotalQuantity() {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.getTotalQuantity().collect {
                setState { copy(totalQuantity = it.orZero()) }
            }
        }
    }
}