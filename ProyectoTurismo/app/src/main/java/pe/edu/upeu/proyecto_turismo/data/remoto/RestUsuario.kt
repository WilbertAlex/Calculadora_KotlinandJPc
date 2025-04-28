package pe.edu.upeu.proyecto_turismo.data.remoto

import pe.edu.upeu.proyecto_turismo.modelo.UsuarioDto
import pe.edu.upeu.proyecto_turismo.modelo.UsuarioResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RestUsuario {
    @POST("/auth/login")
    suspend fun login(@Body user: UsuarioDto):
            Response<UsuarioResp>
}
