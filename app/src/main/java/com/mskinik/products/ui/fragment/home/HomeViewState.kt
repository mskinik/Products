package com.mskinik.products.ui.fragment.home

import com.mskinik.products.data.model.local.FavoriteEntity
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
    data class SetFavorite(val favoriteEntity: FavoriteEntity) : HomeViewEvent
    data class NavigateToDetail(val productId: String) : HomeViewEvent
}

sealed interface HomeViewEffect : Effect {
}