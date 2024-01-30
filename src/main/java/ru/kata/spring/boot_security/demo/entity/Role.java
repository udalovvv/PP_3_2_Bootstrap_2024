package ru.kata.spring.boot_security.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "role")
    @NonNull
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User owner_user;

    @Override
    public String getAuthority() {
        return getRole();
    }
}
