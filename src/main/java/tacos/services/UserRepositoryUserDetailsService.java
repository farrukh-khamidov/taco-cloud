package tacos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.entities.User;
import tacos.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optional = userRepository.findByUsername(username);
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//        throw new UsernameNotFoundException(
//                "User '" + username + "' not found");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}