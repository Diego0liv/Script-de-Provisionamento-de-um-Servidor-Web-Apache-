package Repository;

import Model.ProdutoVenda;
import Model.ProdutoVendaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, ProdutoVendaId> {

}
