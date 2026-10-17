package xyz.xichen.springsecuritydemo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserService {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("pa1ss0wd1"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder.encode("123457"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123458"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
}
