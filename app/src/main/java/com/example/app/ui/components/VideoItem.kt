package com.example.app.ui.components


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.example.app.model.entity.VideoEntity

@Composable
fun VideoItem(modifier: Modifier = Modifier, videoEntity: VideoEntity) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // 创建引用
        val (cover, title, type, duration, divider) = createRefs()

        // 封面图片
        AsyncImage(
            model = videoEntity.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(cover) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                    width = Dimension.value(115.5.dp)
                }
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp))
        )

        // 标题
        Text(
            text = videoEntity.title,
            fontSize = 16.sp,
            color = Color(0xFF666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(cover.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        // 类型
        Text(
            text = videoEntity.type,
            fontSize = 16.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(type) {
                start.linkTo(title.start)
                bottom.linkTo(parent.bottom)
            }
        )

        // 时长
        Text(
            text = "时长:${videoEntity.duration}",
            fontSize = 16.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(duration) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )

        // 分割线
        HorizontalDivider(
            modifier = Modifier.constrainAs(divider) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            thickness = 1.dp,       // 分割线厚度
            color = Color.LightGray // 可以自定义颜色
        )
    }
}


