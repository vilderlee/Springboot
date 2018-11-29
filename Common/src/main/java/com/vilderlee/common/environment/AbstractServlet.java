package com.vilderlee.common.environment;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述:
 *
 * @package com.vilderlee.common.environment
 * @auther vilderlee
 * @date 2018/11/29 9:06 PM
 */
public abstract class AbstractServlet extends HttpServlet {

    protected ApplicationContext applicationContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        String txCode = req.getParameter("txCode");
        String body = req.getParameter("body");
        doService(txCode,body);
    }

    /**
     * 子类真正实现具体的选择交易
     */
    protected abstract void doService(String txCode,String body);

    private byte[] readSteam(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean var3 = false;

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputSteam.write(buffer, 0, len);
        }

        outputSteam.close();
        inputStream.close();

        return outputSteam.toByteArray();
    }
}
