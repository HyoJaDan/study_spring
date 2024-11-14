package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    public void setEmail(String email){
        this.email=email;
    }
    @Column(nullable = true,unique = false)
    private String name;
    public void setName(String name){
        this.name=name;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private UserEntity(String username, String password, String email, String name, AuthProvider provider, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.role = role;
        this.name=name;
    }
    public static class LocalUserBuilder {
        private String username;
        private String password;
        private Role role;
        public LocalUserBuilder username(String username) {
            this.username = username;
            return this;
        }
        public LocalUserBuilder password(String password) {
            this.password = encodePassword(password);
            return this;
        }
        public LocalUserBuilder role(Role role) {
            this.role = role;
            return this;
        }
        public UserEntity build() {
            return new UserEntity(username, password, generateRandomEmail(), null, AuthProvider.LOCAL,  role);
        }
    }
    public static class OAuthUserBuilder {
        private AuthProvider provider;
        private String email;
        private String username;
        private String name;
        private Role role;
        public OAuthUserBuilder provider(AuthProvider provider) {
            this.provider = provider;
            return this;
        }
        public OAuthUserBuilder email(String email) {
            this.email = email != null ? email : generateRandomEmail();
            return this;
        }
        public OAuthUserBuilder username(String username) {
            this.username = username;
            return this;
        }
        public OAuthUserBuilder name(String name){
            this.name = name;
            return this;
        }
        public OAuthUserBuilder role(Role role) {
            this.role = role;
            return this;
        }
        public UserEntity build() {
            return new UserEntity(username, generateSecureRandomPassword(), email, name, provider, role);
        }
    }
    public static LocalUserBuilder localUserBuilder() {
        return new LocalUserBuilder();
    }
    public static OAuthUserBuilder oauthUserBuilder() {
        return new OAuthUserBuilder();
    }
    public static String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
    private static String generateRandomEmail() {
        return UUID.randomUUID().toString() + "@email.com";
    }
    private static String generateSecureRandomPassword() {
        return UUID.randomUUID().toString();
    }
}