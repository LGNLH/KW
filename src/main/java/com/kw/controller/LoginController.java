package com.kw.controller;


import cn.hutool.crypto.digest.MD5;
import cn.hutool.jwt.JWTUtil;
import com.kw.dao.UserDao;
import com.kw.pojo.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserDao userDao =new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("start login.............");

        String username = req.getParameter("username");
        String password = MD5.create().digestHex(req.getParameter("password"));

        User user = userDao.getUserByUsername(username);
        if (user == null) {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            log("login success.");
            user.setPassword(null);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.getRequestDispatcher("teacher/user").forward(req, resp);
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
