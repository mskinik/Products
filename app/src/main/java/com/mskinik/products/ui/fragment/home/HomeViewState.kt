package com.mskinik.products.ui.fragment.home

import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeViewState(
    override val loading: Boolean = false,
    val productList: ImmutableList<Product>? = persistentListOf()
) : State

sealed interface HomeViewEvent : Event {

}

sealed interface HomeViewEffect : Effect {
}