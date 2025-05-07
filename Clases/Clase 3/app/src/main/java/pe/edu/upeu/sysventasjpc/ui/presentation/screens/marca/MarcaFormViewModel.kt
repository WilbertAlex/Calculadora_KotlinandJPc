package pe.edu.upeu.sysventasjpc.ui.presentation.screens.marca

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import pe.edu.upeu.sysventasjpc.modelo.Marca
import pe.edu.upeu.sysventasjpc.repository.MarcaRepository
import javax.inject.Inject

@HiltViewModel
class MarcaFormViewModel @Inject constructor(
    private val marcRepo: MarcaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _marca = MutableStateFlow<Marca?>(null)
    val marca: StateFlow<Marca?> = _marca
    fun getMarca(idX: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _marca.value = marcRepo.getMarcaById(idX).first()
            _isLoading.value = false
        }
    }

    fun addMarca(mar: Marca){
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            Log.i("REAL", mar.toString())
            marcRepo.saveMarca(mar)
            _isLoading.value = false
        }
    }
    fun editMarca(mar: Marca){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            marcRepo.updateMarca(mar)
            _isLoading.value = false
        }
    }
}