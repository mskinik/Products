package com.mskinik.products.ui.fragment.root

import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(): BaseViewModel<RootEvent,RootViewState,RootEffect>() {
    override fun setInitialState(): RootViewState = RootViewState()

    override fun handleEvents(event: RootEvent) {
        when (event) {
           else -> {

           }
        }
    }
}