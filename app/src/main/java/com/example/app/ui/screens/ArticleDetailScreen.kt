package com.example.app.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app.ViewModel.ArticleViewModel
import com.example.app.ui.components.WebView
import com.example.app.ui.components.rememberWebViewState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(articleViewModel: ArticleViewModel = viewModel(), onBack: () -> Unit) {

    val webViewState = rememberWebViewState(data = articleViewModel.content)

    var fontScale by remember {
        mutableFloatStateOf(1.0f)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "文章详情",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onBack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Default.TextFields,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isVisible) {
                                        scaffoldState.bottomSheetState.expand()
                                    } else {
                                        scaffoldState.bottomSheetState.hide()
                                    }
                                }

                            }
                            .padding(8.dp))
                }
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        sheetContent = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("字体大小", fontSize = 16.sp)
                Slider(value = fontScale, onValueChange = {
                    fontScale = it
                    webViewState.evaluateJavaScript("document.body.style.zoom = $it")
                }, steps = 3, valueRange = 0.75f..1.75f)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("较小", fontSize = 14.sp)
                    Text("普通", fontSize = 14.sp)
                    Text("普大", fontSize = 14.sp)
                    Text("超大", fontSize = 14.sp)
                    Text("巨大", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

            }
        },
        sheetPeekHeight = 0.dp
    ) { paddingValues ->
        Log.i("====", "PaddingValues: $paddingValues")
        WebView(
            modifier = Modifier.padding(paddingValues), state = webViewState)
    }

}

