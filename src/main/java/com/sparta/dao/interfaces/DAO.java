package com.sparta.dao.interfaces;

import com.sparta.entities.DataObject;

import java.util.List;

public interface DAO<T extends DataObject> {
    int insert(T newRow);
    void update(T updatedRow);
    T findById(int id);
    List<T> findAll();
    void deleteById(int id);
    void deleteAll();
}

