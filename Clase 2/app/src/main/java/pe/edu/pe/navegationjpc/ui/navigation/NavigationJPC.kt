package pe.edu.pe.navegationjpc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.pe.navegationjpc.ui.presentation.screens.BarcodeScanningScreen
import pe.edu.pe.navegationjpc.ui.presentation.screens.CalcUPeU
import pe.edu.pe.navegationjpc.ui.presentation.screens.HomeScreen
import pe.edu.pe.navegationjpc.ui.presentation.screens.ProfileScreen
import pe.edu.pe.navegationjpc.ui.presentation.screens.SettingsScreen


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
        composable("calc") { CalcUPeU() }
        composable("barcode") { BarcodeScanningScreen(navController) }
    }
}