package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Item_Nota_Fiscal")
@Data
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_nf")
    private Long idItemNf;
    @ManyToOne
    @JoinColumn(name = "nf_entrada_id", nullable = false)
    private NotaFiscalEntrada notaFiscal;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(name = "valor_custo_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCustoUnitario;
}
