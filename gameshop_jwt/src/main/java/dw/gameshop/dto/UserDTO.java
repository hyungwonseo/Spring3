package dw.gameshop.dto;

import dw.gameshop.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String realName;
    private String authority;

    public static UserDTO toUserDto(User user) {
        return new UserDTO(
                user.getUsername(),
                null,
                user.getRealName(),
                user.getEmail(),
                user.getAuthority().getAuthorityName()
        );
    }
}
