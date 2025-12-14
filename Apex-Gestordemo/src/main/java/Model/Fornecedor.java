package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Fornecedor")
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long idFornecedor;
    @Column(nullable = false)
    private int status;
    @Column(name = "razao_social", nullable = false, length = 100)
    private String razaoSocial;
    @Column(name = "nome_fantasia", nullable = false, length = 45)
    private String nomeFantasia;
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;
    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    @Column(nullable = false, length = 15)
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id_endereco", nullable = false)
    private Endereco endereco;
}
