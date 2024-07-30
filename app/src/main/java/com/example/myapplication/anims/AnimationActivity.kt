package com.example.myapplication.anims

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class AnimationActivity : androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isBig by mutableStateOf(false)
        setContent {
            val size by animateDpAsState(if (isBig) 200.dp else 100.dp, label = "")
            Column(
                Modifier
                    .size(size)
                    .background(Color.Green)
                    .clickable {
                        isBig = !isBig
                    }) {}
        }
    }
}