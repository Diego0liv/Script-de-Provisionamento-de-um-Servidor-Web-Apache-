package Model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "Cliente")
@Data
public class Cliente {

    @Id
    private Long id;
    private Long idCliente;

    @Column(name = "nome_razao", nullable = false, length = 100)
    private String nomeRazao;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(name = "tipo_documento")
    private int tipoDocumento;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private int status;

    @ManyToMany
    @JoinTable(
            name = "Cliente_Endereco",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Endereco> enderecos;
}
