package Service;

import Model.Produto;
import Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto desativar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setStatus(0); // 0 = Inativo
        return produtoRepository.save(produto);
    }
    
    public Produto adicionarEstoque(Long id, int quantidade) {
        Produto produto = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        return salvar(produto);
    }
}
