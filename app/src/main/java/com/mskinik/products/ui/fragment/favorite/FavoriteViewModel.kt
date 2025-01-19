package com.mskinik.products.ui.fragment.favorite

import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(): BaseViewModel<FavoriteViewEvent,FavoriteViewState,FavoriteViewEffect>() {
    override fun setInitialState(): FavoriteViewState  = FavoriteViewState()

    override fun handleEvents(event: FavoriteViewEvent) {
       when (event) {
           else -> {
               // do nothing
           }
       }
    }
}