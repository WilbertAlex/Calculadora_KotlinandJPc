package pe.edu.upeu.sysventasjpc.ui.presentation.screens.marca

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pe.edu.upeu.sysventasjpc.modelo.Marca
import pe.edu.upeu.sysventasjpc.repository.MarcaRepository
import javax.inject.Inject

@HiltViewModel
class MarcaMainViewModel @Inject constructor(
    private val marcRepo: MarcaRepository,
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _deleteSuccess =
        MutableStateFlow<Boolean?>(null)
    val deleteSuccess: StateFlow<Boolean?> get() =
        _deleteSuccess
    private val _marcs =
        MutableStateFlow<List<Marca>>(emptyList())
    val marcs: StateFlow<List<Marca>> = _marcs
    fun cargarMarcas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _marcs.value = marcRepo.findAllR().first()
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun buscarPorId(id: Long): Flow<Marca> = flow {
        marcRepo.getMarcaById(id).first()
    }
    fun eliminar(marca: Marca) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val success = marcRepo.deleteMarca(marca)
            if (success) {
                cargarMarcas()
                _deleteSuccess.value = success
            }else{ _deleteSuccess.value = false }
        } catch (e: Exception) {
            Log.e("MarcaVM", "Error al eliminar Marca", e)
            _deleteSuccess.value = false
        }
    }
    fun clearDeleteResult() {
        _deleteSuccess.value = null
    }
}
