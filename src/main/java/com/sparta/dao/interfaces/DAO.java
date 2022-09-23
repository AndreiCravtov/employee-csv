package com.sparta.dao.interfaces;

import com.sparta.entities.DataObject;

import java.util.List;

public interface DAO<T extends DataObject> {
    int insert(T newRow);
    T findById(int id);
    void update(T updatedRow);
    void deleteById(int id);
    List<T> findAll();
}

