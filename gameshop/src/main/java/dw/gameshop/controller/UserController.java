package dw.gameshop.controller;

import dw.gameshop.dto.SessionDTO;
import dw.gameshop.dto.UserDTO;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private HttpServletRequest httpServletRequest;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.registerUser(userDTO),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO,
                                        HttpServletRequest request) {
        String username = userDTO.getUserId();
        String password = userDTO.getPassword();

        if (userService.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return  new ResponseEntity<>(
                    "Login successful",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Invalid credentials",
                    HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<String> getCurrentUser(HttpServletRequest request) {
        String username = userService.getCurrentUser(request);  // 세션에서 유저네임 조회
        if (username != null) {
            return ResponseEntity.ok(username);  // 로그인한 유저네임 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }
}
