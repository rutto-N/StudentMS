package com.student.services;

import com.student.models.User;
import com.student.utils.MessageResponse;

public interface LoginUserBeanI {

    MessageResponse checkUser(User user) throws Exception;
}
