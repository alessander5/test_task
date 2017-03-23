package com.test.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "system_user")
public class UserRole extends AbstractEntity{

    @Column(name = "name", nullable = false)
    private String name;

}
