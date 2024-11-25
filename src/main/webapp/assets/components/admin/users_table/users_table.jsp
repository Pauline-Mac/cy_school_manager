<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="users-table-div">
<table class="users-table">
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Classe</th>
		<th colspan="3">Action</th>
	</tr>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--	<c:forEach items="${users}" var="user">
		<tr>
			<td>
				<a href=""><div class="item">${user.email}</div></a>
			</td>
			<td>
				<a href=""><div class="item">${user.lastName}</div></a>
			</td>
			<td>
				<a href=""><div class="item">${user.firstName}</div></a>
			</td>
			<td>
				<a href=""><div class="item">

				</div></a>
			</td>
		</tr>
	</c:forEach>--%>

	<tr>
		<td>
			<div class="item">a.a@a.a</div>
		</td>
		<td>
			<div class="item">be</div>
		</td>
		<td>
			<div class="item">Theo</div>
		</td>
		<td>
			<div class="item">GSI3</div>
		</td>
		<td>
			<a href="show-user?uuid=abc"><div class="item">Voir</div></a>
		</td>
		<td>
			<a href="update-user?uuid=abc"><div class="item">Modifier</div></a>
		</td>
		<td>
			<a href="remove-user?uuid=abc"><div class="item">Supprimer</div></a>
		</td>
	</tr>


</table>
</div>