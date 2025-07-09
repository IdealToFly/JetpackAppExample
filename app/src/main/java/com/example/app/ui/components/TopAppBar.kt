package com.example.app.ui.components


import androidx.compose.animation.core.Transition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars

val appBarHeight = 56.dp

@Composable
fun TopAppBar(modifier: Modifier = Modifier, content : @Composable () -> Unit ) {

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(Color.Transparent)
    }

    val statusBarHeightDp = WindowInsets.statusBars.
    asPaddingValues().calculateTopPadding()

    Row(modifier = Modifier.background(
        Brush.linearGradient(
            listOf(MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary)
        )).fillMaxWidth().height(appBarHeight + statusBarHeightDp)
        .padding(top = statusBarHeightDp).then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically ,
        ) {
        content()
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar() {
        Text("标题")
    }
}

