package com.example.javagradle.bl.utils;

import java.util.List;

public interface RapidCrudConfiguration {
    List<?> listAll();
    void saveOne(Object objectDTO);
    void deleteOne(Object objectDTO);
    Object findByID(long objectID);
}
