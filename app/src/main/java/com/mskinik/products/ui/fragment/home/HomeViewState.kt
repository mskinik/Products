package com.mskinik.products.ui.fragment.home

import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeViewState(
    override val loading: Boolean = false,
    val productList: ImmutableList<Product>? = persistentListOf(),
    val filteredProductList: ImmutableList<Product>? = persistentListOf(),
    val searchText: String? = null,
    val sorting: Sorting?  = null,
    val minStock: Int? = null,
    val minWeight: Int? = null
) : State

enum class Sorting {
    ASCENDING,
    DESCENDING
}

sealed interface HomeViewEvent : Event {
    data class OnSearchTextChanged(val search: String) : HomeViewEvent
    data object OnSortClicked : HomeViewEvent
    data class OnStockFilterChanged(val minStock: Int) : HomeViewEvent
    data class OnWeightFilterChanged(val minWeight: Int) : HomeViewEvent
}

sealed interface HomeViewEffect : Effect {
}