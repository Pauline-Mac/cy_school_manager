<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form class="user-profile" action="update-profile" method="POST">
	<div class="login-form-container">
		<h1 class="title">Mes coordonnées</h1>
		<%--<br><br>
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
			placeholder="Changer mon prénom"
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
			placeholder="Changer mon numéro de téléphone"
			value=${sessionScope.user != null && sessionScope.user.phone != null ? sessionScope.user.phone : 'n/a'}
		>
		<br><br>

		--%>



		<table>
			<tr>
				<td><label for="last_name">Nom</label></td>
				<td><input name="last_name" id="last_name" class="add-user-input add-user-input-table"
						   value=${sessionScope.user != null && sessionScope.user.lastName != null ? sessionScope.user.lastName : 'n/a'}></td>
			</tr>
			<tr>
				<td><label for="first_name">Prénom</label></td>
				<td><input name="first_name" id="first_name" class="add-user-input add-user-input-table"
						   value=${sessionScope.user != null && sessionScope.user.firstName != null ? sessionScope.user.firstName : 'n/a'}></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td><input name="email" id="email" type="email" class="add-user-input add-user-input-table"
						   value=${sessionScope.user != null && sessionScope.user.email != null ? sessionScope.user.email : 'email@email.com'}></td>
			</tr>
			<tr>
				<td><label for="tel">Numéro de téléphone</label></td>
				<td><input name="tel" id="tel" type="tel" class="add-user-input add-user-input-table"
						   value=${sessionScope.user != null && sessionScope.user.phone != null ? sessionScope.user.phone : 'n/a'}></td>
			</tr>
		</table>

		<input class="button" type="submit" value="Mettre à jour">
	</div>

</form>