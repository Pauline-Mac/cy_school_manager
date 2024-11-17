<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>

	<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/admin/index/index.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="layout">
		<%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>
		
		<main class="container">
			<jsp:include page='/assets/components/admin/header/header.jsp'/>
			<div class="inner-container">
				<h1>Bienvenu(e) admin !</h1>
				<img class = "img_1" alt="CY TECH" src="https://cytech.cyu.fr/medias/photo/20210910-142739-1-_1658393597514-jpg?ID_FICHE=111566">
			</div>
			<jsp:include page='/assets/components/footer/footer.jsp'/>
		</main>
	</div>
</body>
</html>