package com.example.app.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.model.entity.NavigationItem


@Composable
fun MainFrame(onNavigateToArticle : () -> Unit = {}, onNavigateToVideo : () -> Unit = {}, onNavigateToStudyHistory: () -> Unit = {}) {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person)
    )

    var currentNavigationIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), 
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.navigationBarsPadding() // 给底部导航栏添加适当的内边距contentWindowInsets = WindowInsets(0.dp) // 禁用系统栏内边距
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = currentNavigationIndex == index,
                        onClick = { currentNavigationIndex = index },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = navigationItem.title,
                                tint = if (currentNavigationIndex == index) {
                                    Color(0xFF149EE7) // Selected color
                                } else {
                                    Color(0xFF999999) // Unselected color
                                }
                            )
                        },
                        label = {
                            Text(
                                text = navigationItem.title,
                                color = if (currentNavigationIndex == index) {
                                    Color(0xFF149EE7) // Selected color
                                } else {
                                    Color(0xFF999999) // Unselected color
                                }
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF149EE7),
                            selectedTextColor = Color(0xFF149EE7),
                            unselectedIconColor = Color(0xFF999999),
                            unselectedTextColor = Color(0xFF999999),
                            indicatorColor = Color.Transparent // Or set your own indicator color
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentNavigationIndex) {
                0 -> StudyScreen(onNavigateToArticle = onNavigateToArticle, onNavigateToVideo = onNavigateToVideo, onNavigateToStudyHistory = onNavigateToStudyHistory)
                1 -> TaskScreen()
                2 -> MineScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}

