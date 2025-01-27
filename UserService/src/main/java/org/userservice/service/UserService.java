package org.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.userservice.dto.UserCreationRequest;
import org.userservice.dto.UserLoginDetailsRequest;
import org.userservice.model.User;
import org.userservice.repository.UserRepository;

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

    public void loginUser(UserLoginDetailsRequest loginDetailsRequest) {

    }
}
