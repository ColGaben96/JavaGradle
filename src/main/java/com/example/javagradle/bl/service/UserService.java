package com.example.javagradle.bl.service;

import com.example.javagradle.bl.dto.UserDTO;
import com.example.javagradle.bl.utils.RapidCrudConfiguration;

public interface UserService extends RapidCrudConfiguration {

    UserDTO findByMail(String mail);
}
