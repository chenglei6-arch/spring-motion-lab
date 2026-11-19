/*
 * 负责人：程磊
 * TODO 1：用 Compose Canvas 画固定端、弹簧折线、重物和平衡位置参考线。
 * TODO 2：将相对平衡点的位移映射到画布坐标。向下为正，重物与弹簧末端对齐。
 * 验收：用正位移、零位移、负位移样本预览，三种位置关系清楚。
 */
package io.github.chenglei6arch.springmotionlab.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.chenglei6arch.springmotionlab.simulation.model.MotionSample

@Composable
fun SpringCanvas(sample: MotionSample, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text("初始位移 ${sample.displacementMeters()} m")
    }
}
