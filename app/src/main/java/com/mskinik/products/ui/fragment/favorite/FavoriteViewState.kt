package com.mskinik.products.ui.fragment.favorite

import com.mskinik.products.data.model.local.Favorite
import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FavoriteViewState(
    override val loading: Boolean = false,
    val favoriteList: ImmutableList<Favorite>? = persistentListOf()
) : State

sealed interface FavoriteViewEvent : Event {
    data class DeleteFavorite(val id: String) : FavoriteViewEvent
}

sealed interface FavoriteViewEffect : Effect {
}