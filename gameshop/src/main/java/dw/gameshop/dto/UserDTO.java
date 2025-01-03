package dw.gameshop.dto;

import dw.gameshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {
    private String userName;
    private String password;
    private String email;

    public static UserDTO toUserDto(User user) {
        return new UserDTO(
                user.getUserName(),
                null,
                user.getEmail()
        );
    }
}
