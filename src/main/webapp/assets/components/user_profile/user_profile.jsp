<form class="user-profile" action="profile" method="POST">
	<div class="login-form-container">
		<h1 class="title">Mes coordonnées</h1>
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
					value="admin@cy-tech.fr"
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
			placeholder="Changer mon prénom"
			value="Donovan"
		>
		<br><br>
		<span>Nom de famille : </span>
		<input 
  			type="text"
  			id="family_name"
  			name="family_name"
  			placeholder="Changer mon nom de famille"
  			value="Cardenas"
		>
		<br><br>
		<span>Téléphone :</span>
		<input
			type="text"
			id="phone"
			name="phone"
			placeholder="Changer mon numéro mobile"
			value="+33666666666"
		>
		<br><br>
		<input class="button" type="submit" value="Mettre à jour">
	</div>
</form>