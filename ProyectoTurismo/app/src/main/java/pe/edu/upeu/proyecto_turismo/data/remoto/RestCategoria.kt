package pe.edu.upeu.proyecto_turismo.data.remoto

import pe.edu.upeu.proyecto_turismo.modelo.Categoria
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RestCategoria {
    companion object {
        const val BASE_RUTA = "/admin/categoria"
    }

    @GET("${BASE_RUTA}")
    suspend fun reportarCategorias(
        @Header("Authorization")
        token: String
    ): Response<List<Categoria>>
}
