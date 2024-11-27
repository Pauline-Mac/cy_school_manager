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
    <jsp:include page='/assets/components/professor/header/header.jsp'/>

    <main class="container">
        <%@ include file='/assets/components/professor/left_sidebar/left_sidebar.jsp' %>
        <div class="inner-container">
            <div class="notes-main">
                <h1>Mes notes attribuées</h1>
                <jsp:include page='/assets/components/professor/notes_table/notes_table.jsp'/>
            </div>
        </div>
    </main>
    <jsp:include page='/assets/components/footer/footer.jsp'/>
</div>
</body>
</html>