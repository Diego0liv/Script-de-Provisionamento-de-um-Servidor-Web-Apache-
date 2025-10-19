package Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Venda_FormasPagamento")
@Data
public class VendaPagamento {

    @EmbeddedId
    private VendaPagamentoId idVendaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("formaPagamentoId")
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "numero_parcelas")
    private Integer numeroParcelas; 
}
