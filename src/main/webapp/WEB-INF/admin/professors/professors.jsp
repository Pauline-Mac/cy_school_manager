
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>
	<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/pages/admin/users/users.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css>" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="layout">
		<%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>
	
		<main class="container">
			<jsp:include page='/assets/components/admin/header/header.jsp'/>
			
			<div class="inner-container">
				<h1>Professeurs</h1>
				<jsp:include page='/assets/components/admin/users_table/users_table.jsp'/>
			</div>
			
			<jsp:include page='/assets/components/footer/footer.jsp'/>
		</main>

	</div>
</body>
</html>