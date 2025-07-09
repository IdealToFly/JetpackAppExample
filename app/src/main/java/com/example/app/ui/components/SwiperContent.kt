package com.example.app.ui.components


import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil3.compose.AsyncImage
import com.example.app.ViewModel.MainViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperContent(vm : MainViewModel) {

    val actualCount = vm.swiperData.size
    if (actualCount == 0) return // 无数据时不渲染

    // 虚拟页数（实现无限循环效果）
    val virtualCount = Int.MAX_VALUE
    val initialIndex = Int.MAX_VALUE / 2 // 中间开始，支持双向滑动

    val pagerState = rememberPagerState(initialPage = initialIndex) { virtualCount }
    val scope = rememberCoroutineScope()


    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) { index ->
        // 计算实际数据索引
        val actualIndex = index % actualCount
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7f / 3f)
                .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Yellow)),
            contentScale = ContentScale.Crop
        )
    }



    // 自动轮播（可选）
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // 3秒切换一次
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                vm.swiperData()
            }
        }
    }
}

@Preview
@Composable
fun SwiperContentPreview() {
    SwiperContent(MainViewModel())
}

