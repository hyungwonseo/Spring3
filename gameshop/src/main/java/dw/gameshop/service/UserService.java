package dw.gameshop.service;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            return "Username already exists!";
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserName(userDTO.getUserName());
        userRepository.save(user);
        return "User registered successfully!";
    }

    public boolean validateUser(String username, String password) {
        // 유저 확인
        User user = userRepository.findByUserName(username).orElseThrow();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    // 세션에서 유저네임을 가져오는 메서드
    public String getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 없으면 null 반환
        if (session != null) {
            return (String) session.getAttribute("username");  // 세션에서 유저네임 반환
        }
        return null;
    }
}
