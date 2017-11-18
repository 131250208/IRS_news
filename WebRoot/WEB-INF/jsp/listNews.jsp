<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>title</td>
        <td>profile</td>
    </tr>
    <c:forEach items="${list_news}" var="news" varStatus="st">
        <tr>
            <td>${news.id}</td>
            <td>${news.title}</td>
            <td>${news.profile}</td>   
        </tr>
    </c:forEach>
</table>
