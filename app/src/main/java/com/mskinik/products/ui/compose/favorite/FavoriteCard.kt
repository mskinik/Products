package com.mskinik.products.ui.compose.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mskinik.products.R
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.domain.model.ProductDetail

@Composable
fun FavoriteCard(
    productDetail: ProductDetail,
    onFavoriteClick: (id: String) -> Unit,
    onAddToCartClick: (ProductDetail) -> Unit,
    onIncreaseClick: (ProductDetail) -> Unit,
    onDecreaseClick: (ProductDetail) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Gray),
            ) {
                AsyncImage(
                    model = productDetail.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { onFavoriteClick(productDetail.id) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = productDetail.title.orEmpty(),
                style = MaterialTheme.typography.headlineSmall

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = productDetail.desc.orEmpty(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = productDetail.price.toString().orEmpty(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,

                ) {
                if (productDetail.stock == 0) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = stringResource(R.string.out_of_stock),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Red
                    )
                } else {
                    if (productDetail.quantity == null || productDetail.quantity == 0) {
                        IconButton(
                            onClick = {
                                onAddToCartClick(productDetail)
                            }, modifier = Modifier
                                .padding(4.dp)
                                .background(color = Color.White, shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add to cart"
                            )
                        }
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {
                                onDecreaseClick(productDetail)
                            }) {
                                Text(text = stringResource(R.string.minus_sign))
                            }
                            Text(productDetail.quantity.toString(), fontSize = 16.sp)
                            IconButton(onClick = {
                                onIncreaseClick(productDetail)
                            }) {
                                Text(text = stringResource(R.string.plus_sign))
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Favorite Card Stock 0 Preview")
fun ProductCardStockZeroPreview() {
    FavoriteCard(
        productDetail = ProductDetail(
            id = "1",
            title = "Macbook Pro",
            price = 10000.0,
            image = "https://www.google.com",
            desc = "MacBook Pro 2021 MacBook Pro 2021 MacBook Pro 2021 MacBook Pro 2021 MacBook Pro 2021",
            stock = 0, quantity = 0
        ),
        onFavoriteClick = { },
        onAddToCartClick = { },
        onIncreaseClick = { },
        onDecreaseClick = { }
    )
}

@Preview(showBackground = true, name = "Favorite Card Stock 1 Preview")
@Composable
fun ProductCardStockOnePreview() {
    FavoriteCard(
        productDetail = ProductDetail(
            id = "1",
            title = "Macbook Pro",
            price = 10000.0,
            image = "https://www.google.com",
            desc = "Macbook Pro 2021",
            stock = 1, quantity = 0
        ),
        onFavoriteClick = { },
        onAddToCartClick = { },
        onIncreaseClick = { },
        onDecreaseClick = { }
    )
}