package dw.gameshop.service;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.model.Authority;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            return "Username already exists!";
        }
        User user = new User(
                userDTO.getUserName(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getEmail(),
                new Authority("User"),
                LocalDateTime.now()
        );
        userRepository.save(user);
        return "User registered successfully!";
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUserName(username).orElseThrow();
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 없으면 null 반환
        if (session != null) {
            return (String) session.getAttribute("username");  // 세션에서 유저네임 반환
        }
        return null;
    }
}
