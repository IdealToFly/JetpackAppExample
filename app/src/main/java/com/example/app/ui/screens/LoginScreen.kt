package com.example.app.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ViewModel.UserViewModel
import com.example.app.compositionLocal.LocalUserViewModel

@Composable
fun LoginScreen(onClose: () -> Unit) {
    val userViewModel = LocalUserViewModel.current
    var screenWidth: Float
    var screenHeight: Float

    with(LocalDensity.current) {
        screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
        screenHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.arisa),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),

        )

        Box(modifier = Modifier.fillMaxSize().background(
            Brush.linearGradient(listOf(Color(0xffbb8378), Color.Transparent),
                start = Offset(x=screenWidth, y=0f), end = Offset(x=0f, y=screenHeight))))
        Box(modifier = Modifier.fillMaxSize().background(
            Brush.linearGradient(listOf(Color(0xFF149EE7), Color.Transparent),
                start = Offset(x=0f, y=screenHeight), end = Offset(x=screenWidth, y=0f))))
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "用户登录",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(3f)
            )

            Column(modifier = Modifier.fillMaxWidth().weight(3f), horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(text = "用户名", fontSize = 14.sp, color = Color.White)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,    // 聚焦时容器颜色
                        unfocusedContainerColor = Color.Transparent,  // 未聚焦时容器颜色
                        focusedIndicatorColor = Color.LightGray,           // 聚焦时下划线颜色
                        unfocusedIndicatorColor = Color.LightGray,         // 未聚焦时下划线颜色
                        focusedTextColor = Color.LightGray,               // 聚焦时文本颜色
                        unfocusedTextColor = Color.LightGray,             // 未聚焦时文本颜色
                        cursorColor = Color.White
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                showPassword = !showPassword
                            }
                        )
                    },
                    label = {
                        Text(text = "密码", fontSize = 14.sp, color = Color.White)
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,    // 聚焦时容器颜色
                        unfocusedContainerColor = Color.Transparent,  // 未聚焦时容器颜色
                        focusedIndicatorColor = Color.LightGray,           // 聚焦时下划线颜色
                        unfocusedIndicatorColor = Color.LightGray,         // 未聚焦时下划线颜色
                        focusedTextColor = Color.LightGray,               // 聚焦时文本颜色
                        unfocusedTextColor = Color.LightGray,             // 未聚焦时文本颜色
                        cursorColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = {
                    userViewModel.login(onClose = onClose)
                }) {
                    Text(text = "登录", color = Color.White)
                }
                
            }

            TextButton(onClick = {}) {
                Text(text = "还没有账号，快点注册吧", color = Color.LightGray, fontSize = 12.sp)

            }

        }


    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}

