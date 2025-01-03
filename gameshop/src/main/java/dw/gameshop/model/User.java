package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id", length=100)
    private String userId;
    @Column(name="user_name", length=255, nullable = false)
    private String userName;
    @Column(name="email", length=255, nullable = false, unique = true)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
}
