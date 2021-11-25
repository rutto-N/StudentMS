package com.student.ejb;

import com.student.models.User;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;
import com.student.utils.ResultWrapper;

public interface UserEJBI {

    User getUser(User user);

    MessageResponse login(User user) throws Exception;

    ResultWrapper allUsers();
    User save(User user) throws CustomException;
}
