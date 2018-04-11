<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>
	<%@include file="Header.jsp"%>
	<section>

		<h2 align="center">Book Details</h2>
		<div align="center">
			<table>
				<tr>
					<img src="viewimg?imgname=${nlist.bimg}" height="75" width="75" />
					<br />
				</tr>
				<tr>
					<th><h3>Name</h3></th>
					<td>${nlist.bname}</td>
				</tr>
				<tr>
					<th><h3>Author</h3></th>
					<td>${nlist.author}</td>
				</tr>
				<tr>
					<th><h3>Publisher</h3></th>
					<td>${nlist.publisher}</td>
				</tr>
				<tr>
					<th><h3>Price</h3></th>
					<td>${nlist.price}</td>
				</tr>
			</table>
			<c:if test="${sessionScope.user ne null }">
				<a href="bookdownload?bookid=${nlist.bid}"><button type="submit">Download</button></a>
				<br />
			</c:if>


		</div>



	</section>
	<%@include file="Footer.jsp"%>
</body>
</html>