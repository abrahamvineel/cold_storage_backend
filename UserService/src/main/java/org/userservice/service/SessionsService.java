package org.userservice.service;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.userservice.model.Sessions;
import org.userservice.repository.SessionsRepository;

import java.util.Date;

@Service
public class SessionsService {
    private final SessionsRepository sessionsRepository;

    @Inject
    public SessionsService(SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
    }


    public void saveToken(String email, String token, Date expiry) {
        Sessions session = new Sessions();
        session.setEmailId(email);
        session.setToken(token);
        session.setExpiry(expiry);
        sessionsRepository.save(session);
    }
}
