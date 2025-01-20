package com.mskinik.products.ui.compose.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mskinik.products.R
import com.mskinik.products.ui.fragment.detail.ProductDetailViewState
import com.mskinik.util.orZero

@Composable
fun ProductDetailComposeView(
    productDetailViewState: ProductDetailViewState,
    onFavoriteClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = productDetailViewState.image.orEmpty(),
                contentDescription = "MacBook Pro Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
            Back(onBackClick)
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Column {
                    FavoriteIcon(onFavoriteClick, productDetailViewState.isFavorite)
                    Spacer(modifier = Modifier.height(8.dp))
                    productDetailViewState.discount?.takeIf { it != 0.0 }?.let { discount ->
                        DiscountBadge(discount)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = productDetailViewState.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productDetailViewState.desc.orEmpty(),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.brand, productDetailViewState.brand.orEmpty()),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${productDetailViewState.price} TL",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
                .weight(1f)
        )
        if (productDetailViewState.quantity.orZero() > 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    onDecreaseClick()
                }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_minus),
                        contentDescription = ""
                    )
                }
                Text(productDetailViewState.quantity.toString(), fontSize = 24.sp)
                IconButton(onClick = {
                    onIncreaseClick()
                }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                        contentDescription = ""
                    )
                }
            }
        } else {
            Button(
                onClick = onAddToCartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.add_to_cart), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DiscountBadge(discount: Double) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Blue, shape = CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "% ${discount}",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Back(onBackClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back Icon",
            )
        }

    }
}

@Composable
fun FavoriteIcon(onFavoriteClick: () -> Unit, isFavorite: Boolean?) {
    Box(
        modifier = Modifier
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_favorite),
                contentDescription = "Favorite Icon",
                tint = if (isFavorite == true) Color.Red else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, name = "Product Detail Preview")
@Composable
fun ProductDetailComposeViewPreview() {
    ProductDetailComposeView(
        productDetailViewState = ProductDetailViewState(
            image = "https://www.example.com/image.jpg",
            title = "Sample Product",
            desc = "This is a sample product description.",
            brand = "Sample Brand",
            price = 99.99,
            quantity = 1
        ),
        onFavoriteClick = {},
        onAddToCartClick = {},
        onDecreaseClick = {},
        onIncreaseClick = {},
        onBackClick = {}
    )
}
