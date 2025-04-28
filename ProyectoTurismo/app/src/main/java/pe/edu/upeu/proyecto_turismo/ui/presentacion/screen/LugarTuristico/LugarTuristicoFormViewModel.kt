package pe.edu.upeu.proyecto_turismo.ui.presentation.screen.lugarturistico

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upeu.proyecto_turismo.data.remoto.RestCategoria
import pe.edu.upeu.proyecto_turismo.data.remoto.RestLugarTuristico
import pe.edu.upeu.proyecto_turismo.data.remoto.RestUbicacion
import pe.edu.upeu.proyecto_turismo.modelo.Categoria
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoDto
import pe.edu.upeu.proyecto_turismo.modelo.Ubicacion
import pe.edu.upeu.proyecto_turismo.modelo.toDto
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue


import javax.inject.Inject
@HiltViewModel
class LugarTuristicoFormViewModel @Inject constructor(
    private val restLugarTuristico: RestLugarTuristico,
    private val restCategoria: RestCategoria,
    private val restUbicacion: RestUbicacion
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var lugarTuristico by mutableStateOf(LugarTuristicoDto())
    var categorias by mutableStateOf(emptyList<Categoria>())
    var ubicaciones by mutableStateOf(emptyList<Ubicacion>())

    fun getDatosPrevios(token: String) {
        viewModelScope.launch {
            isLoading = true
            categorias = restCategoria.reportarCategorias(token).body() ?: emptyList()
            ubicaciones = restUbicacion.reportarUbicaciones(token).body() ?: emptyList()
            isLoading = false
        }
    }

    fun getLugarTuristico(token: String, id: Long) {
        viewModelScope.launch {
            isLoading = true
            val response = restLugarTuristico.getLugarTuristicoId(token, id)
            response.body()?.let {
                lugarTuristico = it.toDto()
            }
            isLoading = false
        }
    }

    suspend fun addLugarTuristico(token: String): Boolean {
        val response = restLugarTuristico.insertarLugarTuristico(token, lugarTuristico)
        return response.isSuccessful
    }

    suspend fun editLugarTuristico(token: String, id: Long): Boolean {
        val response = restLugarTuristico.actualizarLugarTuristico(token, id, lugarTuristico)
        return response.isSuccessful
    }
}