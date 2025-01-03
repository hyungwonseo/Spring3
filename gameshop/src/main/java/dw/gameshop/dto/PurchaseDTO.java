package dw.gameshop.dto;

import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseDTO {
    private long id;
    private Game game;
    private UserDTO user;
    private LocalDateTime purchaseTime;
}
