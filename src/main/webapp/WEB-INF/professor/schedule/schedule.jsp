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
    <link href="${pageContext.request.contextPath}/assets/components/professor/notes_table/notes_tables.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="layout">
    <%@ include file='/assets/components/professor/left_sidebar/left_sidebar.jsp' %>

    <main class="container">
        <jsp:include page='/assets/components/professor/header/header.jsp'/>
        <div class="inner-container">
            <main class="notes-main">
                <h1>Mes cours</h1><br>
                <jsp:include page='/assets/components/professor/classes_table/classes_table.jsp'/>
            </main>
            <jsp:include page='/assets/components/footer/footer.jsp'/>
        </div>
    </main>
</div>
</body>
</html>