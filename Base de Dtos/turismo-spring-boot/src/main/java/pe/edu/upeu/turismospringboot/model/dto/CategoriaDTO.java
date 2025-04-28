package pe.edu.upeu.turismospringboot.model.dto;

import java.time.LocalDateTime;

public class CategoriaDTO {

    private Long idCategoria;
    private String nombre;
    private LocalDateTime fechaCreacionCategoria;
    private LocalDateTime fechaModificacionCategoria;

    // Getters y Setters
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacionCategoria() {
        return fechaCreacionCategoria;
    }

    public void setFechaCreacionCategoria(LocalDateTime fechaCreacionCategoria) {
        this.fechaCreacionCategoria = fechaCreacionCategoria;
    }

    public LocalDateTime getFechaModificacionCategoria() {
        return fechaModificacionCategoria;
    }

    public void setFechaModificacionCategoria(LocalDateTime fechaModificacionCategoria) {
        this.fechaModificacionCategoria = fechaModificacionCategoria;
    }
}