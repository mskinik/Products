package com.mskinik.products.ui.fragment.home

import androidx.lifecycle.viewModelScope
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.usecase.GetProductUseCase
import com.mskinik.products.ui.base.BaseViewModel
import com.mskinik.util.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) :
    BaseViewModel<HomeViewEvent, HomeViewState, HomeViewEffect>() {
    override fun setInitialState(): HomeViewState = HomeViewState()

    override fun handleEvents(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnSearchTextChanged -> {
                setState { copy(searchText = event.search) }
                searchProducts()
            }

            is HomeViewEvent.OnSortClicked -> {
                handleSorting()
                searchProducts()
            }

            is HomeViewEvent.OnStockFilterChanged -> {
                setState {
                    copy(minStock = if (event.minStock == 0) null else event.minStock)
                }
                searchProducts()
            }

            is HomeViewEvent.OnWeightFilterChanged -> {
                setState {
                    copy(minWeight = if (event.minWeight == 0) null else event.minWeight)
                }
                searchProducts()
            }
        }
    }

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductUseCase().collect { products ->
                when (products) {
                    is Resource.Success -> {
                        setState {
                            copy(
                                productList = products.data?.toImmutableList(),
                                filteredProductList = products.data?.toImmutableList()
                            )
                        }
                    }

                    is Resource.Fail -> {
                        // do not nothing for now
                    }

                    is Resource.Error -> {
                        // do not nothing for now
                    }
                }
            }
        }
    }

    private fun searchProducts() {
        viewModelScope.launch {
            val filteredList = getCurrentState().productList?.filter { product ->
                product.title?.contains(getCurrentState().searchText.orEmpty(), ignoreCase = true) == true
            }?.toImmutableList()
            if (getCurrentState().sorting != null) {
                if (getCurrentState().sorting == Sorting.ASCENDING) {
                    val sortedList = filteredList?.sortedBy { it.price }?.toImmutableList()
                    setState {
                        copy(filteredProductList = sortedList?.filter { it.weight.orZero() > getCurrentState().minWeight.orZero() && it.stock.orZero() > getCurrentState().minStock.orZero() }
                            ?.toPersistentList())
                    }
                } else {
                    val sortedList = filteredList?.sortedByDescending { it.price }
                    setState {
                        copy(filteredProductList = sortedList?.filter { it.weight.orZero() > getCurrentState().minWeight.orZero() && it.stock.orZero() > getCurrentState().minStock.orZero() }
                            ?.toImmutableList())
                    }
                }
            } else {
                setState {
                    val sortedList =
                        filteredList?.filter { it.weight.orZero() > getCurrentState().minWeight.orZero() && it.stock.orZero() > getCurrentState().minStock.orZero() }
                    copy(filteredProductList = sortedList?.toPersistentList())
                }
            }

        }
    }

    private fun handleSorting() {
        if (getCurrentState().sorting == null) {
            setState {
                copy(sorting = Sorting.ASCENDING)
            }
        } else {
            if (getCurrentState().sorting == Sorting.ASCENDING) {
                setState {
                    copy(sorting = Sorting.DESCENDING)
                }
            } else {
                setState {
                    copy(sorting = Sorting.ASCENDING)
                }
            }
        }
    }
}