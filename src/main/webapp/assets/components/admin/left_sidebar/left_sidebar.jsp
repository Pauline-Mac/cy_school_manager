<jsp:directive.page contentType="text/html; charset=UTF-8" />
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<aside class="left-sidebar">
	<nav class="container">
		<a href="students"><div class="item">Etudiants</div></a>
		<a href="professors"><div class="item">Professeurs</div></a>
		<a href="addclass"><div class="item">Ajouter un cours</div></a>
		<a href="addenrollment"><div class="item">Inscrire un étudiant</div></a>
	</nav>
	<footer class="logout">
		<a href="<%= request.getContextPath() + "/logout" %>"><img alt="Logout" src="https://cdn-icons-png.flaticon.com/512/126/126467.png"></a>
	</footer>
</aside>