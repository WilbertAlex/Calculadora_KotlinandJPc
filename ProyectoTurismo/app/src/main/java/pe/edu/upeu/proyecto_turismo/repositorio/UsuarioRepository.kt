package pe.edu.upeu.proyecto_turismo.repositorio

import pe.edu.upeu.proyecto_turismo.data.remoto.RestUsuario
import pe.edu.upeu.proyecto_turismo.modelo.UsuarioDto
import pe.edu.upeu.proyecto_turismo.modelo.UsuarioResp
import retrofit2.Response
import javax.inject.Inject

interface UsuarioRepository {
    suspend fun loginUsuario(user: UsuarioDto):
            Response<UsuarioResp>
}
class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario): UsuarioRepository {
    override suspend fun loginUsuario(user: UsuarioDto): Response<UsuarioResp> {
        val response = restUsuario.login(user)
        if (response.isSuccessful) {
            return response
        } else {
            // Maneja el caso en que la respuesta no fue exitosa
            throw Exception("Error en la autenticación: ${response.message()}")
        }
    }
}