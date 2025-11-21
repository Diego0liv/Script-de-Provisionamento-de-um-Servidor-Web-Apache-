package Service;

import Model.Menu;
import Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarTodos() {
        return menuRepository.findAll();
    }

    public Optional<Menu> buscarPorId(Long id) {
        return menuRepository.findById(id);
    }

    public Menu salvar(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu desativar(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu não encontrado"));
        menu.setExibir(0); // 0 = Não Exibir
        return menuRepository.save(menu);
    }
}