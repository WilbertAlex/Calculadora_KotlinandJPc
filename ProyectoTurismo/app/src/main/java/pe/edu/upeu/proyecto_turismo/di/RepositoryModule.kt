package pe.edu.upeu.proyecto_turismo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.proyecto_turismo.repositorio.CategoriaRepository
import pe.edu.upeu.proyecto_turismo.repositorio.CategoriaRepositoryImp
import pe.edu.upeu.proyecto_turismo.repositorio.LugarTuristicoRepository
import pe.edu.upeu.proyecto_turismo.repositorio.LugarTuristicoRepositoryImp
import pe.edu.upeu.proyecto_turismo.repositorio.UbicacionRepository
import pe.edu.upeu.proyecto_turismo.repositorio.UbicacionRepositoryImp
import pe.edu.upeu.proyecto_turismo.repositorio.UsuarioRepository
import pe.edu.upeu.proyecto_turismo.repositorio.UsuarioRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun
            userRepository(userRepos: UsuarioRepositoryImp): UsuarioRepository
    @Binds
    @Singleton
    abstract fun LugarTuristicoRepository(
        lugarTuristicoRepositoryImp: LugarTuristicoRepositoryImp
    ): LugarTuristicoRepository

    @Binds
    @Singleton
    abstract fun CategoriaRepository(
        categoriaRepositoryImp: CategoriaRepositoryImp
    ): CategoriaRepository

    @Binds
    @Singleton
    abstract fun UbicacionRepository(
        ubicacionRepositoryImp: UbicacionRepositoryImp
    ): UbicacionRepository

}