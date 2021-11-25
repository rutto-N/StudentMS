package com.student.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//
//@WebFilter(filterName = "LoginFilter",
//        urlPatterns = "/*",
//        initParams = {
//                @WebInitParam(name = "excluded", value = "/register,/login,/index.jsp,/js/*")
//        }
//
//)
public class LoginFilter implements Filter {

    private List<String> excluded;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePattern = filterConfig.getInitParameter("excluded");
        excluded = Arrays.asList(excludePattern.split(","));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String servletPath = httpServletRequest.getServletPath();

        System.out.println("Servlet Path: " + servletPath);

        HttpSession session = httpServletRequest.getSession();

        if (session.isNew()) {
            session.invalidate();

            if (!excluded.contains(httpServletRequest.getServletPath()))
                ((HttpServletResponse) response).sendRedirect("http://127.0.0.1:8080/Project/index.jsp");

            else
                chain.doFilter(request, response);

        } else {
            chain.doFilter(request, response);

        }

    }






    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
