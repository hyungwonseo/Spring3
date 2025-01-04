package dw.gameshop.dto;

import dw.gameshop.enums.GameRating;
import dw.gameshop.model.Review;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    private long reviewId;
    private String gameTitle;
    private String userName;
    private String reviewPoint;
    private String reviewText;
}
