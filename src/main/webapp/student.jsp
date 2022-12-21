<%@ page import="web.ejb.StudentStateless" %>
<%@ page import="web.DTOs.CourseDTO" %>
<%@ page import="web.DTOs.StudentDTO" %><%--
  Created by IntelliJ IDEA.
  User: odtselmuun
  Date: 2022.12.11
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Choice</title>
</head>
<body>
<h1><%="Student"%>
</h1>
<a href="advisor-servlet">Advisor Page</a>
<br/>
<%
    StudentStateless studentBean = (StudentStateless) request.getSession().getAttribute("studentBean");
    Integer id = null;
    if (request.getSession().getAttribute("studentId") != null) {
        id = Integer.parseInt((String) request.getSession().getAttribute("studentId"));
    }
%>
<form action="student-servlet" method="post">
    <input name="matriculation" title="matriculation" type="number">
    <input type="submit">
</form>
<%if(id!=null && studentBean.getByMatriculation(id)!=null) {%>
<%StudentDTO d = studentBean.getByMatriculation(id);%>
<table id="table">
    <% String columns[] = {"Matriculation", "Name", "Surname", "Course/Vote"};%>
    <tr>
    <%for (String a : columns) { %>
    <td>
        <%=a%>
    </td>
    <%}%>
    </tr>
    <tr>
        <td><%=id%></td>
        <td><%=d.getName()%></td>
        <td><%=d.getSurName()%></td>
        <td>
            <%
                for(CourseDTO c : studentBean.getCourses(id)) {%>
                  <table>
                    <tr>
                        <td><%=c.getName()%></td>
                        <td><%=c.getVote()%></td>
                    </tr>
                  </table>
                <%}%>
        </td>
    </tr>
</table>
<%} else {%>
<p>Enter matriculation</p>
<%}%>
</body>
</html>
