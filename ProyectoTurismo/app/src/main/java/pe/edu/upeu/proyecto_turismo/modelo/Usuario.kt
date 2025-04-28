package pe.edu.upeu.proyecto_turismo.modelo

data class UsuarioDto(
    var username: String,
    var password: String,
)
data class UsuarioResp(
    val token: String,
)