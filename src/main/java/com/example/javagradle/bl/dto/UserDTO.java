package com.example.javagradle.bl.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
public class UserDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mail;
    private String password;
    @ManyToOne
    private RoleDTO roles;
    @ManyToOne
    private ClientDTO client;
}
