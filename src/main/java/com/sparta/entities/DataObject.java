package com.sparta.entities;

public abstract class DataObject implements Comparable<DataObject> {
    private final int id;

    public int getId() { return id; }

    public DataObject(int id) { this.id = id; }

    @Override
    public int compareTo(DataObject obj) {
        return Integer.compare(id, obj.id);
    }
}
