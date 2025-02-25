package org.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.model.Sessions;
import org.userservice.model.User;

public interface SessionsRepository extends JpaRepository<Sessions, String> {

}
