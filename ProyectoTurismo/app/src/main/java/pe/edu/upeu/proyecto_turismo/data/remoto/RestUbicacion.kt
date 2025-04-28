package pe.edu.upeu.proyecto_turismo.data.remoto

import pe.edu.upeu.proyecto_turismo.modelo.Ubicacion
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RestUbicacion {
    companion object {
        const val BASE_RUTA = "/admin/ubicacion"
    }

    @GET("${BASE_RUTA}")
    suspend fun reportarUbicaciones(
        @Header("Authorization")
        token: String
    ): Response<List<Ubicacion>>
}