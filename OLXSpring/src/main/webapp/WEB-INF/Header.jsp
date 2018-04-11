<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
   <div class="container" >
	<span>HEXA BOOKS</span>
	<h2 style="font-family:Georgia;">Welcome ${sessionScope.user.uname}</h2>
	<c:if test="${sessionScope.user ne null }">
	<a href="logout"><h2 align="right">Logout</h2></a><br />
	</c:if>
	 <c:if test="${sessionScope.user eq null }">
	<a href="loginpage"><h2 align="right">Login</h2></a><br />
	</c:if>
	</div>
</header>
