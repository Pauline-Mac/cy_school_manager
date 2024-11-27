<jsp:directive.page contentType="text/html; charset=UTF-8" />
<form class="user-profile" action="profile" method="POST">
	<div class="login-form-container">
		<h1 class="title">Mes coordonn�es</h1>
		<br><br>
		<div class="user-data">
			<div class="user-data-label">Email :</div>
			<div class="user-data-container">
				<input
					class="user-data-input"
					type="email"
					id="email"
					name="email"
					placeholder="Changer mon email"
					value=${sessionScope.user != null && sessionScope.user.email != null ? sessionScope.user.email : 'email@email.com'}
				>
			</div>
		</div>
		<br><br>
		<span>Mot de passe : </span>
		<input 
  			type="password"
  			id="password"
  			name="password"
  			placeholder="Changer mon mot de passe"
		>
		<br><br>
		<span>Prénom :</span>
		<input
			type="text"
			id="name"
			name="name"
			placeholder="Changer mon pr�nom"
			value=${sessionScope.user != null && sessionScope.user.firstName != null ? sessionScope.user.firstName : 'n/a'}
		>
		<br><br>
		<span>Nom de famille : </span>
		<input 
  			type="text"
  			id="family_name"
  			name="family_name"
  			placeholder="Changer mon nom de famille"
  			value=${sessionScope.user != null && sessionScope.user.lastName != null ? sessionScope.user.lastName : 'n/a'}
		>
		<br><br>
		<span>T�l�phone :</span>
		<input
			type="text"
			id="phone"
			name="phone"
			placeholder="Changer mon num�ro mobile"
			value=${sessionScope.user != null && sessionScope.user.phone != null ? sessionScope.user.phone : 'n/a'}
		>
		<br><br>
		<input class="button" type="submit" value="Mettre � jour">
	</div>
</form>