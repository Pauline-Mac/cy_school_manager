<jsp:directive.page contentType="text/html; charset=UTF-8" />
<aside class="left-sidebar">
	<nav class="container">
		<a href="${pageContext.request.contextPath}/student/schedule"><div class="item">Emploi de temps</div></a>
		<a href="${pageContext.request.contextPath}/student/notes"><div class="item">Mes notes</div></a>
	</nav>
	<footer class="logout">
		<a href="<%= request.getContextPath() + "/logout" %>"><img alt="Logout" src="https://cdn-icons-png.flaticon.com/512/126/126467.png"></a>
	</footer>
</aside>