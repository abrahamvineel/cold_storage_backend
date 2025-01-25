package org.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.userservice.dto.UserCreationRequest;
import org.userservice.model.User;
import org.userservice.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserCreationRequest userCreationRequest) {
        User user = new User();
        user.setEmail(userCreationRequest.getEmail());
        user.setPassword(userCreationRequest.getPassword());
        userRepository.save(user);
    }
}
