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
    @Autowired
    private UserDetailsConfig config;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            try {
                auth.requestMatchers("/").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pf/cadastrar", "/pj/cadastrar", "/carrinho/**").permitAll()
                        .requestMatchers("/pf/cadastrar", "/pj/cadastrar", "/carrinho/**").permitAll()
                        .requestMatchers("/pf", "/pj", "/produtos").hasRole("ADMIN")
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
        }).httpBasic(withDefaults());

        return http.build();
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(config)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}