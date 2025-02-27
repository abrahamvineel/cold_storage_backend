package org.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sessions {

    @Id
    @Column(nullable = false)
    private String emailId;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private Date expiry;
}
