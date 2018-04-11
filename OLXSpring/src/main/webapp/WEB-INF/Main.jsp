<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="HeadSection.jsp"%>
<body>

	<%@include file="Header.jsp"%>
	<section>
		<div align="center">

			<nav>

				<label><b>Search By Category</b></label>
				<form action="searchbycat">
					<select name="ctg">
						<c:forEach items="${clist}" var="c">
							<option value="${c.cname}">${c.cname}</option>
						</c:forEach>
					</select> <br/>
					<button type="submit">Search</button>
				</form>
				<br />

				<form action="byauthor">
					<label><b>Search By Author</b></label> <input type="text"
						name="auth" />&nbsp
					<button type="submit">Search</button>
					<br /> <br />
				</form>
				<form action="bypublisher">
					<label><b>Search By Publisher</b></label> <input type="text"
						name="publ">&nbsp
					<button>Search</button>
					<br /> <br />
				</form>
				<form action="bybook">
					<label><b>Search By BookName</b></label> <input type="text"
						name="book">&nbsp
					<button>Search</button>
					<br /> <br />

				</form>

				<c:if test="${sessionScope.user ne null }">
					<a href="addfrm"><b>Add Book</b></a>
					<br />
					<br />
				</c:if>


			</nav>
		</div>

	</section>
	<%@include file="Footer.jsp"%>
</body>
</html>