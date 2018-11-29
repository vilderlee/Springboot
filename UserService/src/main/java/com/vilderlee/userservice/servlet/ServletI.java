package com.vilderlee.userservice.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
public class ServletI extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext sc = request.getServletContext();

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);

        Test test = (Test) context.getBean("test");
        test.test();
//        request.getParameter("txCode");
//        request.getParameter("name");
//        request.getParameter("age");
//        request.getParameter("email");


    }
}
