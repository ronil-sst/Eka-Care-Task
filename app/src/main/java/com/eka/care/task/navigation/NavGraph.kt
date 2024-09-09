package com.eka.care.task.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eka.care.task.screens.AddEntry
import com.eka.care.task.screens.EntryList
import com.eka.care.task.screens.SplashScreen
import com.eka.care.task.utils.MyAnimation

@Composable
fun SetupNavGraph(navController: NavHostController) {
    var showDialogue by remember {
        mutableStateOf(false)
    }
    if (showDialogue) {
        ProgressDialogue()
    }
    NavHost(
        navController = navController,
        startDestination = Screens.Splash,
        enterTransition = { MyAnimation.myEnterAnimation() },
        exitTransition = { MyAnimation.myExitAnimation() },
        popEnterTransition = { MyAnimation.myEnterAnimation() },
        popExitTransition = { MyAnimation.myExitAnimation() },
    ) {
        composable<Screens.Splash> {
            SplashScreen {
                navController.navigateTo(Screens.EntryList, finish = true)
            }
        }
        composable<Screens.AdEntry> {
            AddEntry(showProgress = {
                showDialogue = it
            }) {
                navController.setResults {
                    putExtra("needRefresh", true)
                }
                navController.finish()
            }
        }
        composable<Screens.EntryList> {
            EntryList(
                showProgress = {
                    showDialogue = it
                },
                navController
            )
        }

    }

}
