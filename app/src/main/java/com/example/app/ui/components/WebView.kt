package com.example.app.ui.components


import android.renderscript.Script
import android.util.Log
import android.view.KeyEvent.DispatcherState
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun WebView(modifier: Modifier = Modifier, state : WebViewState) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    // WebView变化时重新订阅流数据
    LaunchedEffect(webView, state) {
        with(state) {
            webView?.handleEvents()
        }
    }

    AndroidView(factory = { context ->
        WebView(context).apply {
            with(settings) {
                javaScriptEnabled = true
            }
        }.also { webView = it }
    }) { view ->
        when (val content = state.content) {
            is WebContent.Url -> {
                val url = content.url
                if (url.isNotEmpty() && url != view.url) {
                    // url不空或者当前的WebView加载的url不相同
                    view.loadUrl(content.url)
                }
            }
            is WebContent.Data -> {
                Log.i("===", content.data)
                view.loadDataWithBaseURL(
                    content.baseUrl,
                    content.data,
                    "text/html",
                    "utf-8",
                    null
                )
            }
        }
    }

}

sealed class WebContent {
    data class Url(val url : String) : WebContent()
    data class Data(val data : String, val baseUrl: String? = null) : WebContent()
}


class WebViewState(private val coroutineScope: CoroutineScope, webContent : WebContent) {
    var content by mutableStateOf(webContent)

    var pageTitle : String? by mutableStateOf(null)

    enum class EventType {
        EVALUATE_JAVASCRIPT  // 执行js方法

    }

    class Event(val type: EventType, val args: String,
                val callback: ((String) -> Unit)? = {})


    private val events : MutableSharedFlow<Event> = MutableSharedFlow()

    suspend fun WebView.handleEvents(): Unit = withContext(Dispatchers.Main) {
        events.collect { event ->
            when(event.type) {
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavaScript(event.args, event.callback)
            }
        }
    }

    fun evaluateJavaScript(script: String, resultCallBack : ((String) -> Unit)? = {}) {
        val event = Event(EventType.EVALUATE_JAVASCRIPT, script, resultCallBack)
        coroutineScope.launch {
            events.emit(event) // 推送流
        }
    }
}

@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope = rememberCoroutineScope(), url : String) = remember(key1 = url) {
    WebViewState(coroutineScope, WebContent.Url(url))
}

@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope = rememberCoroutineScope(), data : String, baseUrl : String? = null) = remember(key1 = data, key2 = baseUrl) {
    WebViewState(coroutineScope, WebContent.Data(data, baseUrl))
}




