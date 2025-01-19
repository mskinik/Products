package com.mskinik.products.ui.fragment.detail

import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.usecase.FavoriteUseCase
import com.mskinik.products.domain.usecase.GetProductDetailUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val favoriteUseCase: FavoriteUseCase
) :
    BaseViewModel<ProductDetailEvent, ProductDetailViewState, ProductDetailEffect>() {
    override fun setInitialState(): ProductDetailViewState = ProductDetailViewState()
    private val compositeDisposable = CompositeDisposable()

    override fun handleEvents(event: ProductDetailEvent) {
        when (event) {
            else -> {
            }
        }
    }

    fun fetchProductDetail(id: Int) {
        val disposable = getProductDetailUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState { copy(product = resource.data) }
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

    fun getFavorites() {

    }

    fun setFavorite() {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}