package com.kw.controller.teacher;

import cn.hutool.crypto.digest.MD5;
import com.kw.common.enumration.Role;
import com.kw.dao.UserDao;
import com.kw.pojo.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@MultipartConfig
//@WebServlet("/teacher/user/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // 检查是否有文件被上传
        if (request.getPart("file") != null) {
            try {
                // 获取上传的文件
                Part filePart = request.getPart("file");

                InputStream fileContent = filePart.getInputStream();

                // 使用Apache POI读取Excel文件
                Workbook workbook = new XSSFWorkbook(fileContent);
                Sheet sheet = workbook.getSheetAt(0);

                // 遍历Excel表格中的每一行
                for (Row row : sheet) {
                    // 跳过第一行标题行
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    // 第一列是学号，第二列是姓名，用户名与密码同学号
                    String studentId = row.getCell(0).getStringCellValue();
                    String studentName = row.getCell(1).getStringCellValue();

                    User user = new User();
                    user.setStudentId(Integer.parseInt(studentId));
                    user.setName(studentName);
                    user.setUsername(studentId);
                    user.setPassword(MD5.create().digestHex(studentId));
                    user.setRole(Role.STUDENT);

                    // 这里可以添加代码将数据保存到数据库
                    UserDao userDao = new UserDao();
                    userDao.saveUser(user);
                }

                // 关闭工作簿和输入流
                workbook.close();
                fileContent.close();

                // 可以向客户端发送响应，例如上传成功的消息
                request.setAttribute("message", "Upload success.");
                request.getRequestDispatcher("/addStudent.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Upload already exists.");
                throw new ServletException(e);

            }
        } else {
            // 没有文件被上传
            request.setAttribute("message", "No file uploaded.");
        }
        request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
    }

}
