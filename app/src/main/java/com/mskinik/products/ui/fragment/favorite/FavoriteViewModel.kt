package com.mskinik.products.ui.fragment.favorite

import androidx.lifecycle.viewModelScope
import com.mskinik.products.domain.usecase.FavoriteUseCase
import com.mskinik.products.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    BaseViewModel<FavoriteViewEvent, FavoriteViewState, FavoriteViewEffect>() {
    override fun setInitialState(): FavoriteViewState = FavoriteViewState()

    override fun handleEvents(event: FavoriteViewEvent) {
        when (event) {
            is FavoriteViewEvent.DeleteFavorite -> {
                deleteFavorite(event.id)
            }
            else -> {
                // do nothing
            }
        }
    }

    init {
        viewModelScope.launch {
            favoriteUseCase.getFavorites().collect{
                setState { copy(favoriteList = it.toImmutableList()) }
            }
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.deleteFavorite(id)
        }
    }
}