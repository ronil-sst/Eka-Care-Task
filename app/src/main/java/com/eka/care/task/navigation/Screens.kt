package com.eka.care.task.navigation
import kotlinx.serialization.Serializable


sealed class Screens {
    @Serializable
    data object Splash : Screens()

    @Serializable
    data object EntryList : Screens()

    @Serializable
    data object AdEntry : Screens()
}