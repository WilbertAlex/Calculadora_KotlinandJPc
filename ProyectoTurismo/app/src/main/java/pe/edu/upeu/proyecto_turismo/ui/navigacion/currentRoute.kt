package pe.edu.upeu.proyecto_turismo.ui.navigacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun currentRoute(navController: NavHostController): String? {
    // 'by' accede al valor actual de State<NavBackStackEntry?> sin necesidad de hacer .value
    val navBackStackEntry = navController.currentBackStackEntryAsState().value

    // Regresa la ruta actual de la navegación, si está disponible
    return navBackStackEntry?.destination?.route
}