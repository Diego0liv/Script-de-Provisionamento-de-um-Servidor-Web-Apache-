package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(nullable = false, length = 60)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal custo;

    @Column(name = "codigo_barras", length = 20)
    private String codigoBarras;

    @Column(length = 45)
    private String marca;

    @Column(name = "unidade_medida", length = 10)
    private String unidadeMedida;

    @Column(name = "data_aquisicao")
    private LocalDateTime dataAquisicao;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @Column(name = "estoque_minimo", nullable = false)
    private int estoqueMinimo;

    @Column(name = "valor_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorVenda;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
