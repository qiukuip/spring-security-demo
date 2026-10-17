package xyz.xichen.springsecuritydemo.demo;

import org.springframework.security.crypto.encrypt.AesGcmBytesEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;
import org.springframework.security.crypto.password4j.BalloonHashingPassword4jPasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;

import com.password4j.Argon2Function;
import com.password4j.BalloonHashingFunction;
import com.password4j.BcryptFunction;
import com.password4j.types.Argon2;

public class Demo2 {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new Argon2Password4jPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        System.out.println("encoded password: " + encodedPassword);
        System.out.println(passwordEncoder.matches("password", encodedPassword));

        Argon2Function argon2Function = Argon2Function.getInstance(65536, 3, 4, 32, Argon2.ID);
        passwordEncoder = new Argon2Password4jPasswordEncoder(argon2Function);
        encodedPassword = passwordEncoder.encode("myPassword");
        System.out.println(passwordEncoder.matches("myPassword", encodedPassword));

        passwordEncoder = new BcryptPassword4jPasswordEncoder();
        encodedPassword = passwordEncoder.encode("myPassword");
        System.out.println("encoded password: " + encodedPassword);
        System.out.println(passwordEncoder.matches("myPassword", encodedPassword));

        BcryptFunction bcryptFunction = BcryptFunction.getInstance(12);
        passwordEncoder = new BcryptPassword4jPasswordEncoder(bcryptFunction);
        encodedPassword = passwordEncoder.encode("myPassword");
        System.out.println("encoded password: " + encodedPassword);
        System.out.println(passwordEncoder.matches("myPassword", encodedPassword));

        passwordEncoder = new BalloonHashingPassword4jPasswordEncoder();
        encodedPassword = passwordEncoder.encode("myPassword");
        System.out.println("encoded password: " + encodedPassword);
        System.out.println(passwordEncoder.matches("myPassword", encodedPassword));

        BalloonHashingFunction balloonHashingFunction = BalloonHashingFunction.getInstance("SHA-256", 1024, 3, 3);
        passwordEncoder = new BalloonHashingPassword4jPasswordEncoder(balloonHashingFunction);
        encodedPassword = passwordEncoder.encode("myPassword");
        System.out.println("encoded password: " + encodedPassword);
        System.out.println(passwordEncoder.matches("myPassword", encodedPassword));

        String salt = KeyGenerators.string().generateKey();
        System.out.println("salt: " + salt);

        AesGcmBytesEncryptor aesGcmBytesEncryptor = AesGcmBytesEncryptor.withPassword("admin-password", salt).build();
        String encodedString = new String(aesGcmBytesEncryptor.encrypt("myPassword".getBytes()));
        System.out.println("encoded string: " + encodedString);
        try {
            String decodedString = new String(aesGcmBytesEncryptor.decrypt(encodedString.getBytes()));
            System.out.println("decoded string: " + decodedString);
        } catch (Exception e) {
            System.out.println("error");
        }

        BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(16);
        byte[] key = bytesKeyGenerator.generateKey();
        System.out.println("key: " + new String(key));
    }
}
