package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById((username));
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("No username : " + username);
        }
        return user.get();
    }
}
