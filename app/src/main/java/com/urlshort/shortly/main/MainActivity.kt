package com.urlshort.shortly.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.urlshort.shortly.base.ui.navigation.NavigationComponent
import com.urlshort.shortly.base.ui.theme.ShortlyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortlyTheme {
                NavigationComponent(navController = rememberNavController())
            }
        }
    }
}