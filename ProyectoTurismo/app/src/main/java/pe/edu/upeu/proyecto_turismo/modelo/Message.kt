package pe.edu.upeu.proyecto_turismo.modelo

data class Message(
    val statusCode: Long,
    val datetime: String,
    val message: String,
    val details: String
)
