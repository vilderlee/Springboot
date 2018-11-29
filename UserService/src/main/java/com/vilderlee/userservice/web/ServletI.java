package com.vilderlee.userservice.web;

import com.vilderlee.common.environment.AbstractServlet;
import com.vilderlee.userservice.mq.EmailConfirm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
public class ServletI extends AbstractServlet {

    @Override
    protected void doService(String txCode, String body) {

        EmailConfirm emailConfirm = (EmailConfirm) applicationContext.getBean("emailConfirm");
        Class clz = emailConfirm.getClass();

        try {
            Method method = clz.getMethod(txCode, String.class);

            method.invoke(emailConfirm,body);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
