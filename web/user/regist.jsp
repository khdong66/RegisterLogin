<%--
  Created by IntelliJ IDEA.
  User: dongkaihua
  Date: 2022/12/30
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>regist.jsp</title>
    <script type="text/javascript">
        function _change(){
            //1. 获取<img>元素
            var ele = document.getElementById("verifyCode");
            ele.src = "/VerifyCodeServlet?xxx=" + new Date().getTime();
        }
    </script>
</head>
<body>
<h1>注册</h1>
<p style="color: red;font-weight: 900">${msg}</p>
<form action="<c:url value='/RegisterServlet'/> " method="post">
    用户名：<input type="text" name="username" value="${user.username}"/><p style="color: red;font-weight: 900">${errors.username}</p><br/>
    密  码：<input type="password" name="password" value="${user.password}"/><p style="color: red;font-weight: 900">${errors.password}</p><br/>
    验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3">
    <img src="/VerifyCodeServlet" id="verifyCode" border="2">
    <a href="javascript:_change()">换一张</a><p style="color: red;font-weight: 900">${errors.verifyCode}</p><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
