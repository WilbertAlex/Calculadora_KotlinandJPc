package pe.edu.upeu.proyecto_turismo.modelo

data class LugarTuristicoDto(
    val idLugar: Long = 0,
    val descripcion: String = "",
    val fechaCreacionLugarTuristico: String = "",
    val fechaModificacionLugarTuristico: String = "",
    val imagenUrl: String = "",
    val nombre: String = "",
    val ubicacion: String = "",
    val idCategoria: Long = 0
)

data class LugarTuristicoResp(
    val idLugar: Long,
    val descripcion: String,
    val fechaCreacionLugarTuristico: String,
    val fechaModificacionLugarTuristico: String,
    val imagenUrl: String,
    val nombre: String,
    val ubicacion: Ubicacion,
    val categoria: Categoria
)

fun LugarTuristicoResp.toDto(): LugarTuristicoDto {
    return LugarTuristicoDto(
        idLugar = this.idLugar,
        descripcion = this.descripcion,
        fechaCreacionLugarTuristico = this.fechaCreacionLugarTuristico,
        fechaModificacionLugarTuristico = this.fechaModificacionLugarTuristico,
        imagenUrl = this.imagenUrl,
        nombre = this.nombre,
        ubicacion = this.ubicacion.toString(),
        idCategoria = this.categoria.idCategoria
    )
}


