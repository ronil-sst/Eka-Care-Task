    package com.eka.care.task.navigation

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.StateFlow

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

    fun NavHostController.finish(){
        popBackStack()
    }


    fun <T> NavHostController.getLiveData(key: String, initialValue: T? = null): MutableLiveData<T>? {
        return if (initialValue != null) {
            currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key, initialValue)
        } else {
            currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
        }
    }

    fun <T> NavHostController.getData(key: String): T? {
        return currentBackStackEntry?.savedStateHandle?.get<T>(key)
    }

    fun <T> NavHostController.getStateFlow(key: String, initValue: T): StateFlow<T>? {
        return currentBackStackEntry?.savedStateHandle?.getStateFlow<T>(key, initValue)
    }


    fun NavHostController.getStringExtra(key: String): String? {
        return this.currentBackStackEntry?.arguments?.getString(key)
    }

    fun NavHostController.getFloatExtra(key: String): Float? {
        return this.currentBackStackEntry?.arguments?.getFloat(key)

    }

    fun NavHostController.getIntExtra(key: String): Int? {
        return this.currentBackStackEntry?.arguments?.getInt(key, 0)
    }

    fun NavHostController.getBooleanExtra(key: String): Boolean? {
        return this.currentBackStackEntry?.arguments?.getBoolean(key, false)
    }

    fun NavHostController.setResults(sendExtras: NavigationResult.() -> Unit) {
        val results = NavigationResult()
        results.apply(sendExtras)
        results.getExtras().forEach {
            previousBackStackEntry?.savedStateHandle?.set(it.key, it.value)
        }
    }

    class NavigationResult() {
        private val list = ArrayList<PutExtra>()
        fun putExtra(key: String, value: Any) {
            list.add(PutExtra(key, value))
        }

        fun getExtras() = list
    }

    data class PutExtra(val key: String, val value: Any?)
