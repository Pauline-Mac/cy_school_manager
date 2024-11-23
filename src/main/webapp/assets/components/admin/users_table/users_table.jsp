<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="users-table">
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Classe</th>
	</tr>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:forEach items="${users}" var="student">
		<tr>
			<td>
				<a href=""><div class="item">${student.email}</div></a>
			</td>
			<td>
				<a href=""><div class="item">${student.lastName}</div></a>
			</td>
			<td>
				<a href=""><div class="item">${student.firstName}</div></a>
			</td>
			<td>
				<a href=""><div class="item">
					<c:choose>
						<c:when test="${student.studentGroup != null}">
							${student.studentGroup.studentGroupName}
						</c:when>
						<c:otherwise>
							Aucune classe assignée
						</c:otherwise>
					</c:choose>
				</div></a>
			</td>
		</tr>
	</c:forEach>
</table>