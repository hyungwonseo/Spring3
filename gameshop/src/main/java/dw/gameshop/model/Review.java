package dw.gameshop.model;

import dw.gameshop.dto.ReviewDTO;
import dw.gameshop.enums.GameRating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name="user_name")
    private User user;
    @Column(name="point", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameRating point;
    @Column(name="review_text", columnDefinition = "TEXT")
    private String reviewText;
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    public ReviewDTO toDto() {
        return new ReviewDTO(
                this.id,
                this.game.getTitle(),
                this.user.getUserName(),
                this.point.name(),
                this.reviewText
        );
    }
}









