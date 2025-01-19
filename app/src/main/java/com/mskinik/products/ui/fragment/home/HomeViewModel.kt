package com.mskinik.products.ui.fragment.home

import androidx.lifecycle.viewModelScope
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.usecase.GetProductUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) :
    BaseViewModel<HomeViewEvent, HomeViewState, HomeViewEffect>() {
    override fun setInitialState(): HomeViewState = HomeViewState()

    override fun handleEvents(event: HomeViewEvent) {
        when (event) {
            else -> {}
        }
    }

    init {
        viewModelScope.launch {
            getProductUseCase().collect { products ->
                when (products) {
                    is Resource.Success -> {
                        setState {
                            copy(
                                productList = products.data?.toImmutableList()
                            )
                        }
                    }
                    is Resource.Fail -> {
                        //
                    }
                    is Resource.Error -> {
                        //
                    }
                }
            }
        }
    }
}