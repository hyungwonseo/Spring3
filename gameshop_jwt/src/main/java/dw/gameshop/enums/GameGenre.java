package dw.gameshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameGenre {
    ACTION("액션"),
    ADVENTURE("어드벤처"),
    ROLE_PLAYING("롤플레잉"),
    SIMULATION("시뮬레이션"),
    STRATEGY("전략"),
    SPORTS("스포츠"),
    RACING("레이싱"),
    FIGHTING("격투"),
    PUZZLE("퍼즐");

    private final String genreName;
}
