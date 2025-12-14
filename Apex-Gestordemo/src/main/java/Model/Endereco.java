package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;
    @Column(length = 45)
    private String estado;
    @Column(length = 45)
    private String cidade;
    @Column(nullable = false, length = 9)
    private String cep;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 2)
    private String uf;
    @Column(length = 10)
    private String numero;
    @Column(length = 100)
    private String complemento;
}
