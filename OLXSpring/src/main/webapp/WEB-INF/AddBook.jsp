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
		<h2>Add Book</h2>
		
		 <form:form action="add" method="post" modelAttribute="obean" enctype="multipart/form-data">
			<table >
				
				<tr>
					<td>Book Name</td>
					<td><form:input path="bname"/></td>
					<td><form:errors path="bname"   element="div"/></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><form:input path="author"/></td>
					<td><form:errors path="author"  element="div"/></td>
					
				</tr>
				<tr>
					<td>Publisher</td>
					<td><form:input path="publisher"/></td>
					<td><form:errors path="publisher"  element="div"/></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><form:input path="price"/></td>
					<td><form:errors path="price"  element="div"/></td>
				</tr>
				<tr>
					<td>Category</td>
					<td><form:select items="${cmap}"  path="cat.cid"/></td>
				</tr>
				<tr>
					<td>Choose image to upload</td>
					<td><input type="file" name="fname"/></td>
				</tr>
				<tr>
					<td>Choose file to upload</td>
					<td><input type="file" name="bookpdf"/></td>
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