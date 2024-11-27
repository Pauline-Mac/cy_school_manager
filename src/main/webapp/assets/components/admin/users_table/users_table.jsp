<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="services.hibernate.HibernateFacade" %>
<%@ page import="models.HibernateEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="users-table">
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th><c:choose>
			<c:when test="${user_role == 'PROFESSOR'}">
				Cours
			</c:when>
			<c:otherwise>
				Classe
			</c:otherwise>
		</c:choose></th>
		<th colspan="3">Action</th>
	</tr>

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
				<a href="">
					<div class="item">
						<c:choose>
							<c:when test="${user.role == 'PROFESSOR'}">
								<c:choose>
									<c:when test="${not empty user.courses}">
										<c:forEach items="${user.courses}" var="course" varStatus="status">
											${course.className}
											<c:if test="${!status.last}">, </c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										Aucun cours assigné
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${user.studentGroup != null}">
										${user.studentGroup.studentGroupName}
									</c:when>
									<c:otherwise>
										Aucune classe assignée
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</a>
			</td>
			<td><a href="show-user?uuid=${user.uuid}">Afficher</a></td>
			<td><a href="update-user?uuid=${user.uuid}">Modifier</a></td>
			<td><a href="remove-user?uuid=${user.uuid}">Supprimer</a></td>
		</tr>
	</c:forEach>
</table>