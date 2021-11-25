package com.student.interfaces;

import com.student.models.User;

import java.util.List;

public interface CrudI<T> {
    User add(T t);

    List<T> view();

    boolean delete(T t);

    boolean edit(T t);


}
