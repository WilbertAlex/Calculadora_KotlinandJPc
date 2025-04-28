package pe.edu.upeu.turismospringboot.mappers;

import org.springframework.stereotype.Component; // Importar la anotación Component
import pe.edu.upeu.turismospringboot.model.dto.CategoriaDTO;
import pe.edu.upeu.turismospringboot.model.entity.Categoria;
import java.util.List;
import java.util.stream.Collectors;

@Component // Añadir la anotación Component
public class CategoriaMapper {

    // Método para convertir de Categoria a CategoriaDTO
    public CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(categoria.getIdCategoria());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setFechaCreacionCategoria(categoria.getFechaCreacionCategoria());
        categoriaDTO.setFechaModificacionCategoria(categoria.getFechaModificacionCategoria());
        return categoriaDTO;
    }

    // Método para convertir de CategoriaDTO a Categoria
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDTO.getIdCategoria());
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setFechaCreacionCategoria(categoriaDTO.getFechaCreacionCategoria());
        categoria.setFechaModificacionCategoria(categoriaDTO.getFechaModificacionCategoria());
        return categoria;
    }

    // Método para convertir de lista de Categoria a lista de CategoriaDTO
    public List<CategoriaDTO> toDTOs(List<Categoria> categorias) {
        return categorias.stream()
                         .map(this::toDTO)
                         .collect(Collectors.toList());
    }
}
