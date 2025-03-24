package dw.gameshop.dto;

import dw.gameshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String realName;
    private String role;

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
