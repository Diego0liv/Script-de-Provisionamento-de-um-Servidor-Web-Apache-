package Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Entity
@Table(name = "Menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long idMenu;
    @Column(nullable = false, length = 45)
    private String nome;
    @Column(nullable = false, length = 100)
    private String link;
    @Column(length = 45)
    private String icone;
    @Column(nullable = false)
    private int exibir;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Menu_Perfil", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Perfil> perfis;
}
