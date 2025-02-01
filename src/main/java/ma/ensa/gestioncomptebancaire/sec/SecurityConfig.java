package ma.ensa.gestioncomptebancaire.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build(),
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/css/**", "/webjars/**").permitAll() // Autoriser le CSS et Bootstrap
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/operations", true) // Rediriger après une connexion réussie
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirige après déconnexion
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(eh -> eh
                        .accessDeniedPage("/403") // Rediriger vers une page d'erreur 403
                )
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/consulterCompte/**", "/operations/**").hasRole("USER")
                        .requestMatchers("/saveOperation/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .build();
    }
}
