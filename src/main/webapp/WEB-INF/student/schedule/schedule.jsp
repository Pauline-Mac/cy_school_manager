<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CY School Manager</title>

    <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/layouts/student_left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/pages/student/index/index.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/student/header/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/student/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/student/notes_table/notes_tables.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="layout">
    <%@ include file='/assets/components/student/left_sidebar/left_sidebar.jsp' %>

    <main class="container">
        <jsp:include page='/assets/components/student/header/header.jsp'/>
        <div class="inner-container">
            <main class="notes-main">
                <h1>Emploi du temps</h1><br>
                <c:forEach var="course" items="${classes}">
                <ul>
                    <li>${course.className}</li>

                </ul>
                </c:forEach>
            </main>
            <jsp:include page='/assets/components/footer/footer.jsp'/>
        </div>
    </main>
</div>
</body>
</html>