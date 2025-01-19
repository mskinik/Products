package com.mskinik.products.ui.fragment.detail

import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State

data class ProductDetailViewState(
    override val loading: Boolean = false,
    val id: String? = null,
    val title: String? = null,
    val desc: String? = null,
    val price: Double? = null,
    val image: String? = null,
    val stock: Int? = null,
    val quantity: Int? = null,
    val brand: String? = null,
    val category: String? = null,
    val isFavorite: Boolean? = false,
    val discount: Double? = null,
) : State

sealed interface ProductDetailEvent : Event {
    data object OnFavoriteClick : ProductDetailEvent
    data object DecreaseQuantity : ProductDetailEvent
    data object IncreaseQuantity : ProductDetailEvent
    data object AddToCart : ProductDetailEvent
}

sealed interface ProductDetailEffect : Effect {
}