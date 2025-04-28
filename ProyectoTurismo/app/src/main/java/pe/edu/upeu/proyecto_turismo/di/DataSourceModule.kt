package pe.edu.upeu.proyecto_turismo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pe.edu.upeu.proyecto_turismo.data.remoto.RestCategoria
import pe.edu.upeu.proyecto_turismo.data.remoto.RestLugarTuristico
import pe.edu.upeu.proyecto_turismo.data.remoto.RestUbicacion
import pe.edu.upeu.proyecto_turismo.data.remoto.RestUsuario
import pe.edu.upeu.proyecto_turismo.ui.utils.TokenUtils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    var retrofit: Retrofit?=null
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl()= TokenUtils.API_URL
    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl:String):
            Retrofit {
        val okHttpClient= OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        if (retrofit==null){
            retrofit= Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl).build()
        }
        return retrofit!!
    }
    @Singleton
    @Provides
    fun restUsuario(retrofit: Retrofit): RestUsuario{
        return retrofit.create(RestUsuario::class.java)
    }

    @Singleton
    @Provides
    fun restCategoria(retrofit: Retrofit): RestCategoria {
        return retrofit.create(RestCategoria::class.java)
    }



    @Singleton
    @Provides
    fun restLugarTuristico(retrofit: Retrofit): RestLugarTuristico {
        return retrofit.create(RestLugarTuristico::class.java)
    }

    @Singleton
    @Provides
    fun restUbicacion(retrofit: Retrofit): RestUbicacion {
        return retrofit.create(RestUbicacion::class.java)
    }
}
