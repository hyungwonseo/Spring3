package dw.gameshop.service;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.InvalidRequestException;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Authority;
import dw.gameshop.model.User;
import dw.gameshop.repository.AuthorityRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserName())
                .orElseThrow(()->new InvalidRequestException("Username already exists"));
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

    public UserDTO getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 없으면 null 반환
        if (session != null) {
            String userName = (String) session.getAttribute("username");  // 세션에서 유저네임 반환
            return userRepository.findById(userName)
                    .map(User::toDto)
                    .orElseThrow(()->new InvalidRequestException("No username"));
        }
        return null;
    }
}
