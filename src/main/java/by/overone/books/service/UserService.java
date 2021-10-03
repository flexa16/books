package by.overone.books.service;

import by.overone.books.model.entitity.AppUser;
import by.overone.books.model.entitity.UserRole;
import by.overone.books.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static by.overone.books.configuration.SecurityConfiguration.ADMIN_ROLE;
import static by.overone.books.configuration.SecurityConfiguration.USER_ROLE;


@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerNewUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        if (user.isAdmin()) {
            roles.add(new UserRole(ADMIN_ROLE));
        }
        roles.add(new UserRole(USER_ROLE));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
