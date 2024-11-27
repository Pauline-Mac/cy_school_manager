<jsp:directive.page contentType="text/html; charset=UTF-8" />
<header class="professor-header">
	<a href="${pageContext.request.contextPath}/professor/index"><div class="logo"><h1>CY School Manager</h1></div></a>
	<a href="${pageContext.request.contextPath}/professor/profile">
		<div class="profile">
			<h2>${sessionScope.user != null && sessionScope.user.firstName != null ? sessionScope.user.firstName : 'Professor CY'}</h2>
			<div class="profile-logo">
				<img alt="My profile" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1024px-User-avatar.svg.png?20201213175635">
			</div>
		</div>
	</a>
</header>