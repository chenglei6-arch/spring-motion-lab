/*
 * 负责人：程磊
 * TODO 1：增加参数输入和开始、暂停、复位按钮。按接口文档应用参数并显示校验错误。
 * TODO 2：用 withFrameNanos 与时间累加器驱动 Java tick()，按固定模拟步长补齐时间。
 *         暂停或复位时清除累加器，限制单帧补步并在 SETTLED 时结束循环。
 * TODO 3：用最新快照更新状态与弹簧视图，按模拟时间采样并更新位移曲线。
 */
package io.github.chenglei6arch.springmotionlab.desktop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.chenglei6arch.springmotionlab.simulation.SimulationController
import io.github.chenglei6arch.springmotionlab.simulation.SpringParameters

@Composable
fun SpringLabScreen() {
    val controller = remember { SimulationController(SpringParameters.defaults()) }
    val sample = controller.snapshot().sample()

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("弹簧运动实验室", style = MaterialTheme.typography.headlineSmall)
                SpringCanvas(sample, Modifier.fillMaxWidth().height(160.dp))
                DisplacementChart(listOf(sample), Modifier.fillMaxWidth().height(100.dp))
            }
        }
    }
}
