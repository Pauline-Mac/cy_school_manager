<jsp:directive.page contentType="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CY School Manager</title>

    <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/layouts/admin_left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/pages/admin/users/users.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/form/add_user_form.css" rel="stylesheet" type="text/css">


    <script src="${pageContext.request.contextPath}/assets/components/admin/form/add_user_form.js"></script>

</head>
<body>
<div class="layout">
    <jsp:include page='/assets/components/admin/header/header.jsp'/>

    <main class="container">
        <%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>

        <div class="inner-container">
            <div class="users-main">

                <jsp:include page="/assets/components/admin/form/add_user_form.jsp"/>

            </div>
        </div>

    </main>
    <jsp:include page='/assets/components/footer/footer.jsp'/>

</div>
</body>
</html>