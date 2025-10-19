package Repository;

import Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRopository extends JpaRepository<Categoria, Long>{
    
}
