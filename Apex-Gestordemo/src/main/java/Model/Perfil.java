package Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "Perfil")
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long idPerfil;
    @Column(nullable = false, length = 45)
    private String nome;
    @Column(nullable = false)
    private int status;
    @ManyToMany(mappedBy = "perfis")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Menu> menus;
}
