
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员页面</title>
    <style>
        /* 基本样式 */
        table {
            width: 100%; /* 宽度 */
            border-collapse: collapse; /* 边框合并 */
            margin: 20px 0; /* 外边距 */
        }

        /* 表格头部 */
        th {
            background-color: #f2f2f2; /* 背景颜色 */
            padding: 10px; /* 内边距 */
            border: 1px solid #ddd; /* 边框 */
            text-align: left; /* 文本对齐 */
        }

        /* 表格单元格 */
        td {
            padding: 10px; /* 内边距 */
            border: 1px solid #ddd; /* 边框 */
            text-align: left; /* 文本对齐 */
        }

        /* 表格行 hover 效果 */
        tr:hover {
            background-color: #f5f5f5; /* 鼠标悬停时的背景颜色 */
        }
    </style>
</head>
<body>
    <a href="teacher/user/addStudent.jsp">添加一个学生</a>
    <a href="editStudent.jsp"></a>
    <div>
        <h1>用户列表</h1>
        <table >
            <tr>
                <th>用户ID</th>
                <th>姓名</th>
                <th>学生ID</th>
                <th>教师ID</th>
                <th>用户名</th>
                <th>密码</th>
                <th>角色</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.studentId}</td>
                    <td>${user.teacherId}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
