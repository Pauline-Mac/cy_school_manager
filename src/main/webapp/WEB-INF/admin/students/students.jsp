<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>

	<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/admin_left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/pages/student/student.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/popup/popup.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/popup/popup_success.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/popup/popup_error.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="layout">
		<jsp:include page='/assets/components/admin/header/header.jsp'/>
	
		<main class="container">
			<%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>
			
			<div class="inner-container">

				<c:choose>
					<c:when test="${error != null || success != null}">
						<%@ include file="/assets/components/popup/popup.jsp" %>
					</c:when>
				</c:choose>

				<div class="users-main">
					<div class="users-title">
						<h1>Etudiants</h1>
						<a class="add-user-link" href="adduser">Ajouter un étudiant</a>
					</div>

					<jsp:include page='/assets/components/admin/users_table/users_table.jsp'/>
				</div>
			</div>

		</main>

		<jsp:include page='/assets/components/footer/footer.jsp'/>
	</div>
</body>
</html>