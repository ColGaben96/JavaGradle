package com.example.javagradle.bl.dao;

import com.example.javagradle.bl.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserDTO, Long> {
    UserDTO findByMail(String mail);
}
