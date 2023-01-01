<%--
  Created by IntelliJ IDEA.
  User: dongkaihua
  Date: 2022/12/30
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录</h1>
<p style="color: red;font-weight: 900">${msg}</p>
<form action="<c:url value='/LoginServlet'/> " method="post">
    用户名：<input type="text" name="username" value="${user.username}"/><br/>
    密  码：<input type="password" name="password" value="${user.password}"/><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
