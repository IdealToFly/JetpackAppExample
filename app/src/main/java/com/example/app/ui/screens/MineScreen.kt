package com.example.app.ui.screens


import android.R.attr.x
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.ui.components.TopAppBar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon



@Composable
fun MineScreen() {

    val menus = listOf(
        MenuItem(R.drawable.banner1, "学习积分"),
        MenuItem(R.drawable.banner1, "浏览记录"),
        MenuItem(R.drawable.banner1, "学习档案"),
        MenuItem(R.drawable.banner1, "常见问题"),
        MenuItem(R.drawable.banner1, "版本信息"),
        MenuItem(R.drawable.banner1, "个人设置"),
        MenuItem(R.drawable.banner1, "联系我们"),
    )
    
   

    Column(modifier = Modifier) {
        TopAppBar() {
            Text("我的", fontSize = 18.sp, color = Color.White)
        }

        LazyColumn(modifier = Modifier.padding(8.dp)) {
            // 头像部分
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 24.dp)) {
                    Image(
                        painter = painterResource(R.drawable.arisa),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(62.dp).clip(CircleShape)
                    )

                    Column(verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                        Text("未登录", color = Color(0xFF333333), fontSize = 18.sp)
                        Text("已坚持学习0天", color = Color(0xFF999999), fontSize = 12.sp)
                    }

                }
            }

            itemsIndexed(menus) { index, menu ->
                if (index == 3) {
                    Spacer(modifier = Modifier.height(16.dp).background(Color(0xFFF5F5F5)))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = menu.icon),
                        contentDescription = null,
                        modifier = Modifier.size(17.dp)
                    )

                    Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        ) {

                            Text(
                                text = menu.title,
                                color = Color(0xFF333333),
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.width(13.dp)
                            )

                        }

                        HorizontalDivider()

                    }

                }

            }


        }


    }
}

data class MenuItem(@DrawableRes val icon : Int, val title : String)

@Preview
@Composable
fun MineScreenPreview() {
    MineScreen()
}

