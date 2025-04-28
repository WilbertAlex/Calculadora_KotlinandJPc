package pe.edu.upeu.proyecto_turismo.repositorio

import pe.edu.upeu.proyecto_turismo.data.remoto.RestLugarTuristico
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoDto
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoResp
import pe.edu.upeu.proyecto_turismo.modelo.Message
import pe.edu.upeu.proyecto_turismo.ui.utils.TokenUtils
import javax.inject.Inject

interface LugarTuristicoRepository {
    suspend fun findAll(): List<LugarTuristicoResp>
    suspend fun findById(id: Long): LugarTuristicoResp?
    suspend fun deleteById(id: Long): Message?
    suspend fun updateLugarTuristico(id: Long, lugarTuristicoDto: LugarTuristicoDto): LugarTuristicoResp?
    suspend fun createLugarTuristico(lugarTuristicoDto: LugarTuristicoDto): Message?
}

class LugarTuristicoRepositoryImp @Inject constructor(
    private val rest: RestLugarTuristico
): LugarTuristicoRepository {
    override suspend fun findAll(): List<LugarTuristicoResp> {
        val response = rest.reportarLugarTuristico(TokenUtils.TOKEN_CONTENT)
        return if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }

    override suspend fun findById(id: Long): LugarTuristicoResp? {
        val response = rest.getLugarTuristicoId(TokenUtils.TOKEN_CONTENT, id)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun deleteById(id: Long): Message? {
        val response = rest.deleteLugarTuristico(TokenUtils.TOKEN_CONTENT, id)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun updateLugarTuristico(id: Long, lugarTuristicoDto: LugarTuristicoDto): LugarTuristicoResp? {
        val response = rest.actualizarLugarTuristico(TokenUtils.TOKEN_CONTENT, id, lugarTuristicoDto)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun createLugarTuristico(lugarTuristicoDto: LugarTuristicoDto): Message? {
        val response = rest.insertarLugarTuristico(TokenUtils.TOKEN_CONTENT, lugarTuristicoDto)
        return if (response.isSuccessful) response.body() else null
    }
}
