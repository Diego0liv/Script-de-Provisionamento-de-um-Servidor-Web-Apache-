package Model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class VendaPagamentoId implements Serializable{
    
    @Column(name = "venda_id")
    private Long idVenda;
    
    @Column(name = "forma_pagamento_id")
    private Long idFromaPg;
}
