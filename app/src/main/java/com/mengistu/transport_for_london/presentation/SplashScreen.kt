package com.mengistu.transport_for_london.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.mengistu.transposrtforlondon.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.3f , animationSpec = tween(
            durationMillis = 500,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)

            }
        ))

        delay(3000L)
        navController.navigate("main_controller")
    }


    Box(modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.tfl_logo), contentDescription = "Logo" )
    }
}
