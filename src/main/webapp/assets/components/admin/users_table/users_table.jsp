<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html; charset=UTF-8" />

<table class="users-table">
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th><c:choose>
			<c:when test="${users[0].role == 'PROFESSOR'}">
				Cours
			</c:when>
			<c:otherwise>
				Classe
			</c:otherwise>
		</c:choose></th>
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
		</tr>
	</c:forEach>
</table>