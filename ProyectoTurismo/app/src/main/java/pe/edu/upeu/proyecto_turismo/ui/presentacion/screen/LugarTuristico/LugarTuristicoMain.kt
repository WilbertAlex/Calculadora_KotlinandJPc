package pe.edu.upeu.proyecto_turismo.ui.presentation.screens.producto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pe.edu.upeu.proyecto_turismo.ui.navigacion.Destinations

@Composable
fun LugarTuristicoMain(
    navegarEditarAct: (String) -> Unit,
    navController: NavController,
    token: String,
    viewModel: LugarTuristicoMainViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getLugaresTuristicos(token)
    }

    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Destinations.LugarTuristicoFormSC.route)
                    }
                ) {
                    Text("+")
                }
            }
        ) { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(viewModel.lugaresTuristicos) { lugar ->
                    LugarTuristicoItem(
                        nombre = lugar.nombre,
                        descripcion = lugar.descripcion,
                        onClick = {
                            lugar.idLugarTuristico?.let { id ->
                                navegarEditarAct(id.toString())
                            }
                        }
                    )
                }
            }
        }
    }
}