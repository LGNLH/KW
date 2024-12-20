

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导入Excel表格</title>
</head>
<body>
<h1>导入Excel表格</h1>
<form action="teacher/user/upload" method="post" enctype="multipart/form-data">
    <label for="file">选择Excel文件:</label>
    <input type="file" id="file" name="file" accept=".xlsx,.xls">
    <button type="submit">上传并导入</button>
</form>
</body>
</html>

