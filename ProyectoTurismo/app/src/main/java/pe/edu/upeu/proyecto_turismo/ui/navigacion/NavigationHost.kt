package pe.edu.upeu.proyecto_turismo.ui.navigacion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.edu.upeu.proyecto_turismo.ui.navigacion.Destinations.LugarTuristicoFormSC
import pe.edu.upeu.proyecto_turismo.ui.navigacion.Destinations.LugarTuristicoMainSC
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.Pantalla1
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.Pantalla2
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.Pantalla3
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.Pantalla4
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.Pantalla5

import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.login.LoginScreen
import pe.edu.upeu.proyecto_turismo.ui.presentation.screen.lugarturistico.LugarTuristicoForm
import pe.edu.upeu.proyecto_turismo.ui.presentation.screens.producto.LugarTuristicoMain


@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>,
    modif: PaddingValues
) {
    NavHost(
        navController = navController, startDestination =
            Destinations.Login.route
    ) {
        composable (Destinations.Login.route){
            LoginScreen(navigateToHome = {
                navController.navigate(Destinations.Pantalla1.route)})
        }
        composable(Destinations.Pantalla1.route) {
            Pantalla1  (navegarPantalla2 = { newText ->navController.navigate(Destinations.Pantalla2.createRoute(newText)) }
            )
        }
        composable( Destinations.Pantalla2.route, arguments =
            listOf(navArgument ("newText") { defaultValue = "Pantalla 2"
            })
        ) { navBackStackEntry ->
            var newText
                    =navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            Pantalla2(newText, darkMode)
        }
        composable(Destinations.Pantalla3.route) {
            Pantalla3()
        }
        composable(Destinations.Pantalla4.route) {
            Pantalla4()
        }
        composable(Destinations.Pantalla5.route) {
            Pantalla5()
        }
        composable(LugarTuristicoMainSC.route) {
            LugarTuristicoMain(
                navegarEditarAct = { lugarId ->
                    navController.navigate(LugarTuristicoFormSC.createRoute(lugarId))
                },
                navController = navController,
                token = "TOKEN_AQUÍ" // pasa tu token real
            )
        }

        composable(
            route = LugarTuristicoFormSC.route,
            arguments = listOf(navArgument("lugarId") {
                defaultValue = "lugarId"
            })
        ) { navBackStackEntry ->
            val lugarId = navBackStackEntry.arguments?.getString("lugarId")
            requireNotNull(lugarId)
            LugarTuristicoForm(
                lugarId = lugarId,
                darkMode = darkMode,
                navController = navController
            )
        }
    }
}
