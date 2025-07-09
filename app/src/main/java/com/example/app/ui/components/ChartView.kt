package com.example.app.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ChartView(points: List<Double>, modifier: Modifier) {

    // 每一行的高度
    val heightForRow = 24

    // 总行数
    val countForRow = 5

    val perY = 8 // 24 * 5 / 15 每8dp一积分，每一行三积分

    // 小圆圈半径
    val circleRadius = 2.5

    // 画布宽度 = 屏幕宽度 - padding * 2
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2

    // 画布高度
    val canvasHeight = heightForRow * countForRow + circleRadius * 2

    // 7平方的宽度
    val averageOfWidth = canvasWidth / 7


    Canvas(modifier = Modifier.size(width = canvasWidth.dp, height = canvasHeight.dp)) {
        // 画背景的线
        for (index in 0..countForRow) {
            val startY = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(
                Color(0xFFEEEEEE),
                start = Offset(0f, startY),
                end = Offset(canvasWidth.dp.toPx(), startY),
                strokeWidth = 2.5f
            )

        // 画折线、圆圈
        for (index in 0 until points.count()) {
            val circleCenter = Offset(
                (averageOfWidth * index + averageOfWidth / 2).dp.toPx(),
                (heightForRow * countForRow - points[index] * perY + circleRadius).dp.toPx()
            )
            drawCircle(
                Color(0xFF149EE7),
                radius = circleRadius.dp.toPx(),
                center = circleCenter,
                style = Stroke(width = 5f)
            )

            if (index < points.count() - 1) {
                val nextPointOffset = Offset(
                    (averageOfWidth * (index + 1) + averageOfWidth / 2).dp.toPx(),
                    (heightForRow * countForRow - points[(index + 1)] * perY + circleRadius).dp.toPx()
                )
                drawLine(
                    Color(0xFF149EE7),
                    start = circleCenter,
                    end = nextPointOffset,
                    strokeWidth = 5f
                )
            }
        }



        }
    }

}



