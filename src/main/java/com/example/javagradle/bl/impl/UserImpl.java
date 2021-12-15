package com.example.javagradle.bl.impl;

import com.example.javagradle.bl.dao.RoleDAO;
import com.example.javagradle.bl.dao.UserDAO;
import com.example.javagradle.bl.dto.RoleDTO;
import com.example.javagradle.bl.dto.UserDTO;
import com.example.javagradle.bl.service.RoleService;
import com.example.javagradle.bl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Slf4j
public class UserImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO user;
    @Autowired
    private RoleDAO role;

    @Override
    public List<?> listAll() {
        return this.user.findAll();
    }

    @Override
    public void saveOne(Object objectDTO) {
        this.user.save((UserDTO) objectDTO);
    }

    @Override
    public void deleteOne(Object objectDTO) {
        this.user.delete((UserDTO) objectDTO);
    }

    @Override
    public Object findByID(long objectID) {
        return this.user.findById(objectID).orElse(null);
    }

    @Override
    public UserDTO findByMail(String mail) {
        var users = this.user.findAll();
        for (UserDTO user : users) {
            if (user.getMail().equals(mail)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.user.findByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();
        for (RoleDTO rol : this.role.findAll()) {
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }
        return new User(user.getMail(), user.getPassword(), roles);
    }
}
