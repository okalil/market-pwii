package com.market.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz //define com as requisições HTTP devem ser tratadas com relação à segurança.
                                        .requestMatchers("/webjars/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/").permitAll()
                                        .anyRequest() //define que a configuração é válida para qualquer requisição.
                                        .authenticated() //define que o usuário precisa estar autenticado.
                                        .and()
                                        .formLogin() //define que a autenticação pode ser feita via formulário de login.
                                        .loginPage("/login").defaultSuccessUrl("/", true) // passamos como parâmetro a URL para acesso à página de login que criamos
                                        .permitAll() //define que essa página pode ser acessada por todos, independentemente do usuário estar autenticado ou não.
                                        .and()
                                        .logout() //para logout
                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.inMemoryAuthentication() //configuração de autenticação em memória
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN");
    }

    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar disso, ele já terá o que precisa configurado.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
