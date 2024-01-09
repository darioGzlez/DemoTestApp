package com.dario.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dario.myapplication.presentation.NavGraphs
import com.dario.myapplication.ui.theme.DemoTestAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTestAppTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}