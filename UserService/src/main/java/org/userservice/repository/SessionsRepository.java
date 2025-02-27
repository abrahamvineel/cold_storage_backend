package org.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.userservice.model.Sessions;
import org.userservice.model.User;

public interface SessionsRepository extends JpaRepository<Sessions, String> {

    @Query("SELECT COUNT(s.emailId) FROM Sessions s WHERE s.emailId = :email AND s.expiry > CURRENT_TIMESTAMP")
    int findTokenValidity(@Param("email") String email);
}
