package com.mskinik.products.ui.fragment.basket

import androidx.lifecycle.viewModelScope
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val checkoutUseCase: CheckoutUseCase
) : BaseViewModel<BasketEvent, BasketViewState, BasketEffect>() {
    override fun setInitialState(): BasketViewState = BasketViewState()

    override fun handleEvents(event: BasketEvent) {
        when (event) {
            is BasketEvent.NavigateToCheckout -> {
                setEffect { BasketEffect.NavigateToCheckout }
            }

            is BasketEvent.DeleteProductDetail -> {
                deleteCheckout(event.productDetail)
            }

            is BasketEvent.DecreaseQuantity -> {
                checkQuantity(event.productDetail)
            }

            is BasketEvent.IncreaseQuantity -> {
                increaseQuantity(event.productDetail)
            }
        }
    }

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.getCheckouts().collect {
                setState { copy(productDetailList = it.toImmutableList()) }
            }
        }
    }

    private fun checkQuantity(productDetail: ProductDetail) {
        if (productDetail.quantity == 1) {
            deleteCheckout(productDetail)
        } else {
            decreaseQuantity(productDetail)
        }
    }

    private fun increaseQuantity(productDetail: ProductDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            productDetail.id?.let { id ->
                checkoutUseCase.increaseQuantity(id)
            }
        }
    }

    private fun addToCart(productDetail: ProductDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.addCheckout(productDetail)
        }
    }

    private fun decreaseQuantity(productDetail: ProductDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            productDetail.id?.let { id ->
                checkoutUseCase.decreaseQuantity(id)
            }
        }
    }

    private fun deleteCheckout(productDetail: ProductDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            productDetail.id?.let { id ->
                checkoutUseCase.deleteCheckout(id)
            }
        }
    }

}