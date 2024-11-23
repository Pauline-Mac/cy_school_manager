<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="users-table">
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Classe</th>
	</tr>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:forEach items="${users}" var="user">
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
	</c:forEach>
</table>