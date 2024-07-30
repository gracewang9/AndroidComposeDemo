package com.example.myapplication.anims

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * 动画 Animatable
 */
class AnimatableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var big by mutableStateOf(false)
        setContent {

            val size = remember(big) { if (big) 96.dp else 48.dp }
            val offsetSize by remember {
                mutableStateOf(if(big)400.dp else 100.dp)
            }
//            val anim = remember {
//                Animatable(size, Dp.VectorConverter)
//            }
            val anim = remember {
                Animatable(0.dp, Dp.VectorConverter)
            }

            val density = exponentialDecay<Dp>()
//            val density = rememberSplineBasedDecay<Dp>()
            LaunchedEffect(key1 = big) {
                delay(1000)
//                anim.snapTo(450.dp)
//                anim.animateTo(size, spring(Spring.DampingRatioMediumBouncy))
//                anim.animateDecay(size, spring(0.dp,Spring.DampingRatioNoBouncy))
//                anim.animateTo(offsetSize, tween(easing = LinearEasing))
//                anim.animateTo(offsetSize, tween(easing = LinearOutSlowInEasing))
//                anim.animateTo(offsetSize, tween(easing = FastOutSlowInEasing))
//                anim.animateTo(offsetSize, tween(easing = FastOutLinearInEasing))
//                anim.animateTo(48.dp, spring(0.1f,Spring.DampingRatioMediumBouncy),2000.dp)
//                anim.animateTo(offsetSize, tween(easing = CubicBezierEasing(0.1f,4f,1f,1f)))
                anim.animateDecay(1000.dp,density)
            }

//            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .padding(0.dp, anim.value, 0.dp, 0.dp)
//                    .offset(x = 100.dp, y =anim.value)
                    .background(Color.Green)
                    .size(100.dp)
                    .clickable {
                        big = !big
                    })
//            }
        }
    }
}