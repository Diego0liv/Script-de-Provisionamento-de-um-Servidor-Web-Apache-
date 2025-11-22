package Service;

import Model.ProdutoVenda;
import Model.ProdutoVendaId;
import Repository.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoVendaService {

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public List<ProdutoVenda> listarTodos() {
        return produtoVendaRepository.findAll();
    }

    public Optional<ProdutoVenda> buscarPorId(ProdutoVendaId id) {
        return produtoVendaRepository.findById(id);
    }

    public ProdutoVenda salvar(ProdutoVenda ProdutoVenda) {
        return produtoVendaRepository.save(ProdutoVenda);
    }

    public void deletar(ProdutoVendaId id) {
        if (!produtoVendaRepository.existsById(id)) {
            throw new RuntimeException("Item de Venda não encontrado");
        }
        produtoVendaRepository.deleteById(id);
    }
}
