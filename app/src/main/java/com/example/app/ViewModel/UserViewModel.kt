package com.example.app.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.service.UserInfoManager
import com.example.app.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class UserViewModel(context: Context): ViewModel() {

    private val userInfoManager = UserInfoManager(context)

    var userInfo: UserInfoEntity? = null
        private set

    init {
        // 可以使用dataStore对象存储去存储整个对象
        viewModelScope.launch {
            val userName = userInfoManager.userName.firstOrNull()
            userInfo = if (userName?.isNotEmpty() == true) {
                UserInfoEntity(userName)
            } else {
                null
            }


        }
    }

    // 是否登录
    val logged: Boolean
        get() {
            return userInfo != null
        }

    fun login(onClose: () -> Unit) {
        userInfo = UserInfoEntity("user001")
        viewModelScope.launch {
            userInfoManager.save("user001")
        }
        onClose()
    }

    fun clear() {
        viewModelScope.launch {
            userInfoManager.clear()
            userInfo = null
        }
    }

}