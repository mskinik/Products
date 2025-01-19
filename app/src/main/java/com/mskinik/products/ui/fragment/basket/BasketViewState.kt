package com.mskinik.products.ui.fragment.basket

import com.mskinik.products.domain.model.Product
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import com.mskinik.products.ui.fragment.favorite.FavoriteViewEvent
import com.mskinik.util.orZero
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class BasketViewState(
    override val loading: Boolean = false,
    val productDetailList: ImmutableList<ProductDetail>? = persistentListOf(),

) : State {
    val totalPrice: Double?
        get() = productDetailList?.sumOf { (it.price.orZero()) * (it.quantity.orZero())}
}

sealed interface BasketEvent : Event {
    data object NavigateToCheckout : BasketEvent
    data class DeleteProductDetail(val productDetail: ProductDetail) : BasketEvent
    data class DecreaseQuantity(val productDetail: ProductDetail) : BasketEvent
    data class IncreaseQuantity(val productDetail: ProductDetail) : BasketEvent
}

sealed interface BasketEffect : Effect {

}