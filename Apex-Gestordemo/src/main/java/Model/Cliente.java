package Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Cliente", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipo_documento", "cpf_cnpj"})})
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "nome_razao", nullable = false, length = 100)
    private String nomeRazao;
    @Column(nullable = false, length = 15)
    private String telefone;
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Column(name = "cpf_cnpj", nullable = false, length = 14)
    private String cpfCnpj;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
    @Column(nullable = false)
    private int status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Cliente_Endereco", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Endereco> enderecos;
}
