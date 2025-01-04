package dw.gameshop.model;

import dw.gameshop.dto.PurchaseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
    @Column(name="purchase_time")
    private LocalDateTime purchaseTime;

    public PurchaseDTO toDto() {
        return new PurchaseDTO(
                this.id,
                this.game,
                this.user.toDto(),
                this.purchaseTime
        );
    }
}
