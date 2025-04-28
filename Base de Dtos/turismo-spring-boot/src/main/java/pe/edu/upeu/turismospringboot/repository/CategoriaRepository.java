package pe.edu.upeu.turismospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.turismospringboot.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
