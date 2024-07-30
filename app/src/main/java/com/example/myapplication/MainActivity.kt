package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var name by mutableStateOf("Android")

        val user = User("Android")
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        println("")
                        Button(onClick = {
                            user.name = ("Compose")
                        }) {
                            Text(text = "Click")
                        }
                        Greeting(
                            user,
                            modifier = Modifier.padding(innerPadding)
                        )
                        TestDerivedStateOf()
                        var name by remember {
                            mutableStateOf("wanghong")
                        }
                        ProcessedName(name = name) {
                            name = "grace"
                        }

                        TestLocal()
                    }
                }
            }
        }
    }
}

@Composable
fun TestLocal() {
    CompositionLocalProvider(value = LocalBackground provides Green) {
        TextWithBackground()
    }
}

@Composable
fun TextWithBackground() {
    Surface(color = LocalBackground.current) {
        Text(text = "有背景的文字！")
    }
    val name by remember { mutableStateOf("") }
}

//当作变量用
val LocalName = compositionLocalOf<String> { error("LocalName 没提供") }

//获取本地上下文
val LocalActivity = compositionLocalOf<Activity> { error("LocalActivity 没提供") }
val LocalBackground = compositionLocalOf<Color> { error("LocalBackground 没提供") }


@Composable
fun ProcessedName(name: String, onClick: () -> Unit) {
//    val processedName by remember(name) {
//        derivedStateOf  { name.uppercase() }
//    }
    val processedName = remember(name) {
        name.uppercase()
    }
    Text(text = processedName, Modifier.clickable(onClick = onClick))
}

@Composable
private fun TestDerivedStateOf() {
    var name by remember {
        mutableStateOf("wanghong")
    }
    var i = 0
    val processName by remember {
        derivedStateOf { name + " ${i++}" }
    }
    Text(text = processName, Modifier.clickable {
        name = "gracewang"
    })
}

@Composable
fun Greeting(name: User, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${name.name}!",
        modifier = modifier
    )
}

@Composable
fun TodoList(highPriorityKeywords: List<String> = listOf("Review", "Unblock", "Compose")) {
    val todoTasks = remember {
        mutableStateListOf<String>()
    }
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf { todoTasks.filter { it.contains(highPriorityKeywords[0]) } }
    }
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            item(highPriorityTasks) { }
            items(todoTasks) {}
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting(user = User("Android"))
//    }
//}

//data class User(var name: String)

@Stable
data class User(var name: String)

//class User(name: String) {
//    var name by mutableStateOf(name)
//}