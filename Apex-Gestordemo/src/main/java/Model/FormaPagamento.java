package Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Forma_Pagamento")
@Data
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pagamento")
    private Long idFromaPg;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @Column(nullable = false)
    private int status;
    
    @Column(name = "tipo_pagamento", nullable = false)
    private int tipoPagamento;

}
