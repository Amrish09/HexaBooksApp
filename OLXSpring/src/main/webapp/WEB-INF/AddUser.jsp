<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<style >
body {
	width:100%;
	margin:0 auto;
	color:black;
}
</style>
</head>
<%@include file="HeadSection.jsp"%>

	<%@include file="Header.jsp"%>
	
<body style="width:100%;">
	<section>
		<h2>Add User</h2>
		
		 <form:form action="newUser" method="post" modelAttribute="ubean" enctype="multipart/form-data">
			<table >
				
				<tr>
					<td>User Name</td>
					<td><form:input path="uname"/></td>
					<td><form:errors path="uname"   element="div"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" path="pwd"/></td>
					<td><form:errors path="pwd"  element="div"/></td>
					
				</tr>
				<tr>
					<td colspan="2" align="center">
					    <button type="submit">Add</button>
					</td>
					
				</tr>
				
			</table>
			</form:form>
		
	</section>

	<%@include file="Footer.jsp"%>
</body>
</html>