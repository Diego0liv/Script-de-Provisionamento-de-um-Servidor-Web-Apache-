package Model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Data
public class ProdutoVendaId implements Serializable{
    
    @Column(name = "produto_id")
    private Long idProduto; 
    
    @Column(name = "venda_id")
    private Long idVenda;
}
