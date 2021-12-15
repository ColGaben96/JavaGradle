package com.example.javagradle.bl.impl;

import com.example.javagradle.bl.dao.ClientDAO;
import com.example.javagradle.bl.dto.ClientDTO;
import com.example.javagradle.bl.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientDAO client;

    @Override
    @Transactional(readOnly = true)
    public List<?> listAll() {
        return (List<ClientDTO>) this.client.findAll();
    }

    @Override
    @Transactional
    public void saveOne(Object objectDTO) {
        this.client.save((ClientDTO) objectDTO);
    }

    @Override
    @Transactional
    public void deleteOne(Object objectDTO) {
        this.client.delete((ClientDTO) objectDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Object findByID(long objectID) {
        return this.client.findById(objectID).orElse(null);
    }
}
