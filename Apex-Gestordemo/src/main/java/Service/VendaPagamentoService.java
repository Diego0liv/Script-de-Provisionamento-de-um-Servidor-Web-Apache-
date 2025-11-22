package Service;

import Model.VendaPagamento;
import Model.VendaPagamentoId;
import Repository.VendaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendaPagamentoService {

    @Autowired
    private VendaPagamentoRepository vendaPagamentoRepository;

    public List<VendaPagamento> listarTodos() {
        return vendaPagamentoRepository.findAll();
    }

    public Optional<VendaPagamento> buscarPorId(VendaPagamentoId id) {
        return vendaPagamentoRepository.findById(id);
    }

    public VendaPagamento salvar(VendaPagamento pagamentoVenda) {
        return vendaPagamentoRepository.save(pagamentoVenda);
    }

    public void deletar(VendaPagamentoId id) {
        if (!vendaPagamentoRepository.existsById(id)) {
            throw new RuntimeException("Pagamento de Venda não encontrado");
        }
        vendaPagamentoRepository.deleteById(id);
    }
}
