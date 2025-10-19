package Model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto_Venda")
@Data
public class ProdutoVenda {

    @EmbeddedId
    private ProdutoVendaId idProdutoVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @Column(nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "preco_total", insertable = false, updatable = false)
    private BigDecimal precoTotal;
}
