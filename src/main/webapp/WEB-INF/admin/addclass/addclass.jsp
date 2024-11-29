<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CY School Manager</title>
  <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/layouts/admin_left_sidebar.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/pages/student/student.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="layout">
  <jsp:include page='/assets/components/admin/header/header.jsp'/>

  <main class="container">

    <%@ include file='/assets/components/admin/left_sidebar/left_sidebar.jsp' %>
    <div class="inner-container">
      <div class="users-main">
        <h1>Ajouter une Classe</h1>
        <br><br>
        <form action="addclass" method="POST">
          <input type="hidden" name="action" value="search">
          <label for="new_class_name">Nom de la classe :</label>
          <br><br>
          <input type="text" name="new_class_name" id="new_class_name" value="${param.new_class_name}">
          <br><br>
          <h3>Rechercher un professeur</h3>
          <br><br>
          <label for="nom">Nom :</label>
          <input type="text" name="nom" id="nom" value="${param.nom}">
          <label for="prenom">Prénom :</label>
          <input type="text" name="prenom" id="prenom" value="${param.prenom}">
          <label for="email">Email :</label>
          <input type="email" name="email" id="email" value="${param.email}">
          <button type="submit">Rechercher</button>
        </form>
        <br><br>
        <c:if test="${not empty resultats}">
          <h4>Résultats :</h4>
          <form action="addclass" method="POST">
            <input type="hidden" name="action" value="createClass">
            <input type="hidden" name="new_class_name" value="${param.new_class_name}">
            <ul>
              <c:forEach var="professor" items="${resultats}">
                <li>
                  <input type="radio" name="selected_professor" value="${professor.email}">
                    ${professor.firstName} ${professor.lastName} - ${professor.email}
                </li>
              </c:forEach>
            </ul>
            <br><br>
            <button type="submit">Créer la classe</button>
          </form>
        </c:if>


        <c:if test="${not empty error}">
          <p style="color: red;">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
          <p style="color: green;">${message}</p>
        </c:if>
      </div>
    </div>

  </main>
  <jsp:include page='/assets/components/footer/footer.jsp'/>

</div>
</body>
</html>
