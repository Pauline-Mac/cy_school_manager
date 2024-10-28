<%@ include file='/WEB-INF/config/initializers/init.jsp' %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>
	<link href="<c:url value='${global_css_path}'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${layouts_css_path}/left_sidebar.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${pages_css_path}/admin/users/users.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/button/button.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/admin/header/header.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/admin/left_sidebar/left_sidebar.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/admin/users_table/users_table.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/footer/footer.css'/>" rel="stylesheet" type="text/css">
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