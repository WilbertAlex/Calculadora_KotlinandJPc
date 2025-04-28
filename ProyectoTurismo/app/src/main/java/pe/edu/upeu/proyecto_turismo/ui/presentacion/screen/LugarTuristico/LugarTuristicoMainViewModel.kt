package pe.edu.upeu.proyecto_turismo.ui.presentation.screens.producto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoDto
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoResp
import pe.edu.upeu.proyecto_turismo.repositorio.LugarTuristicoRepository

import javax.inject.Inject

@HiltViewModel
class LugarTuristicoMainViewModel @Inject constructor(
    private val lugarRepo: LugarTuristicoRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _deleteSuccess = MutableStateFlow<Boolean?>(null)
    val deleteSuccess: StateFlow<Boolean?> get() = _deleteSuccess

    private val _lugares = MutableStateFlow<List<LugarTuristicoResp>>(emptyList())
    val lugares: StateFlow<List<LugarTuristicoResp>> = _lugares

    fun cargarLugares() {
        viewModelScope.launch {
            _isLoading.value = true
            _lugares.value = lugarRepo.reportarLugares()
            _isLoading.value = false
        }
    }

    fun buscarPorId(id: Long): Flow<LugarTuristicoResp> = flow {
        emit(lugarRepo.buscarLugarId(id))
    }

    fun eliminar(lugar: LugarTuristicoDto) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val success = lugarRepo.deleteLugarTuristico(lugar)
            if (success) {
                cargarLugares()
                _deleteSuccess.value = success
            } else {
                _deleteSuccess.value = false
            }
        } catch (e: Exception) {
            Log.e("LugarTuristicoVM", "Error al eliminar lugar turístico", e)
            _deleteSuccess.value = false
        }
    }

    fun clearDeleteResult() {
        _deleteSuccess.value = null
    }
}