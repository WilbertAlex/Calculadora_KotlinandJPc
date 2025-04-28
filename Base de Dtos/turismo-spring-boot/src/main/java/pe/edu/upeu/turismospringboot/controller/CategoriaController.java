package pe.edu.upeu.turismospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.turismospringboot.model.dto.CategoriaDTO;
import pe.edu.upeu.turismospringboot.model.entity.Categoria;
import pe.edu.upeu.turismospringboot.service.ICategoriaService;
import pe.edu.upeu.turismospringboot.mappers.CategoriaMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = categoriaMapper.toDTOs(categoriaService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Long id) {
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok(categoriaMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoriaDTO dto) {
        // No necesitas convertir el DTO en la entidad aquí. El servicio lo hace.
        Categoria obj = categoriaService.save(dto);  // Pasa el DTO directamente
        URI location = URI.create("/categorias/" + obj.getIdCategoria());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable("id") Long id, @RequestBody CategoriaDTO dto) {
        dto.setIdCategoria(id);  // Se asegura de que el ID del DTO coincida con el ID de la URL
        // No necesitas convertir el DTO en la entidad aquí. El servicio lo hace.
        Categoria obj = categoriaService.update(id, dto);  // Pasa el DTO directamente
        return ResponseEntity.ok(categoriaMapper.toDTO(obj));  // Convertir la entidad de vuelta a DTO para la respuesta
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
