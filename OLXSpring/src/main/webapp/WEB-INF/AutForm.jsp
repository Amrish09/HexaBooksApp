<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp" %>
<body>
    <%@include file="Header.jsp" %>
   <section>
   
   <h2 align="center">Book Details By Author</h2>
    <table id="alist">
    <c:if test="${alist != null }">
    <c:forEach items="${alist}" var="a">
    <tr>
    <td><a href="bookdetails?bookid=${a.bid}"><img src="viewimg?imgname=${a.bimg}" height="75" width="75"/></a></td>
    <td>${a.bname}</td>
    </tr>
    </c:forEach>
    </c:if>
    </table>
      </section>
  <%@include file="Footer.jsp" %>
</body>
</html>