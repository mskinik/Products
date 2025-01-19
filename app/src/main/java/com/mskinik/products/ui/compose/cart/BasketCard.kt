package com.mskinik.products.ui.compose.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mskinik.products.R
import com.mskinik.products.domain.model.ProductDetail


@Composable
fun BasketCard(
    productDetail: ProductDetail,
    onDecreaseClick: (ProductDetail) -> Unit,
    onIncreaseClick: (ProductDetail) -> Unit,
    onDelete: (ProductDetail) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = productDetail.image,
            modifier = Modifier.size(50.dp),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(productDetail.title.orEmpty(), fontSize = 16.sp)
            Text("${productDetail.price} TL", fontSize = 14.sp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                onDecreaseClick(productDetail)
            }) {
                Text(stringResource(R.string.minus_sign))
            }
            Text(productDetail.quantity.toString(), fontSize = 16.sp)
            IconButton(onClick = {
                onIncreaseClick(productDetail)
            }) {
                Text(stringResource(R.string.plus_sign))
            }
        }

        IconButton(onClick = {onDelete(productDetail)}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                contentDescription = "Delete"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasketCard() {
    BasketCard(
        productDetail = ProductDetail(
            id = "1",
            title = "Sample Product",
            price = 10.0,
            quantity = 1,
            image = "https://via.placeholder.com/150",
            stock = 10,
            desc = "Sample Description"
        ),
        onDecreaseClick = {},
        onIncreaseClick = {},
        onDelete = {}
    )
}