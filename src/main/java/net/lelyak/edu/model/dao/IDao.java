package net.lelyak.edu.model.dao;


import java.util.List;

public interface IDao<T> {

    public T findById(String id);

    public List<T> findListById(String id);
}
