package com.mskinik.products.ui.fragment.detail

import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ProductDetailViewState(
    override val loading: Boolean = false,
    val product: Product? = null
) : State

sealed interface ProductDetailEvent : Event {

}

sealed interface ProductDetailEffect : Effect {
}