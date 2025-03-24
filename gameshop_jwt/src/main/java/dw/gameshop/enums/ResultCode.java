package dw.gameshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {
    SUCCESS("정상 처리되었습니다"),
    ERROR("에러가 발생했습니다");

    private final String msg;
}