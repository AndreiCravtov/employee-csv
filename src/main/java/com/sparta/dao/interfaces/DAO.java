package com.sparta.dao.interfaces;

import com.sparta.entities.DataObject;

public interface DAO<T extends DataObject> {
    void insert(T newRow);
    T findById(int id);
    void update(T updatedRow);
    void deleteById(int id);
}

