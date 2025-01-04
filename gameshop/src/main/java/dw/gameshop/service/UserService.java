package dw.gameshop.service;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.InvalidRequestException;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.exception.UnauthorizedUserException;
import dw.gameshop.model.User;
import dw.gameshop.repository.AuthorityRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;

    public UserDTO registerUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUserName());
        if (user.isPresent()) {
            throw new InvalidRequestException("Username already exists");
        }
        return userRepository.save(
                    new User(
                        userDTO.getUserName(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        userDTO.getEmail(),
                        userDTO.getRealName(),
                        authorityRepository.findById("USER")
                                .orElseThrow(()->new ResourceNotFoundException("No role")),
                        LocalDateTime.now())
                ).toDto();
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findById(username)
                .orElseThrow(()->new InvalidRequestException("Invalid Username"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 없으면 예외 처리
        if (session == null) {
            throw new UnauthorizedUserException("No Session exist");
        }
        String userName = (String) session.getAttribute("username");  // 세션에서 유저네임 반환
        return userRepository.findById(userName)
                .orElseThrow(()->new InvalidRequestException("No username"));
    }
}
