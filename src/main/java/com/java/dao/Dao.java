package com.java.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	T get(int id); //get Object by id
    
    List<T> getAll(); //get all objects
     
    void save(T t); //save object to the database if new
     
    void update(T t); //save object to the database if already exists
     
    void delete(T t); //delete object from the database 

}
