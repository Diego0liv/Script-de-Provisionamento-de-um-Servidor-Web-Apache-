package Repository;

import Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    // É só isso! O Spring Data JPA cria todos os métodos para você.
    
}
