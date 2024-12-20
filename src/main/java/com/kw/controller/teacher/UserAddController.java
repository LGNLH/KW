package com.kw.controller.teacher;

import cn.hutool.crypto.digest.MD5;

import com.kw.common.enumration.Role;
import com.kw.dao.UserDao;
import com.kw.pojo.dto.UserDto;
import com.kw.pojo.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/teacher/user/add")
public class UserAddController extends HttpServlet {

    UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        UserDto user = new UserDto();
//        // 根据前端传来的role参数，设置角色
//        if (req.getParameter("role")!= null && req.getParameter("role").equals("teacher")) {
//            user.setRole(Role.TEACHER);
//        } else {
//            user.setRole(Role.STUDENT);
//        }
        user.setRole(Role.STUDENT);
        user.setStudentId(Integer.parseInt(req.getParameter("studentId")));
        user.setUsername(req.getParameter("studentId"));
        user.setPassword(MD5.create().digestHex(req.getParameter("studentId")));
        user.setName(req.getParameter("name"));
        user.setClassId(Integer.parseInt(req.getParameter("classId")));
        user.setTeacherId(Integer.parseInt(req.getSession().getAttribute("teacherId").toString()));
        try {
            userDao.saveUser(user);
        } catch (Exception e) {
            req.setAttribute("message", "User already exists!");
            req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
        }
        log("user successfully added: ");
    }
}
