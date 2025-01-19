package com.mskinik.products.ui.fragment.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.domain.usecase.CheckoutUseCase
import com.mskinik.products.domain.usecase.FavoriteUseCase
import com.mskinik.products.domain.usecase.GetProductDetailUseCase
import com.mskinik.products.ui.base.BaseViewModel
import com.mskinik.util.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel<ProductDetailEvent, ProductDetailViewState, ProductDetailEffect>() {
    private val productId: String? = savedStateHandle["id"]
    override fun setInitialState(): ProductDetailViewState = ProductDetailViewState()
    private val compositeDisposable = CompositeDisposable()

    override fun handleEvents(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.OnFavoriteClick -> {
                updateFavorite()
            }

            is ProductDetailEvent.DecreaseQuantity -> {
                checkQuantity()
            }

            is ProductDetailEvent.IncreaseQuantity -> {
                increaseQuantity()
            }

            is ProductDetailEvent.AddToCart -> {
                addToCart()
            }
        }
    }

    private fun updateFavorite() {
        if (state.value.isFavorite == true) {
            deleteFavorite()
        } else {
            setFavorite()
        }
    }

    private fun checkQuantity() {
        if (state.value.quantity == 1) {
            deleteCheckout()
        } else {
            decreaseQuantity()
        }
    }

    init {
        productId?.let {
            checkFavorite(it)
            getCheckoutById(it)
            fetchProductDetail(it.toInt())
        }

    }

    private fun checkFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.isFavorite(id).collect {
                setState {
                    copy(isFavorite = it)
                }
            }

        }
    }

    private fun fetchProductDetail(id: Int) {
        val disposable = getProductDetailUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState {
                            with(resource) {
                                copy(
                                    id = data?.id.orZero().toString(),
                                    title = data?.title,
                                    desc = data?.description,
                                    price = data?.price,
                                    image = data?.previewImage,
                                    stock = data?.stock,
                                    brand = data?.brand,
                                    category = data?.category,
                                    discount = data?.discountPercentage
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                    }

                    is Resource.Fail -> {
                    }
                }
            }, { throwable ->
            })
        compositeDisposable.add(disposable)
    }

    private fun increaseQuantity() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.id?.let { id ->
                checkoutUseCase.increaseQuantity(id)
            }
        }
    }

    private fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.addCheckout(
                ProductDetail(
                    id = state.value.id,
                    title = state.value.title,
                    desc = state.value.desc,
                    price = state.value.price,
                    image = state.value.image,
                    stock = state.value.stock,
                    quantity = 1
                )
            )
        }
    }

    private fun decreaseQuantity() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.id?.let { id ->
                checkoutUseCase.decreaseQuantity(id)
            }
        }
    }

    private fun deleteCheckout() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.id?.let { id ->
                checkoutUseCase.deleteCheckout(id)
            }
        }
    }

    private fun deleteFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.id?.let { id ->
                favoriteUseCase.deleteFavorite(id)
            }
        }
    }

    private fun setFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.id?.let { id ->
                favoriteUseCase.addFavorite(
                    FavoriteEntity(
                        id = id,
                        title = state.value.title,
                        desc = state.value.desc,
                        price = state.value.price,
                        image = state.value.image,
                        stock = state.value.stock
                    )
                )
            }
        }
    }

    private fun getCheckoutById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            checkoutUseCase.getCheckoutById(id).collect {
                setState {
                    copy(quantity = it?.quantity.orZero())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}