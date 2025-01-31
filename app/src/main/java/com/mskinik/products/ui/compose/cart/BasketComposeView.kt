import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mskinik.products.R
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.ui.compose.cart.BasketCard
import com.mskinik.products.ui.compose.cart.PriceSummary
import com.mskinik.products.ui.fragment.basket.BasketViewState
import com.mskinik.util.orZero

@Composable
fun BasketComposeView(
    basketViewState: BasketViewState,
    onIncreaseClick: (ProductDetail) -> Unit,
    onDelete: (ProductDetail) -> Unit,
    onDecreaseClick: (ProductDetail) -> Unit,
    onCheckoutClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (basketViewState.productDetailList.isNullOrEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.cart_is_empty),
                    fontSize = 24.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(basketViewState.productDetailList.toList().orEmpty()) { productDetail ->
                    BasketCard(
                        productDetail,
                        onIncreaseClick = onIncreaseClick,
                        onDelete = onDelete,
                        onDecreaseClick = onDecreaseClick
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            PriceSummary(basketViewState.totalPrice.orZero())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onCheckoutClick() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    stringResource(R.string.checkout),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingCartScreen() {
    BasketComposeView(
        basketViewState = BasketViewState(productDetailList = null),
        onIncreaseClick = {},
        onDelete = {},
        onDecreaseClick = {},
        onCheckoutClick = {})
}
