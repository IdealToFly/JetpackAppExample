package com.example.app.ui.components


import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.navigation.Destinations
import com.example.app.ui.screens.MainFrame
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.example.app.ViewModel.UserViewModel
import com.example.app.compositionLocal.LocalUserViewModel
import com.example.app.ui.screens.ArticleDetailScreen
import com.example.app.ui.screens.LoginScreen
import com.example.app.ui.screens.VideoDetailScreen
import com.google.accompanist.insets.ProvideWindowInsets


@Composable
fun NavHostApp() {

    val navController = rememberNavController()
    ProvideWindowInsets (

    ) {

        CompositionLocalProvider(LocalUserViewModel provides UserViewModel(LocalContext.current)) {

            val userViewModel = LocalUserViewModel.current

            NavHost(
                navController = navController,
                startDestination = Destinations.HomeFrame.route
            ) {
                // 首页大框架
                composable(Destinations.HomeFrame.route, enterTransition = {
                    slideIntoContainer(SlideDirection.Right)
                }, exitTransition = {
                    slideOutOfContainer(SlideDirection.Left)
                }) {
                    MainFrame(onNavigateToArticle = {
                        navController.navigate(Destinations.ArticleDetail.route)
                    }, onNavigateToVideo = {
                        navController.navigate(Destinations.VideoDetail.route)
                    }, onNavigateToStudyHistory = {
                        if (userViewModel.logged) {
                            // 已登录
                        } else {
                            navController.navigate(Destinations.Login.route)
                        }
                    })
                }

                composable(Destinations.ArticleDetail.route, enterTransition = {
                    slideIntoContainer(SlideDirection.Left)
                }, exitTransition = {
                    slideOutOfContainer(SlideDirection.Right)
                }) {
                    ArticleDetailScreen(onBack = {
                        navController.popBackStack()
                    })
                }

                composable(Destinations.VideoDetail.route, enterTransition = {
                    slideIntoContainer(SlideDirection.Left)
                }, exitTransition = {
                    slideOutOfContainer(SlideDirection.Right)
                }) {
                    VideoDetailScreen(onBack = {
                        navController.popBackStack()
                    })
                }

                composable(Destinations.Login.route, enterTransition = {
                    slideIntoContainer(SlideDirection.Left)
                }, exitTransition = {
                    slideOutOfContainer(SlideDirection.Right)
                }) {
                    LoginScreen {
                        navController.popBackStack()
                    }
                }

            }
        }
    }

}

@Preview
@Composable
fun NavHostAppPreview() {
    NavHostApp()
}

