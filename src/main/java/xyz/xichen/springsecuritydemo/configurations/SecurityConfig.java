package xyz.xichen.springsecuritydemo.configurations;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/css/**")
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .defaultSuccessUrl("/index.html", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html")
                        .permitAll());
        return http.build();
    }

    @Bean 
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    static class CompromisedPasswordAuthenticationFailureHandler implements AuthenticationFailureHandler {

        private final SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {
            if (exception instanceof CompromisedPasswordException) {
                this.redirectStrategy.sendRedirect(request, response, "/reset-password");
                return;
            }
            this.simpleUrlAuthenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        }
    }
}
