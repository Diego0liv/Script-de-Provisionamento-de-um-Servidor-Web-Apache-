package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class VendaPagamentoId implements Serializable {

    @Column(name = "venda_id")
    private Long vendaId;
    @Column(name = "forma_pagamento_id")
    private Long formaPagamentoId;

    public VendaPagamentoId() {
    }

    public VendaPagamentoId(Long vendaId, Long formaPagamentoId) {
        this.vendaId = vendaId;
        this.formaPagamentoId = formaPagamentoId;
    }
}
