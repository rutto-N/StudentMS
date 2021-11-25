package com.student.interfaces;

import com.student.models.User;
import com.student.utils.ResultWrapper;

public interface UserDaoI {
    User add(User user);

    User findUser(User user);

    User findUserByUserName(User user);

    ResultWrapper getAll(User filter, int start, int limit);
}
