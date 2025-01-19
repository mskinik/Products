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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mskinik.products.data.model.local.Favorite

@Composable
fun FavoriteCard(
    favorite: Favorite,
    onFavoriteClick: (id: String) -> Unit,
    onAddToCartClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Gray),
            ) {
                AsyncImage(
                    model = favorite.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(onClick = {onFavoriteClick(favorite.id)} , modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favori",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = favorite.title.orEmpty(),
                style = MaterialTheme.typography.headlineSmall

            )

            Text(
                text = favorite.price.toString().orEmpty(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,

            ) {
                IconButton(onClick = onAddToCartClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Sepete Ekle"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    FavoriteCard(
        favorite = Favorite(
            id = "1",
            title = "Macbook Pro",
            price = 10000.0,
            image = "https://www.google.com",
            desc = "Macbook Pro 2021"),
        onFavoriteClick = { /* Favorilere ekleme işlemi */ },
        onAddToCartClick = { /* Sepete ekleme işlemi */ }
    )
}