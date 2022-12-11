package com.urlshort.shortly.base.ui.navigation

object NavigationDirections {

    val home = object: NavigationItem {
        override val destination: String by lazy { HOME }
    }

    private const val HOME = "home"
}