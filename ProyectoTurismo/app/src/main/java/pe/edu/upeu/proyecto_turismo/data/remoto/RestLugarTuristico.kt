package pe.edu.upeu.proyecto_turismo.data.remoto

import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoDto
import pe.edu.upeu.proyecto_turismo.modelo.LugarTuristicoResp
import pe.edu.upeu.proyecto_turismo.modelo.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestLugarTuristico {

    @GET("${BASE_LUGAR_TURISTICO}")
    suspend fun reportarLugarTuristico(@Header("Authorization") token: String): Response<List<LugarTuristicoResp>>

    @GET("${BASE_LUGAR_TURISTICO}/{id}")
    suspend fun getLugarTuristicoId(@Header("Authorization") token: String, @Path("id") id: Long): Response<LugarTuristicoResp>

    @DELETE("${BASE_LUGAR_TURISTICO}/{id}")
    suspend fun deleteLugarTuristico(@Header("Authorization") token: String, @Path("id") id: Long): Response<Message>

    @PUT("${BASE_LUGAR_TURISTICO}/{id}")
    suspend fun actualizarLugarTuristico(@Header("Authorization") token: String, @Path("id") id: Long, @Body lugarTuristico: LugarTuristicoDto): Response<LugarTuristicoResp>

    @POST("${BASE_LUGAR_TURISTICO}")
    suspend fun insertarLugarTuristico(@Header("Authorization") token: String, @Body lugarTuristico: LugarTuristicoDto): Response<Message>

    companion object {
        const val BASE_LUGAR_TURISTICO = "/admin/lugarturistico"
    }
}