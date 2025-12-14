package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class ProdutoVendaId implements Serializable {

    @Column(name = "produto_id")
    private Long produtoId;
    @Column(name = "venda_id")
    private Long vendaId;

    public ProdutoVendaId() {
    }

    public ProdutoVendaId(Long produtoId, Long vendaId) {
        this.produtoId = produtoId;
        this.vendaId = vendaId;
    }
}
