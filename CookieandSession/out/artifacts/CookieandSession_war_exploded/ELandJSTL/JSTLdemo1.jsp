<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--
       完成数字编号对应星期几案例
           1.域中存储一数字
           2.使用choose标签取出数字         相当于switch声明
           3.使用when标签做数字判断         相当于case
           4.otherwise标签做其他情况的声明  相当于default
   --%>
    <%
        request.setAttribute("num",40);
    %>

    <c:choose>
        <c:when test="${requestScope.num==1}">星期一</c:when>
        <c:when test="${requestScope.num==2}">星期二</c:when>
        <c:when test="${requestScope.num==3}">星期三</c:when>
        <c:when test="${requestScope.num==4}">星期四</c:when>
        <c:when test="${requestScope.num==5}">星期五</c:when>
        <c:when test="${requestScope.num==6}">星期六</c:when>
        <c:when test="${requestScope.num==7}">星期天</c:when>
        <c:otherwise>无此日期</c:otherwise>
    </c:choose>
</body>
</html>
