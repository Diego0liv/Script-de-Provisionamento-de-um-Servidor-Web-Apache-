package Service;

import Model.*;
import Repository.ProdutoRepository;
import Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda realizarVenda(Venda venda) {
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(1); // 1 = Concluída
        
        BigDecimal valorTotal = BigDecimal.ZERO;

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new RuntimeException("Uma venda não pode ser realizada sem itens.");
        }

        // Primeiro, salva a venda para gerar um ID
        Venda vendaSalva = vendaRepository.save(venda);

        for (ProdutoVenda item : venda.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getIdProduto()));

            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getDescricao());
            }

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            item.setPrecoUnitario(produto.getValorVenda());
            valorTotal = valorTotal.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
            
            item.setVenda(vendaSalva); // Associa à venda já salva
            item.setId(new ProdutoVendaId(produto.getId(), vendaSalva.getId()));
        }

        if (venda.getDesconto() != null) {
            valorTotal = valorTotal.subtract(venda.getDesconto());
        }
        vendaSalva.setValorTotal(valorTotal);

        if (venda.getPagamentos() != null) {
            for (VendaPagamento pag : venda.getPagamentos()) {
                pag.setVenda(vendaSalva); // Associa à venda já salva
                pag.setId(new VendaPagamentoId(vendaSalva.getId(), pag.getFormaPagamento().getId()));
            }
        }
        
        // Atualiza a venda com o valor total e associações
        return vendaRepository.save(vendaSalva);
    }

    @Transactional
    public Venda cancelarVenda(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        
        // Devolve itens ao estoque
        for (ProdutoVenda item : venda.getItens()) {
            Produto produto = item.getProduto();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }
        
        venda.setStatus(0); // 0 = Cancelada
        return vendaRepository.save(venda);
    }
}
