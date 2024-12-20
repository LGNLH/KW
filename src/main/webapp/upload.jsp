<%--
  Created by IntelliJ IDEA.
  User: 29688
  Date: 2024/12/19
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.apache.poi.ss.usermodel.*" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@ page import="java.io.*" %>
<%@ page import="com.kw.dao.UserDao" %>
<%@ page import="com.kw.pojo.entity.User" %>
<%@ page import="com.kw.common.enumration.Role" %>
<%@ page import="cn.hutool.crypto.digest.MD5" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传Excel文件处理</title>
</head>
<body>
<%
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
                //第一列是学号，第二列是姓名，用户与密码同学号
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        // 没有文件被上传
    }
%>
</body>
</html>
