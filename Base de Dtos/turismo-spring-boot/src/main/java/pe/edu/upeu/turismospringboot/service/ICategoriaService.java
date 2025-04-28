package pe.edu.upeu.turismospringboot.service;

import pe.edu.upeu.turismospringboot.model.dto.CategoriaDTO;
import pe.edu.upeu.turismospringboot.model.entity.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria save(CategoriaDTO categoriaDTO);  // Cambiado para aceptar CategoriaDTO
    Categoria update(Long id, CategoriaDTO categoriaDTO);  // Cambiado para aceptar CategoriaDTO
    void delete(Long id);
}