package com.example.asm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        if (session.getAttribute("username") != null ) {
            return true;
        }

        // else if(session.getAttribute("username") != null && session.getAttribute("role").equals("user")){
        //     session.setAttribute("request-url", request.getRequestURL());
        //     session.setAttribute("mess", "your");
        //     response.sendRedirect("/login");
        //     return false;
        // }

        session.setAttribute("request-url", request.getRequestURL());
        session.setAttribute("mess", "your");
        session.setAttribute("chk", "true");

        response.sendRedirect("/login");
        return false;
    }

}
