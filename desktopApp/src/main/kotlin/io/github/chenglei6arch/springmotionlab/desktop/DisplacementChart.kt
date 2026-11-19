/*
 * 负责人：程磊
 * TODO 1：在基本动画跑通后，根据 List<MotionSample> 画位移–时间曲线。
 * TODO 2：横轴用模拟时间，纵轴用位移，标注 s 和 m；允许空列表和单个采样点。
 * TODO 3：比较两组阻尼时使用相同坐标尺度；可先保存两次截图完成对照实验。
 */
package io.github.chenglei6arch.springmotionlab.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.chenglei6arch.springmotionlab.simulation.MotionSample

@Composable
fun DisplacementChart(samples: List<MotionSample>, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text("位移曲线 · ${samples.size} 个采样点")
    }
}
