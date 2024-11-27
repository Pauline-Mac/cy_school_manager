<jsp:directive.page contentType="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CY School Manager</title>
		<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/layouts/admin_left_sidebar.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/components/user_profile/user_profile.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="container">
			<jsp:include page='/assets/components/admin/header/header.jsp'/>
			<%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>
			<div class="inner-container">
				<main>
					<%@ include file='/assets/components/user_profile/user_profile.jsp' %>
				</main>
				<jsp:include page='/assets/components/footer/footer.jsp'/>
			</div>
		</div>
	</body>
</html>