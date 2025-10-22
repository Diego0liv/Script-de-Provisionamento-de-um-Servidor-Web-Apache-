package Repository;

import Model.VendaPagamento;
import Model.VendaPagamentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaPagamentoRepository extends JpaRepository<VendaPagamento, VendaPagamentoId>{
    
}
