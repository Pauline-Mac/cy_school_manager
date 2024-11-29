<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page='/assets/components/professor/header/header.jsp'/>

    <main class="container">
        <%@ include file='/assets/components/professor/left_sidebar/left_sidebar.jsp' %>
        <div class="inner-container">
            <div class="notes-main">
                <h1>Ajouter une  note</h1>
                <br><br>
                <form action="addnote" method="POST">
                    <input type="hidden" name="action" value="search">

                    <h3>Sélectionnez un cours</h3><br>
                    <select id="choosen_course" name="choosen_course">
                        <option value="">Sélectionner un cours</option>
                        <c:forEach var="course" items="${requestScope.courses}">
                            <option value="${course.className}">
                                    ${course.className}
                            </option>
                        </c:forEach>
                    </select><br><br>

                    <h3>Recherche d'Étudiants</h3><br><br>
                    <label for="nom">Nom :</label>
                    <input type="text" name="nom" id="nom" placeholder="Nom" value="${param.nom}">
                    <label for="prenom">Prénom :</label>
                    <input type="text" name="prenom" id="prenom" placeholder="Prénom" value="${param.prenom}">
                    <label for="studentgroupname">Classe :</label>
                    <input type="text" name="studentgroupname" id="studentgroupname" placeholder="Classe" value="${param.studentgroupname}">
                    <button type="submit">Rechercher</button>
                </form>

                <br><br>
                <h4>Résultats :</h4>

                <form action="addnote" method="POST">
                    <input type="hidden" name="action" value="addNote">

                    <ul id="results">
                        <c:forEach var="student" items="${requestScope.resultats}">
                            <li>
                                <input type="radio" name="selected_student" value="${student.email}">
                                    ${student.firstName} ${student.lastName} - ${student.studentGroup.studentGroupName}
                            </li>
                        </c:forEach>
                    </ul>
                    <br><br>

                    <label for="note_value">Note :</label>
                    <input type="number" name="note_value" id="note_value" placeholder="Entrez une note" required>
                    <br><br>

                    <button type="submit">Attribuer la note</button>
                </form>

            </div>
        </div>
    </main>
    <jsp:include page='/assets/components/footer/footer.jsp'/>
</div>
</body>
</html>