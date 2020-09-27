package com.jarry.demo1.java3y.ProxyMode.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.Connector
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 17:00
 */
@WebServlet(urlPatterns = "/servlet/laugh")
public class springbootServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get method");
        resp.getWriter().write("my doGet method");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post method");
        resp.getWriter().write("my doPost method");
        super.doPost(req, resp);
    }
}
