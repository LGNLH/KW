package com.kw.controller.teacher;

import com.kw.dao.UserDao;
import com.kw.pojo.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/user")
public class UserController extends HttpServlet {

    private UserDao userDao=new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<User> users = userDao.getAllUsers();
        log("users: " + users);

        req.getSession().setAttribute("users", users);
       req.getRequestDispatcher("./tHome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //显示所有用户信息
        doGet(req, resp);
    }
}
