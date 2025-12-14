package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Tipo_Despesa")
@Data
public class TipoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_despesa")
    private Long idTipoDespesa;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false)
    private int status;
}
