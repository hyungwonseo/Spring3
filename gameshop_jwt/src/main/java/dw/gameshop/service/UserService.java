package dw.gameshop.service;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.InvalidRequestException;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.exception.UnauthorizedUserException;
import dw.gameshop.model.User;
import dw.gameshop.repository.AuthorityRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Optional<User> user = userRepository.findById(userDTO.getUsername());
        if (user.isPresent()) {
            throw new InvalidRequestException("Username already exists");
        }
        return userRepository.save(
                    new User(
                        userDTO.getUsername(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        userDTO.getEmail(),
                        userDTO.getRealName(),
                        authorityRepository.findById("ROLE_USER")
                                .orElseThrow(()->new ResourceNotFoundException("No role")),
                        LocalDateTime.now())
                ).toDto();
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findById(username)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Username"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedUserException("User is not authenticated");
        }
        return userRepository.findById(authentication.getName())
                .orElseThrow(()->new ResourceNotFoundException("No username"));
    }
}
