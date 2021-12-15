package com.example.javagradle.bl.dao;

import com.example.javagradle.bl.dto.RoleDTO;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAO extends CrudRepository<RoleDTO, Long> {
}
