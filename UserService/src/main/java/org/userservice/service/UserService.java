package org.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.userservice.dto.UserCreationRequest;
import org.userservice.dto.UserLoginDetailsRequest;
import org.userservice.model.User;
import org.userservice.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createUser(UserCreationRequest userCreationRequest) {
        User user = new User();
        user.setEmail(userCreationRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(userCreationRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean loginUser(UserLoginDetailsRequest loginDetailsRequest) {
        String loginEmail = loginDetailsRequest.getEmail();
        User user = getUser(loginEmail);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = loginDetailsRequest.getPassword();
        String storedHashedPassword = user.getPassword(); // Retrieved from DB

        return encoder.matches(rawPassword, storedHashedPassword) && loginEmail.equals(user.getEmail());
    }

    private User getUser(String emailId) {
        Optional<User> user = userRepository.findById(emailId);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
