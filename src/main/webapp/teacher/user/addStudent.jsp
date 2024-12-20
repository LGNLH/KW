<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户输入表单</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }

        input[type="text"],
        input[type="password"],
        input[type="file"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .submit-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }

        button {
            background-color: #f0ad4e;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #ec971f;
        }

        /* 用于显示消息的样式 */
        .message {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }
    </style>

</head>
<body>

<div class="form-container">
    <h2>用户信息输入</h2>
    <form action="add" method="post"> <!-- 提交到提交处理的 Servlet -->


        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" id="name" name="name" required>

        </div>

        <div class="form-group">
            <label for="studentId">学生 ID:</label>
            <input type="text" id="studentId" name="studentId">
        </div>

        <div class="form-group">
            <label for="classId">教学班 ID:</label>
            <input type="text" id="classId" name="classId">
        </div>


        <div class="form-group">
            <input type="submit" class="submit-btn" value="提交">
        </div>
    </form>
</div>

<div class="form-container">
    <h2>Excel文件导入用户信息</h2>
    <form action="teacher/user/upload" method="post" enctype="multipart/form-data">
        <label for="file">选择Excel文件:</label>
        <input type="file" id="file" name="file" accept=".xlsx,.xls">
        <button type="submit">上传并导入</button>
    </form>
</div>

<div class="message">
    ${message}
</div>


</body>
</html>
