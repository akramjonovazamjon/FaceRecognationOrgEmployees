package com.example.lionprintfirstproject.entity;

import com.example.lionprintfirstproject.dto.user.UpdateUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserDetails asDetailedUser() {
        var authority = new SimpleGrantedAuthority(role.name());
        return new org.springframework.security.core.userdetails.User(username, password, true, true, true, true, List.of(authority));
    }

    public static User of(String fullName, String username, String password, UserRole role) {
        return User.builder()
                .fullName(fullName)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

    public void update(UpdateUser dto) {
        setFullName(dto.fullName());
        setUsername(dto.username());
        setPassword(new BCryptPasswordEncoder().encode(dto.password()));
        setRole(dto.role());
    }

}
