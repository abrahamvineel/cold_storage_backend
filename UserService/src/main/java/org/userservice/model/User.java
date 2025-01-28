package org.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String email;
    private String password;
}
