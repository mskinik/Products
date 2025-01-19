package com.mskinik.products.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mskinik.products.ui.theme.ProductsTheme

fun ComposeView.applyComposeView(
    viewCompositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
    content: @Composable () -> Unit
){
    this.apply {
        setViewCompositionStrategy(viewCompositionStrategy)
        setContent {
            ProductsTheme{
                content()
            }
        }
    }
}