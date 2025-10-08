package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Define esta classe como uma fonte de configurações para o Spring
@EnableWebSecurity // Habilita a segurança web do Spring
public class SecurityConfig {

    @Bean // Diz ao Spring: "crie e gerencie este objeto para mim"
    public PasswordEncoder passwordEncoder() {
        // Estamos dizendo que o algoritmo de criptografia padrão será o BCrypt
        return new BCryptPasswordEncoder();
    }

    // --- IMPORTANTE (PARA A FASE DE DESENVOLVIMENTO) ---
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilita a proteção CSRF e permite todas as requisições
        // para que possamos testar nossa API no Postman sem sermos bloqueados.
        // MAIS TARDE, vamos configurar as permissões corretas aqui.
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                ).build();
    }
}
