package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_name", length=255)
    private String userName;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", length=255, nullable = false, unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
}
