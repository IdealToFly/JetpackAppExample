package com.example.app.ui.components.video


import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.app.R
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView


@Composable
fun VideoView(vodPlayer: TXVodPlayer) {
    AndroidView(factory = { context ->
        (LayoutInflater.from(context).inflate(R.layout.video, null, false)
            .findViewById<TXCloudVideoView>(R.id.videoView)!!).apply {
                vodPlayer.setPlayerView(this)
        }
    })
}


