package com.example.app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ViewModel.TaskViewModel
import com.example.app.ui.components.ChartView
import com.example.app.ui.components.CircleRing
import com.example.app.ui.components.DailyTaskContent
import com.example.app.ui.components.appBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(taskVM: TaskViewModel = viewModel()) {
    // 创建一个垂直排列的Column，填充整个屏幕，并使用垂直渐变背景

    // 圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }

    LaunchedEffect(taskVM.pointOfYear) {
        taskVM.updatePointPercent()
        taskVM.updateTips()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF149EE7), Color.White)))
    ) {
        Row(
            modifier = Modifier
                .padding(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                )
                .height(appBarHeight), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "学习任务",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            // 学习周期
            item {
                Text(
                    text = taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(8.dp)
                ) {
                    // 圆环
                    CircleRing(boxWidthDp, taskVM)
                    // 进度数据
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )
                        Text("学年积分", fontSize = 12.sp, color = Color.White)
                    }

                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Column(modifier = Modifier.offset(x = 80.dp)) {
                        Text(
                            text = "${taskVM.totalPointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(text = "学年规定积分", fontSize = 12.sp, color = Color.White)
                    }
                    Column(modifier = Modifier.offset(x = (-80).dp)) {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(text = "还差", fontSize = 12.sp, color = Color.White)
                    }

                }
            }

            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .fillMaxSize()
                        .padding(8.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = "学习明细", fontSize = 16.sp, color = Color(0xFF333333))

                    Text(text = "最近一周获得积分情况", fontSize = 14.sp, color = Color(0xFF999999))

                    ChartView(taskVM.pointsOfWeek, modifier = Modifier.padding(horizontal = 8.dp))

                    Row() {
                        taskVM.weeks.forEach {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xFF999999),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Text(
                        text = taskVM.tips,
                        color = Color(0xFF149EE7),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x33149EE7))
                            .padding(8.dp).fillMaxSize()
                        )

                    DailyTaskContent()


                }

            }



        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}

