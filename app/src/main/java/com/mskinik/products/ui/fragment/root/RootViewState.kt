package com.mskinik.products.ui.fragment.root

import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State

data class RootViewState(
    override val loading: Boolean = false,
    val totalQuantity: Int? = 0,
) : State

sealed interface RootEvent : Event {

}

sealed interface RootEffect : Effect {
}