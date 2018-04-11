<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

</head>

<%@include file="HeadSection.jsp" %>
<body>
   <%@include file="Header.jsp" %>
   <section>
   <form action="loginval" method="post">
   <div class="container">
      <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required="required"/><br>
    <label ><b>Password</b></label>
   
    <input type="password" placeholder="Enter Password" name="password" required="required"/><br>
    <button type="submit">Login</button>
    </div>
    </form>
    <a href="adduser"><h2 align="right">Register</h2></a><br />
    </section>
  <%@include file="Footer.jsp" %>
</body>
</html>