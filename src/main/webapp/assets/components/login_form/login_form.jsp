<form class="login-form" action="${pageContext.request.contextPath}/login" method="POST">
	<div class="login-form-container">
		<h2 class="title">Connectez-vous :</h2>
		<input
			type="email"
			id="email"
			name="email"
			placeholder="Adresse e-mail"
			required 
		>
		<br><br>
		<input 
  			type="password"
  			id="password"
  			name="password"
  			placeholder="Mot de passe"
  			required
		>
		<br><br>
		<input class="button" type="submit" value="Connexion">
		<br>
		<a class="lost-password" href="">Mot de passe oubli� ?</a>
		<br>
		<hr>
		<br>
		<a href="admin/signup.jsp"><div class="button">Nouveau compte</div></a>
	</div>
</form>