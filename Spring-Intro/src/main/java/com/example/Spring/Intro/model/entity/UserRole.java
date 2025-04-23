package com.example.Spring.Intro.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_roles")
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

/*@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor*/

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany()
    @JoinTable(name = "user_roles_users",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;
    private String description;

}
