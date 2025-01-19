package com.mskinik.products.ui.compose.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mskinik.products.R

@Composable
fun PriceSummary(price: Double) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(R.string.total_title), fontSize = 18.sp)
            Text(String.format("%.2f TL", price), fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPriceSummary() {
    PriceSummary(price = 100.0)
}