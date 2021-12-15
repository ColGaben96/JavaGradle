package com.example.javagradle.bl.impl;

import com.example.javagradle.bl.dao.RoleDAO;
import com.example.javagradle.bl.dto.RoleDTO;
import com.example.javagradle.bl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleImpl implements RoleService {

    @Autowired
    private RoleDAO role;

    @Override
    @Transactional(readOnly = true)
    public List<?> listAll() {
        return (List<RoleDTO>) this.role.findAll();
    }

    @Override
    @Transactional
    public void saveOne(Object objectDTO) {
        this.role.save((RoleDTO) objectDTO);
    }

    @Override
    @Transactional
    public void deleteOne(Object objectDTO) {
        this.role.delete((RoleDTO) objectDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Object findByID(long objectID) {
        return this.role.findById(objectID).orElse(null);
    }
}
