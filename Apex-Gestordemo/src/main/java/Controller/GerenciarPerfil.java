package Controller;

import Model.Perfil;
import Service.PerfilService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfis") 
@CrossOrigin(origins = "http://localhost:4200")
public class GerenciarPerfil { 

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Perfil> listarTodos() {
        return perfilService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        return perfilService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Perfil> criarPerfil(@Valid @RequestBody Perfil perfil) {
        Perfil novoPerfil = perfilService.salvar(perfil);
        return new ResponseEntity<>(novoPerfil, HttpStatus.CREATED);
    }

    // --- LÓGICA DE ATUALIZAÇÃO CORRIGIDA E MAIS SEGURA ---
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @Valid @RequestBody Perfil perfilDetalhes) {
        return perfilService.buscarPorId(id)
                .map(perfilExistente -> {
                    // 1. Atualiza os campos do objeto que veio do banco
                    perfilExistente.setNome(perfilDetalhes.getNome());
                    perfilExistente.setStatus(perfilDetalhes.getStatus());
                    // (Aqui você também poderia atualizar a lista de menus, se necessário)

                    // 2. Salva o objeto já existente e atualizado
                    Perfil perfilAtualizado = perfilService.salvar(perfilExistente);
                    return ResponseEntity.ok(perfilAtualizado);
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se o perfil não existir
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Perfil> desativarPerfil(@PathVariable Long id) {
        try {
            Perfil perfilDesativado = perfilService.desativar(id);
            return ResponseEntity.ok(perfilDesativado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable Long id) {
        try {
            perfilService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

