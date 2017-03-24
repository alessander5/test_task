package com.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "system_user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity{

    @Column(name = "login", nullable = false, unique = true, length = 100)
    private String userName;

    @Column(name = "password", nullable = false)
    private String passWord;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "name")
    private Set<UserRoleEnum> roles = new HashSet<>();

    @Column(name = "is_active", nullable = false, columnDefinition="tinyint(1) default 1")
    private boolean isActive = false;

}
