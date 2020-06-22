<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="Demo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List list=new ArrayList();
    list.add(new User("张三",10,new Date()));
    list.add(new User("李四",11,new Date()));
    list.add(new User("王五",12,new Date()));
    request.setAttribute("list",list);
%>
<table style="text-align:center;border: 1px solid olivedrab;width: 500px">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${list}" var="str" varStatus="s">
        <c:if test="${s.count mod 2==0}">
            <tr bgcolor="aqua">
                <td>${s.count}</td>
                <td>${str.name}</td>
                <td>${str.age}</td>
                <td>${str.birStr}</td>
            </tr>
        </c:if><c:if test="${s.count mod 2!=0}">
            <tr bgcolor="#7fff00">
                <td>${s.count}</td>
                <td>${str.name}</td>
                <td>${str.age}</td>
                <td>${str.birStr}</td>
            </tr>
        </c:if>

    </c:forEach>
</table>
</body>
</html>
