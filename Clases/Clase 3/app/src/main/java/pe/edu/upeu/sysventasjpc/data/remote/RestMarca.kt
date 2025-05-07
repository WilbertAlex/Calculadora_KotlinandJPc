package pe.edu.upeu.sysventasjpc.data.remote

import pe.edu.upeu.sysventasjpc.modelo.Marca
import pe.edu.upeu.sysventasjpc.modelo.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestMarca{
    companion object {
        const val BASE_RUTA = "/marcas"
    }
    @GET("${BASE_RUTA}")
    suspend fun reportarMarcas(@Header("Authorization")
                               token:String): Response<List<Marca>>

    @GET("${BASE_RUTA}/{id}")
    suspend fun getMarcasId(@Header("Authorization") token:String,
                            @Path("id") id:Long): Response<Marca>
    @DELETE("${BASE_RUTA}/{id}")
    suspend fun deleteMarca(@Header("Authorization") token:String,
                            @Path("id") id:Long): Response<Message>
    @PUT("${BASE_RUTA}/{id}")
    suspend fun actualizarMarca(@Header("Authorization") token:String,
                                @Path("id") id:Long, @Body marca: Marca): Response<Marca>
    @POST("${BASE_RUTA}")
    suspend fun insertarMarca(@Header("Authorization") token:String,
                              @Body marca:Marca): Response<Message>
}