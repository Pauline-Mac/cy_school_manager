<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>

	<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/admin_left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/pages/admin/index/index.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
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

				<div class="index-main">
					<h1>Bienvenue admin !</h1>
					<img class = "img_1" alt="CY TECH" src="https://cytech.cyu.fr/medias/photo/20210910-142739-1-_1658393597514-jpg?ID_FICHE=111566">
				</div>
			</div>
		</main>
		<jsp:include page='/assets/components/footer/footer.jsp'/>
	</div>
</body>
</html>