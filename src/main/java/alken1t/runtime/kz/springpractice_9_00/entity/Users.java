package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Enumerated
//    private String role;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;
}