<%@ include file='/WEB-INF/config/initializers/init.jsp' %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CY School Manager</title>
	<link href="<c:url value='${global_css_path}'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${pages_css_path}/login/login.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/login_form/login_form.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/button/button.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/header/header.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='${components_css_path}/footer/footer.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page='/assets/components/header/header.jsp'/>
	
	<main class="login-main">
		<h1 class="title">Bienvenu(e) !</h1>
		<div class="form-row">
			<div class="image-container">
				<img class = "img_1" alt="CY TECH" src="https://cytech.cyu.fr/medias/photo/20210910-142739-1-_1658393597514-jpg?ID_FICHE=111566">
			</div>
			<jsp:include page='/assets/components/login_form/login_form.jsp'/>
		</div>	
	</main>

	<jsp:include page="/assets/components/footer/footer.jsp"/>
</body>
</html>