package Service;

import Model.VendaPagamento;
import Model.VendaPagamentoId;
import Repository.VendaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoVendaService {

    @Autowired
    private PagamentoVendaRepository pagamentoVendaRepository;

    public List<PagamentoVenda> listarTodos() {
        return pagamentoVendaRepository.findAll();
    }

    public Optional<PagamentoVenda> buscarPorId(PagamentoVendaId id) {
        return pagamentoVendaRepository.findById(id);
    }

    public PagamentoVenda salvar(PagamentoVenda pagamentoVenda) {
        return pagamentoVendaRepository.save(pagamentoVenda);
    }

    public void deletar(PagamentoVendaId id) {
        if (!pagamentoVendaRepository.existsById(id)) {
            throw new RuntimeException("Pagamento de Venda não encontrado");
        }
        pagamentoVendaRepository.deleteById(id);
    }
}
