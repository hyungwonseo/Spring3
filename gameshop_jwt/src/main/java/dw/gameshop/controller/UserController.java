package dw.gameshop.controller;

import dw.gameshop.dto.SessionDto;
import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.UnauthorizedUserException;
import dw.gameshop.model.User;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.registerUser(userDTO),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO,
                                        HttpServletRequest request) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (userService.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return  new ResponseEntity<>(
                    "Login successful",
                    HttpStatus.OK);
        } else {
            throw new UnauthorizedUserException("Authentication Failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate(); // 세션 종료
        return new ResponseEntity<>(
                "You have been logged out.",
                HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<SessionDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUsername(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());

        return new ResponseEntity<>(sessionDto, HttpStatus.OK);
    }
}
