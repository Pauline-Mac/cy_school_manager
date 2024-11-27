<jsp:directive.page contentType="text/html; charset=UTF-8" />
<aside class="left-sidebar">
	<nav class="container">
		<a href="${pageContext.request.contextPath}/professor/schedule"><div class="item">Mes cours</div></a>
		<a href="${pageContext.request.contextPath}/professor/notes"><div class="item">Mes notes</div></a>
		<a href=""><div class="item">Attribuer une note</div></a>
	</nav>
	<footer class="logout">
		<a href="<%= request.getContextPath() + "/logout" %>"><img alt="Logout" src="https://cdn-icons-png.flaticon.com/512/126/126467.png"></a>
	</footer>
</aside>