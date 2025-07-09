package com.example.app.compositionLocal

import androidx.compose.runtime.compositionLocalOf
import com.example.app.ViewModel.UserViewModel

val LocalUserViewModel = compositionLocalOf<UserViewModel> { error("User View Model Context Not Found") }
