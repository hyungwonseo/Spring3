package dw.gameshop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SessionDTO {
    private String userId;
    private String authority;
}
