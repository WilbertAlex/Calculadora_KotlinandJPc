package pe.edu.upeu.proyecto_turismo.repositorio

import pe.edu.upeu.proyecto_turismo.data.remoto.RestCategoria
import pe.edu.upeu.proyecto_turismo.modelo.Categoria
import pe.edu.upeu.proyecto_turismo.ui.utils.TokenUtils
import javax.inject.Inject

interface CategoriaRepository {
    suspend fun findAll(): List<Categoria>
}
class CategoriaRepositoryImp @Inject constructor(
    private val rest: RestCategoria,
): CategoriaRepository{
    override suspend fun findAll(): List<Categoria> {
        val response =
            rest.reportarCategorias(TokenUtils.TOKEN_CONTENT)
        return if (response.isSuccessful) response.body() ?: emptyList()
        else emptyList()
    }
}
