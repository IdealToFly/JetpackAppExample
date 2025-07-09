package com.example.app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ViewModel.VideoDetailModel
import com.example.app.ui.components.WebView
import com.example.app.ui.components.rememberWebViewState
import com.example.app.ui.components.video.VideoView
import com.tencent.rtmp.TXVodPlayer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoDetailScreen(videoDetailModel: VideoDetailModel = viewModel(), onBack: () -> Unit) {

    val webViewState = rememberWebViewState(data = videoDetailModel.videoDesc)

    val vodPlayer = TXVodPlayer(LocalContext.current)

    LaunchedEffect(vodPlayer) {
        val url = "http://media.w3.org/2010/05/sintel/trailer.mp4"
        vodPlayer.startVodPlay(url)
    }

    Scaffold(topBar = { TopAppBar(title = { Text(
        text = "视频详情",
        fontSize = 18.sp
    )}, navigationIcon = {
        Icon(
            imageVector = Icons.Default.NavigateBefore,
            contentDescription = null,
            modifier = Modifier.clickable {
                onBack()
            }
        )
    }) }, modifier = Modifier
        .background(MaterialTheme.colorScheme.primary)
        .statusBarsPadding())
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 视频区域
            Box(modifier = Modifier.height(200.dp)) {
                VideoView(vodPlayer = vodPlayer)
            }
            // 简介
            WebView(state = webViewState)
        }
        }
    }

}


