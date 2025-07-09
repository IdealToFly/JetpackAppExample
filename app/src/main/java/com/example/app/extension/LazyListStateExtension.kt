package com.example.app.extension


import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LazyListState.OnBottomReached(loadMore: () -> Unit) {
    val shouldLoadMore = remember {
        // 另一个状态计算派生
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }
    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore.value) {
            loadMore()
        }
    }
}



