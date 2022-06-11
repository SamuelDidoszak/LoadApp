package com.didoszak.loadapp.feature_add_find_job.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.didoszak.loadapp.feature_add_find_job.presentation.add_route_screen.AddRouteScreen
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen.JobOffersScreen
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.LoginRegisterScreen
import com.didoszak.loadapp.feature_add_find_job.presentation.util.Screen
import com.didoszak.loadapp.ui.theme.LoadAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginRegisterScreen.route
                    ) {
                        composable(route = Screen.LoginRegisterScreen.route) {
                            LoginRegisterScreen(navController = navController)
                        }
                        composable(route = Screen.JobOffersScreen.route) {
                            JobOffersScreen(navController = navController)
                        }
                        composable(route = Screen.AddRouteScreen.route) {
                            AddRouteScreen(navController = navController)
                        }
                }
            }
        }
    }
}
}