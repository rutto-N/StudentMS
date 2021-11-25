package com.student.services;

import com.student.enums.UserType;
import com.student.models.User;
import com.student.utils.MessageResponse;

@LoginUser(type = UserType.STUDENT)
public class LoginStudentUserBean implements LoginUserBeanI {

    @Override
    public MessageResponse checkUser(User user) throws Exception {

        return new MessageResponse(true,"Login Success","./student.jsp");
    }
}
