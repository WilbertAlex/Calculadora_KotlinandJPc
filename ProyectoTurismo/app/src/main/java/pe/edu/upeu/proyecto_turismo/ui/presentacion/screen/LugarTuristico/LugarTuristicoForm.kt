package pe.edu.upeu.proyecto_turismo.ui.presentation.screen.lugarturistico

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoDto
import pe.edu.upeu.proyecto_turismo.ui.navigacion.Destinations


import androidx.compose.runtime.setValue

@Composable
fun LugarTuristicoForm(
    navController: NavController,
    token: String,
    idLugar: Long? = null,
    viewModel: LugarTuristicoFormViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.getDatosPrevios(token)
        idLugar?.let { viewModel.getLugarTuristico(token, it) }
    }

    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else {
        formularioLugarTuristico(viewModel) { lugarTuristico ->
            scope.launch {
                val result = if (idLugar == null) {
                    viewModel.addLugarTuristico(token)
                } else {
                    viewModel.editLugarTuristico(token, idLugar)
                }

                if (result) {
                    navController.navigate(Destinations.LugarTuristicoMainSC.route) {
                        popUpTo(Destinations.LugarTuristicoMainSC.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

@Composable
private fun formularioLugarTuristico(
    viewModel: LugarTuristicoFormViewModel,
    onSubmit: (LugarTuristicoDto) -> Unit
) {
    val lugarTuristico = viewModel.lugarTuristico

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = lugarTuristico.nombre,
            onValueChange = { viewModel.lugarTuristico = lugarTuristico.copy(nombre = it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lugarTuristico.descripcion,
            onValueChange = { viewModel.lugarTuristico = lugarTuristico.copy(descripcion = it) },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lugarTuristico.imagenUrl,
            onValueChange = { viewModel.lugarTuristico = lugarTuristico.copy(imagenUrl = it) },
            label = { Text("Imagen URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ComboBox de Categorías
        var expandedCategoria by remember { mutableStateOf(false) }
        var selectedCategoria by remember { mutableStateOf(viewModel.categorias.find { it.idCategoria == lugarTuristico.idCategoria }?.nombreCategoria ?: "") }

        ExposedDropdownMenuBox (expanded = expandedCategoria, onExpandedChange = { expandedCategoria = !expandedCategoria }) {
            OutlinedTextField(
                value = selectedCategoria,
                onValueChange = {},
                label = { Text("Categoría") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedCategoria,
                onDismissRequest = { expandedCategoria = false }
            ) {
                viewModel.categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria.nombreCategoria) },
                        onClick = {
                            selectedCategoria = categoria.nombreCategoria
                            viewModel.lugarTuristico = lugarTuristico.copy(idCategoria = categoria.idCategoria)
                            expandedCategoria = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ComboBox de Ubicaciones
        var expandedUbicacion by remember { mutableStateOf(false) }
        var selectedUbicacion by remember { mutableStateOf(viewModel.ubicaciones.find { it.idUbicacion.toString() == lugarTuristico.ubicacion }?.ciudad ?: "") }

        ExposedDropdownMenuBox(expanded = expandedUbicacion, onExpandedChange = { expandedUbicacion = !expandedUbicacion }) {
            OutlinedTextField(
                value = selectedUbicacion,
                onValueChange = {},
                label = { Text("Ubicación") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedUbicacion,
                onDismissRequest = { expandedUbicacion = false }
            ) {
                viewModel.ubicaciones.forEach { ubicacion ->
                    DropdownMenuItem(
                        text = { Text(ubicacion.ciudad) },
                        onClick = {
                            selectedUbicacion = ubicacion.ciudad
                            viewModel.lugarTuristico = lugarTuristico.copy(ubicacion = ubicacion.idUbicacion.toString())
                            expandedUbicacion = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = { onSubmit(viewModel.lugarTuristico) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}