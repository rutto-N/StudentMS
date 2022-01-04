package com.student.enums;

import com.student.services.LoginAdminUserBean;
import com.student.services.LoginLecturerUserBean;
import com.student.services.LoginStudentUserBean;
import com.student.services.LoginUserBeanI;

public enum UserType {

    ADMIN(LoginAdminUserBean.class),
    LECTURER(LoginLecturerUserBean.class),
    STUDENT(LoginStudentUserBean.class),
    DEVELOPER(LoginStudentUserBean.class);

    private Class<? extends LoginUserBeanI> clazz;

    UserType(Class<? extends LoginUserBeanI> clazz){
        this.clazz = clazz;
    }

    public Class<? extends LoginUserBeanI> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends LoginUserBeanI> clazz) {
        this.clazz = clazz;
    }
}
