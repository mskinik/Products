package com.mskinik.products.ui.fragment.checkout

import com.mskinik.products.domain.model.Product
import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CheckoutViewState(
    override val loading: Boolean = false,
) : State

sealed interface CheckoutViewEvent : Event {

}

sealed interface CheckoutViewEffect : Effect {
}