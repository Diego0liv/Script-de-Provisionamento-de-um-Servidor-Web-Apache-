package Service;

import Model.Usuario;
import Repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired // 1. Injetamos o PasswordEncoder que criamos na SecurityConfig
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Substitui getCarregaPorID()
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Substitui getRecuperarUsuario()
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    // Substitui gravar()
    public Usuario salvar(Usuario usuario) {
        // 2. Antes de salvar, criptografamos a senha que veio do formulário/requisição

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    // Substitui desativar()
    @Transactional
    public Usuario desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));

        usuario.setStatus(2); // Define o status como inativo

        return usuarioRepository.save(usuario);
    }

}
