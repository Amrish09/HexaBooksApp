<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style >
.gallery {
	width: 80%;
	margin: 0 auto;
	display: flex;
	flex-wrap: wrap;
	/* background-color: orange; */
	padding: 10px;
}

.gallery .book {
	height: 80%;
	width: 200px;
	padding: 10px;
	background-color: #a5d9ef;
	box-shadow: 5px 5px 5px black;
	margin: 10px;
	text-decoration: none;
	color: white;
	background-webkit: 
}
</style>
</head>
<body>
 <%@include file="HeadSection.jsp"%>

	<%@include file="Header.jsp"%>
   <section style="background-color:white;">
   
   <h2 align="center">Book Details</h2>
   <div class="gallery">
   
    <c:forEach items="${blist}" var="b">
    <div class="book" align="center">
    <a href="bookdetails?bookid=${b.bid}"><img src="viewimg?imgname=${b.bimg}" height="75" width="75"/></a><br/>
    ${b.bname}<br/>
    
    
    </div>
    </c:forEach>
    </div>
   
    </section>
<%@include file="Footer.jsp"%>
</body>
</html>