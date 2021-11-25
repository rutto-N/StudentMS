package com.student.utils;

public class MessageResponse {

    private String message;
    private Object object;
    private boolean success;
    private String redirectPage;


    public MessageResponse(boolean success, String message,String redirectPage) {
        this.message = message;
        this.success = success;
        this.redirectPage = redirectPage;
    }

    public MessageResponse(boolean success, String message) {
        this.message = message;
        this.success = success;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }
}
