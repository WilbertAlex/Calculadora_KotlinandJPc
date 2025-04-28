package pe.edu.pe.navegationjpc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.edu.pe.navegationjpc.ui.presentation.screens.*
import pe.edu.pe.navegationjpc.ui.presentation.screens.DetalleScreen
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
        composable("calc") { CalcUPeU() }
        composable("barcode") { BarcodeScanningScreen(navController) }
        composable("venta") { VentaScreen(navController) }
        composable(
            route = "detalle/{nombre}/{precio}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("precio") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            val precio = backStackEntry.arguments?.getString("precio")
            DetalleScreen(
                navController = navController,
                nombre = nombre,
                precio = precio
            )
        }
        //composable("confirmacion") { ConfirmacionScreen(navController) }
    }
}