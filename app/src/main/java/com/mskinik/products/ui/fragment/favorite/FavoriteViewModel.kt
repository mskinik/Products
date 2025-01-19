package com.mskinik.products.ui.fragment.favorite

import androidx.lifecycle.viewModelScope
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.domain.usecase.FavoriteUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val checkoutUseCase: CheckoutUseCase
) :
    BaseViewModel<FavoriteViewEvent, FavoriteViewState, FavoriteViewEffect>() {
    override fun setInitialState(): FavoriteViewState = FavoriteViewState()

    override fun handleEvents(event: FavoriteViewEvent) {
        when (event) {
            is FavoriteViewEvent.DeleteFavorite -> {
                deleteFavorite(event.id)
            }

            is FavoriteViewEvent.DecreaseQuantity -> {
                checkQuantity(event.productDetail)
            }

            is FavoriteViewEvent.IncreaseQuantity -> {
                increaseQuantity(event.productDetail)
            }

            is FavoriteViewEvent.AddToCart -> {
                val productDetail = event.productDetail.copy(quantity = 1)
                addToCart(productDetail)
            }
        }
    }

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.getMyProducts().collect {
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

    private fun deleteFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.deleteFavorite(id)
        }
    }
}