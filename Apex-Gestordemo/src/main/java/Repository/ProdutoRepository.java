package Repository;

import Model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
}
