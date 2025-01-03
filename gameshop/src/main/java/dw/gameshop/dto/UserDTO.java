package dw.gameshop.dto;

import dw.gameshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {
    private String userId;
    private String password;
    private String userName;
    private String userEmail;

    public static UserDTO toUserDto(User user) {
        return new UserDTO(
                user.getUserId(),
                null,
                user.getUserName(),
                user.getEmail()
        );
    }
}
