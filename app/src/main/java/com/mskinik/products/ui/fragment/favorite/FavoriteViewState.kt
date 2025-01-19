package com.mskinik.products.ui.fragment.favorite

import com.mskinik.products.domain.model.Favorite
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FavoriteViewState(
    override val loading: Boolean = false,
    val favoriteEntityList: ImmutableList<Favorite>? = persistentListOf(),
    val productDetailList: ImmutableList<ProductDetail>? = persistentListOf()
) : State

sealed interface FavoriteViewEvent : Event {
    data class DeleteFavorite(val id: String) : FavoriteViewEvent
    data class AddToCart(val productDetail: ProductDetail) : FavoriteViewEvent
    data class DecreaseQuantity(val productDetail: ProductDetail) : FavoriteViewEvent
    data class IncreaseQuantity(val productDetail: ProductDetail) : FavoriteViewEvent
}

sealed interface FavoriteViewEffect : Effect {
}