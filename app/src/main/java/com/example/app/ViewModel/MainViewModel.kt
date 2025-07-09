package com.example.app.ViewModel


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.app.R
import com.example.app.model.entity.Category
import com.example.app.model.entity.SwiperEntity
import com.example.app.model.service.HomeService

class MainViewModel : ViewModel() {

    val homeService = HomeService.instance()

    var categoryLoaded by mutableStateOf(false)
        private set

    var categories by mutableStateOf(
        listOf(
            Category("思想政治1", "1"),
            Category("法律法规2", "2"),
            Category("职业道德3", "3"),
            Category("诚信自律4", "4")
        )
    )
        private set

    suspend fun categoryData() {
        val categoryRes = homeService.category()
        if (categoryRes.code == 0) {
            categories = categoryRes.data
            categoryLoaded = true
        } else {
            val message = categoryRes.message
        }
    }

    var categoryIndex by mutableStateOf(0)
        private set

    fun updatecategoryIndex(index: Int) {
        categoryIndex = index
    }

    val types =
        mutableStateListOf(
            "相关资讯" to Icons.Default.Description,
            "视频课程" to Icons.Default.SmartDisplay
        )


    var currentTypeIndex by mutableStateOf(0)
        private set

    var showArticleList by mutableStateOf(false)
        private set

    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
        showArticleList = currentTypeIndex == 0
    }

    // 轮番图数据
    var swiperData by mutableStateOf(
        listOf(
        SwiperEntity(R.drawable.banner1),
        SwiperEntity(R.drawable.banner2),
        SwiperEntity(R.drawable.banner3),
        SwiperEntity(R.drawable.banner4),
        SwiperEntity(R.drawable.banner5)
    ))
        private set


    suspend fun swiperData() {
        val swiperRes = homeService.banner()
        if (swiperRes.code == 0 && swiperRes.data != null) {
            swiperData = swiperRes.data
        } else {
            val message = swiperRes.message
        }
    }


    val notifications = listOf("人社部", "疫情布拉布拉", "安徽收入收入")

}
