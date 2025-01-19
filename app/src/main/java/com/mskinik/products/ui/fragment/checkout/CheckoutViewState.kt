package com.mskinik.products.ui.fragment.checkout

import com.mskinik.products.ui.base.Effect
import com.mskinik.products.ui.base.Event
import com.mskinik.products.ui.base.State
import com.mskinik.util.orZero

data class CheckoutViewState(
    override val loading: Boolean = false,
    val phoneText: String? = null,
    val mailText: String? = null,
    val nameText: String? = null
) : State {
    val isButtonEnable: Boolean = listOf(phoneText, mailText, nameText).all { (it?.length.orZero()) >= 2 }}

sealed interface CheckoutViewEvent : Event {
    data class OnPhoneTextChanged(val phone: String) : CheckoutViewEvent
    data class OnMailTextChanged(val mail: String) : CheckoutViewEvent
    data class OnNameTextChanged(val name: String) : CheckoutViewEvent
    data object OnCheckoutClicked : CheckoutViewEvent
}

sealed interface CheckoutViewEffect : Effect {
    data object NavigateToHome : CheckoutViewEffect
}