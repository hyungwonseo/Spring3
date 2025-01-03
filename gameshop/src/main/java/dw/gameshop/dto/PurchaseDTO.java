package dw.gameshop.dto;

import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDTO {
    private long id;
    private Game game;
    private UserDTO user;
    private LocalDateTime purchaseTime;

    public static PurchaseDTO toPurchaseDto(Purchase purchase) {
        return new PurchaseDTO(
                purchase.getId(),
                purchase.getGame(),
                UserDTO.toUserDto(purchase.getUser()),
                purchase.getPurchaseTime()
        );
    }
}
