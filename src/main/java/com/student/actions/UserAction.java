package com.student.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.ejb.UserEJBI;
import com.student.enums.UserType;
import com.student.models.User;
import com.student.services.LoginUserBeanI;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;

import javax.ejb.EJB;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Login", urlPatterns = {"/login","/register","/users","/logout"})
public class UserAction extends BaseServlet {

    @EJB
    UserEJBI userEJB;

    @Inject @Any
    private Instance<LoginUserBeanI> loginUserBeans;


    private User user=new User();
   private MessageResponse messageResponse=null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        ObjectMapper mapper=new ObjectMapper();
        switch (action){
            case "/users":
                resp.setContentType("application/json");
                resp.getWriter().print(mapper.writeValueAsString(userEJB.allUsers()));
                break;
            case "/logout":
                HttpSession session = req.getSession();
                if (session != null) {
                    session.invalidate();
                    System.out.println("Session invalidated");
                }
                resp.sendRedirect("./index.jsp");
                break;
            case "/login":
                HttpSession session1 = req.getSession();
                List<MessageResponse> loginResponse = new ArrayList<MessageResponse>();

                if (session1.getAttribute("email") != null)
                    loginResponse.add((MessageResponse) session1.getAttribute("email"));

                handleResponse(resp, loginResponse);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        switch (action){
            case "/login":
                transform(user, req.getParameterMap());
                HttpSession session= req.getSession(true);
                session.setAttribute("email",user.getUsername());
                session.setAttribute("role",user.getUserType());
                try {
                    messageResponse=userEJB.login(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ObjectMapper mapper=new ObjectMapper();
                resp.getWriter().print(mapper.writeValueAsString(messageResponse));
                break;
            case "/register":
                    transform(user, req.getParameterMap());
                    user.setUserType(UserType.valueOf(user.getUserTypeStr().trim().toUpperCase()));
                try {
                    userEJB.save(user);
                } catch (CustomException e) {
                    e.getMessage();
                }
                resp.getWriter().print(new ObjectMapper().writeValueAsString(messageResponse));
                break;
        }

    }

}
