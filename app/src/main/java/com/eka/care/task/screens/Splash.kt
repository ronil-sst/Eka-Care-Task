package com.eka.care.task.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.eka.care.task.R
import com.eka.care.task.navigation.Screens
import com.eka.care.task.ui.theme.AppColors
import com.eka.care.task.navigation.navigateTo


@Composable
fun SplashScreen(mainNavyController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = Unit, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )


//        mainNavyController.navigate("details_screen/hello my friend")

        mainNavyController.navigateTo(Screens.EntryList, finish = true)
//        mainNavyController.navigate(RonConstants.Routs.MainScreen) {
//            popUpTo(RonConstants.Routs.splashScreen) {
//                inclusive = false
//            }
//
//        }
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.backgroundColor), contentAlignment = Alignment.Center) {
        val value=scale.value
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo of the app",
            modifier = Modifier.scale(value)
        )
    }
}
