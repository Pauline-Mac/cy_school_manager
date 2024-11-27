<jsp:directive.page contentType="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>

	<link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/layouts/professor_left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/pages/professor/index/index.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/professor/header/header.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/professor/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="layout">
		<%@ include file='/assets/components/professor/left_sidebar/left_sidebar.jsp' %>
		
		<main class="container">
			<jsp:include page='/assets/components/professor/header/header.jsp'/>
			<div class="inner-container">
				<main class="index-main">
					<h1>Bienvenu(e) professor !</h1>
					<img class = "img_1" alt="CY TECH" src="https://cytech.cyu.fr/medias/photo/20210910-142739-1-_1658393597514-jpg?ID_FICHE=111566">
				</main>
				<jsp:include page='/assets/components/footer/footer.jsp'/>
			</div>
		</main>
	</div>
</body>
</html>