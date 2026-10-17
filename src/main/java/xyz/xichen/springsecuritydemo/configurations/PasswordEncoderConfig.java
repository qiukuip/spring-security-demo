package xyz.xichen.springsecuritydemo.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();
        passwordEncoderMap.put(idForEncode, new BCryptPasswordEncoder(16));
        passwordEncoderMap.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        passwordEncoderMap.put("argon2", new Argon2Password4jPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, passwordEncoderMap);
    }
}
