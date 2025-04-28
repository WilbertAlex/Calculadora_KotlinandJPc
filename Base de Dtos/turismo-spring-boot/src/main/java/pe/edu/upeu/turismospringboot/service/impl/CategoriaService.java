package pe.edu.upeu.turismospringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.turismospringboot.exception.CategoriaNotFoundException;
import pe.edu.upeu.turismospringboot.model.dto.CategoriaDTO;
import pe.edu.upeu.turismospringboot.model.entity.Categoria;
import pe.edu.upeu.turismospringboot.repository.CategoriaRepository;
import pe.edu.upeu.turismospringboot.service.ICategoriaService;
import pe.edu.upeu.turismospringboot.mappers.CategoriaMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final CategoriaMapper categoriaMapper = new CategoriaMapper();  // Instanciamos el mapper

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría con id: " + id));
    }

    @Override
    public Categoria save(CategoriaDTO categoriaDTO) {
        // Usamos el mapper para convertir DTO a entidad
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria.setFechaCreacionCategoria(LocalDateTime.now());  // Se agrega la fecha de creación
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría con id: " + id));

        // Usamos el mapper para convertir DTO a entidad
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setFechaModificacionCategoria(LocalDateTime.now());  // Se actualiza la fecha de modificación

        return categoriaRepository.save(categoria);
    }

    @Override
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría con id: " + id));
        categoriaRepository.deleteById(id);
    }
}