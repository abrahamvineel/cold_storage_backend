package org.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.userservice.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM user_info WHERE email = :email and password = :password", nativeQuery = true)
    Optional<User> doesUserExist(@Param("email") String email, @Param("password") String password);
}
