package com.example.app.ui.components


import android.R.attr.maxLines
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ViewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun NotificationContent(vm: MainViewModel) {
    Row(
        modifier = Modifier
            .background(Color(0x22149EE7))
            .clip(RoundedCornerShape(8.dp))
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "最新活动", fontSize = 14.sp, color = Color(0xFF149EE7))
        val pagerState = rememberPagerState(pageCount = { vm.notifications.size })

        Spacer(modifier = Modifier.width(8.dp))

        // 自动轮播逻辑
        LaunchedEffect(pagerState) {
            while (true) {
                delay(3000)
                if (pagerState.currentPage < vm.notifications.size - 1) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    pagerState.animateScrollToPage(0)
                }
            }
        }

        VerticalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            modifier = Modifier.weight(1f),

        ) { page ->
            Text(
                text = vm.notifications[page],
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF333333),
                    fontSize = 14.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "更多", color = Color(0xFF666666),
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun NotificationContentPreview() {
    NotificationContent(MainViewModel())
}

