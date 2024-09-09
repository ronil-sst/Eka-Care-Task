package com.eka.care.task.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object MyAnimation {

    fun myEnterAnimation(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tween(700),
            initialOffsetX = {
                it
            }
        )

//        expandHorizontally(
//            animationSpec = tween(700),
//            expandFrom = Alignment.CenterHorizontally
//        )
    }


    fun myExitAnimation(): ExitTransition {
        return slideOutHorizontally(
            animationSpec = tween(700),
            targetOffsetX = {
                -it
            }
        )

//        return shrinkHorizontally(
//            animationSpec = tween(700),
//            shrinkTowards = Alignment.CenterHorizontally
//        )
    }


    fun mySlideIn(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tween(700),
            initialOffsetX = {
                it
            }
        )
    }

    fun mySlideOut(): ExitTransition {
        return slideOutHorizontally(
            animationSpec = tween(700),
            targetOffsetX = {
                -it
            }
        )

    }
}