package com.example.app.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app.R
import com.example.app.model.entity.VideoEntity

class VideoDetailModel : ViewModel() {
    val list = listOf(
        VideoEntity(
            title = "行测老师教你公考密集",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = R.drawable.banner1
        ),VideoEntity(
            title = "行测老师教你公考密集",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = R.drawable.banner2
        ),VideoEntity(
            title = "行测老师教你公考密集",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = R.drawable.banner3
        ),VideoEntity(
            title = "行测老师教你公考密集",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = R.drawable.banner4
        ),VideoEntity(
            title = "行测老师教你公考密集",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = R.drawable.banner5
        )
    )

    var videoTitle by mutableStateOf("我们一起抗击疫情")
        private set

    private val htmlHeader = """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body { font-family: sans-serif; padding: 16px; }
            img { max-width: 100%; height: auto; }
        </style>
    </head>
    <body>
    """.trimIndent()

    private val htmlFooter = """
       </body>
       </html>
    """.trimIndent()

    var content = """
        $htmlHeader
            <h1>人工智能助手突破性进展：DeepSeek Chat 发布多模态能力</h1>
    
    <div class="article-meta">
        <span>2023年11月15日</span> • 
        <span>科技频道</span> • 
        <span>记者：李明</span>
    </div>
    
    <p>国内人工智能公司深度求索（DeepSeek）今日正式发布其大语言模型 DeepSeek Chat 的多模态版本，这是该模型首次支持图像、语音和文本的联合理解能力。</p>
    
    <img src="https://picsum.photos/600/400" alt="DeepSeek Chat 多模态演示" onerror="this.style.display='none'">
    
    <h2>核心升级亮点</h2>
    
    <ul>
        <li><strong>视觉理解</strong>：可分析图片中的物体、场景和文字</li>
        <li><strong>语音交互</strong>：支持实时语音输入输出</li>
        <li><strong>上下文记忆</strong>：对话轮次提升至128K tokens</li>
    </ul>
    
    <p>深度求索CTO张伟在发布会上演示了模型解读医学影像的能力："这是AI首次在保持强大语言能力的同时，实现专业级的图像理解。"</p>
    
    <h2>行业影响</h2>
    
    <p>分析师王芳表示："多模态突破将改变教育、医疗和创意产业。预计到2024年，60%的企业AI系统将采用类似技术。"</p>
    
    <img src="https://picsum.photos/600/400" alt="AI市场增长预测" onerror="this.style.display='none'">
    
    <p>该模型即日起面向企业用户开放API，个人版App预计下月上线。</p>
    $htmlFooter
    """.trimIndent()

    var videoDesc by mutableStateOf(content)

}