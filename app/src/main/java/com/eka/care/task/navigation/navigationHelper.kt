package com.eka.care.task.navigation

import androidx.navigation.NavHostController

fun <T : Screens> NavHostController.navigateTo(
    route: T,
    finish: Boolean = false,
    finishAll: Boolean = false

) {
    val current = currentDestination?.route ?: this.graph.startDestinationRoute

    navigate(route) {
//        launchSingleTop=true
        if (finishAll) {
            popUpTo(graph.id) {
                inclusive = true
                saveState = true
            }

        } else if (finish) {
            current?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true

                }
            }
        }

    }
}
