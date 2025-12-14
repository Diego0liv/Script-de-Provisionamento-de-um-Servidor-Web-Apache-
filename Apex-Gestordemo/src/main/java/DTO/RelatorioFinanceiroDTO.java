package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RelatorioFinanceiroDTO {

    private BigDecimal totalVendas;
    private BigDecimal totalCustosProdutos;
    private BigDecimal totalDespesas;
    private BigDecimal lucroLiquido;
    private BigDecimal saldoCaixa;
}
