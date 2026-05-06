package com.example.myfisrtkkotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfisrtkkotlinapp.ui.theme.MyFisrtKkotlinAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFisrtKkotlinAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // 1. 定义状态：记住一个可变的布尔值
    var expanded by remember { mutableStateOf(false) }
    // 2. 根据状态计算额外的内边距
    val extraPadding = if (expanded) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f) // 占据剩余所有空间
                    .padding(bottom = extraPadding) // 应用动态内边距
            ) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded } // 3. 点击时切换状态
            ) {
                Text(if (expanded) "Show less" else "Show more") // 根据状态改变按钮文字
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFisrtKkotlinAppTheme {
        Greeting("Android")
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var showAiDemo by remember { mutableStateOf(false) }

    if (showAiDemo) {
        // 呈现 AiDemoScreen 内容，并传入返回逻辑
        AiDemoScreen(onBack = { showAiDemo = false })
    } else {
        val names = listOf("World", "Compose", "Kotlin")
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            // 先显示列表内容
            for (name in names) {
                Greeting(name = name)
            }
            
            // 使用 Spacer 占据剩余空间，将按钮推向底部
            Spacer(modifier = Modifier.weight(1f))

            // 将按钮移动到 Column 底部
            Button(
                onClick = { showAiDemo = true },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("打开 AI Demo 演示")
            }
        }
    }
}