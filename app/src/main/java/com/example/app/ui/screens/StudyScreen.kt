package com.example.app.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ViewModel.ArticleViewModel
import com.example.app.ViewModel.MainViewModel
import com.example.app.ViewModel.VideoDetailModel
import com.example.app.ui.components.ArticleItem
import com.example.app.ui.components.NotificationContent
import com.example.app.ui.components.SwiperContent
import com.example.app.ui.components.TopAppBar
import com.example.app.ui.components.VideoItem
import com.google.accompanist.placeholder.material.placeholder



@Composable
fun StudyScreen(
    vm: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoDetailModel = viewModel(),
    onNavigateToArticle : () -> Unit = {},
    onNavigateToVideo: () -> Unit = {},
    onNavigateToStudyHistory: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        vm.categoryData()
    }

    Column(
        modifier = Modifier,
    ) {
        TopAppBar {
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33FFFFFF),

            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        "搜索你感兴趣的资讯或课程",
                        color = Color.White,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "学习\n进度", fontSize = 10.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }

        TabRow(
            selectedTabIndex = vm.categoryIndex,
            modifier = Modifier.background(Color(0x22149EE7)),
            contentColor = Color(0xFF149EE7),
        ) {
            vm.categories.forEachIndexed { index, category ->
                Tab(
                    selected = vm.categoryIndex == index,
                    onClick = {
                        vm.updatecategoryIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8.dp).placeholder(visible = !vm.categoryLoaded, color = Color.LightGray),
                        fontSize = 14.sp
                    )
                }
            }
        }

        TabRow(
            selectedTabIndex = vm.currentTypeIndex,
            containerColor = Color.Transparent,
            contentColor = Color(0xFF149EE7)
        ) {
            vm.types.forEachIndexed { index, (title, icon) ->
                LeadingIconTab(
                    selected = vm.currentTypeIndex == index, onClick = {
                        vm.updateTypeIndex(index)
                    }, selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = {
                        Icon(imageVector = icon, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 16.sp
                        )
                    })
            }
        }

        LazyColumn {

            item { SwiperContent(vm) }

            item { NotificationContent(vm) }

            if (vm.currentTypeIndex == 0) {
                items(articleViewModel.list) { article ->
                    ArticleItem(article = article, modifier = Modifier.clickable{
                        onNavigateToArticle.invoke()
                    }
                    )
                }
            } else {
                items(videoViewModel.list) { video ->
                    VideoItem(modifier = Modifier.clickable {
                        onNavigateToVideo.invoke()
                    }, video)
                }
            }
        }


    }
}





