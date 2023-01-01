<%--
  Created by IntelliJ IDEA.
  User: dongkaihua
  Date: 2022/12/30
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>welcome.jsp</title>
</head>
<body>
<h1>欢迎登录本系统</h1>
<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">滚！</c:when>
    <c:otherwise>
        ${sessionScope.sessionUser}
    </c:otherwise>
</c:choose>
</body>
</html>
