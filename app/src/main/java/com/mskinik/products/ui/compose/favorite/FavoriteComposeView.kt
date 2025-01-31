package com.mskinik.products.ui.compose.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.util.orZero
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavoriteComposeView(
    productDetailList: ImmutableList<ProductDetail>?,
    onFavoriteClick: (id: Int) -> Unit,
    onAddToCartClick: (ProductDetail) -> Unit,
    onDecreaseClick: (ProductDetail) -> Unit,
    onIncreaseClick: (ProductDetail) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(productDetailList?.size.orZero()) { index ->
                productDetailList?.get(index)?.let { productDetail ->
                    FavoriteCard(
                        productDetail = productDetail,
                        onFavoriteClick = onFavoriteClick,
                        onAddToCartClick = onAddToCartClick,
                        onIncreaseClick = onIncreaseClick,
                        onDecreaseClick = onDecreaseClick
                    )
                }
            }
        }
    }
}