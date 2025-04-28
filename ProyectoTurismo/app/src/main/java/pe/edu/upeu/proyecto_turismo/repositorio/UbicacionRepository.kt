package pe.edu.upeu.proyecto_turismo.repositorio

import pe.edu.upeu.proyecto_turismo.data.remoto.RestUbicacion
import pe.edu.upeu.proyecto_turismo.modelo.Ubicacion
import pe.edu.upeu.proyecto_turismo.ui.utils.TokenUtils
import javax.inject.Inject

interface UbicacionRepository {
    suspend fun findAll(): List<Ubicacion>
}

class UbicacionRepositoryImp @Inject constructor(
    private val rest: RestUbicacion
): UbicacionRepository {
    override suspend fun findAll(): List<Ubicacion> {
        val response = rest.reportarUbicaciones(TokenUtils.TOKEN_CONTENT)
        return if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }
}