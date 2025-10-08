package Service;


import Model.Perfil;
import Repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Anotação que define esta classe como um serviço gerenciado pelo Spring
public class PerfilService {

    @Autowired // Injeta o repository para que possamos usá-lo
    private PerfilRepository perfilRepository;

    // Substituição direta do antigo getLista()
    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    // Substituição do getCarregaPorID()
    public Optional<Perfil> buscarPorId(Long id) {
        return perfilRepository.findById(id);
    }

    // Substituição do gravar()
    public Perfil salvar(Perfil perfil) {
        // A lógica de INSERT vs UPDATE é feita automaticamente pelo método save()
        return perfilRepository.save(perfil);
    }

    // Implementação da regra de negócio "desativar"
    @Transactional // Garante que a operação seja atômica
    public Perfil desativar(Long id) {
        // 1. Busca o perfil no banco de dados
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o id: " + id));

        // 2. Aplica a regra de negócio
        perfil.setStatus(2); // Define o status como "desativado"

        // 3. Salva a alteração de volta no banco
        return perfilRepository.save(perfil);
    }

    public void deletar(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new RuntimeException("Perfil não encontrado com o id: " + id);
        }
        perfilRepository.deleteById(id);
    }
}
