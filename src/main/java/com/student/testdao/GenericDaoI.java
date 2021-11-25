package com.student.testdao;

import com.student.dao.ModelListWrapper;

public interface GenericDaoI<T>{

    T save(T t);
    ModelListWrapper<T> list(T filter, int start, int limit);
    T findById(int id);
    T findByName(String Name);
    T delete(int id);


}
