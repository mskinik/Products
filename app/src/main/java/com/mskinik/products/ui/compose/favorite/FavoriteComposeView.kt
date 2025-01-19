package com.mskinik.products.ui.compose.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mskinik.products.data.model.local.Favorite
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavoriteComposeView (
    favoriteList: ImmutableList<Favorite>?,
    onFavoriteClick: (id:String) -> Unit,
    ) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(favoriteList?.size ?: 0) { index ->
                favoriteList?.get(index)?.let { favorite ->
                    FavoriteCard (favorite = favorite,onFavoriteClick= onFavoriteClick, onAddToCartClick = {})
                }
            }
        }
    }
}