package com.example.javagradle.bl.dao;

import com.example.javagradle.bl.dto.ClientDTO;
import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<ClientDTO, Long> {
}
