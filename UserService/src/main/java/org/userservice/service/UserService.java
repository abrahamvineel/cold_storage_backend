package org.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.userservice.dto.UserCreationRequest;
import org.userservice.dto.UserLoginDetailsRequest;
import org.userservice.model.User;
import org.userservice.repository.UserRepository;

import java.security.SecureRandom;

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
        User user = new User();
        user.setEmail(loginDetailsRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(loginDetailsRequest.getPassword());

        SecureRandom random = new SecureRandom();
        random.generateSeed(12345);
        String salt = BCrypt.gensalt(10, random); // Fixed salt

        user.setPassword(encodedPassword);
        return userRepository.doesUserExist(user.getEmail(), user.getPassword()).isPresent();
    }
}
