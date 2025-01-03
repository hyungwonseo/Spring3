package dw.gameshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameRating {
    VERY_BAD(1, "매우 나쁨"),
    BAD(2, "나쁨"),
    AVERAGE(3, "보통"),
    GOOD(4, "좋음"),
    EXCELLENT(5, "훌륭함");

    private final int ratingValue;
    private final String ratingDescription;
}
