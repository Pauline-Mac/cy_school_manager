
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                <form class="add-form" method="post" action="">

                    <div class="add-user-form-title">
                        Profil de l'utilisateur
                    </div>


                    <table>
                        <tr>
                            <td><label for="last_name">Nom</label></td>
                            <td><input name="last_name" id="last_name" class="add-user-input add-user-input-table" value="${user.lastName}"></td>
                        </tr>
                        <tr>
                            <td><label for="first_name">Prénom</label></td>
                            <td><input name="first_name" id="first_name" class="add-user-input add-user-input-table" value="${user.firstName}"></td>
                        </tr>
                        <tr>
                            <td><label for="email">Email</label></td>
                            <td><input name="email" id="email" type="email" class="add-user-input add-user-input-table" value="${user.email}"></td>
                        </tr>
                        <tr>
                            <td><label for="tel">Numéro de téléphone</label></td>
                            <td><input name="tel" id="tel" type="tel" class="add-user-input add-user-input-table" value="${user.phone}"></td>
                        </tr>
                        <tr>
                            <td><label for="birth_date">Date de naissance</label></td>
                            <td><input name="birth_date" id="birth_date" type="date" class="add-user-input add-user-input-table" value="${user.birthDate}"></td>
                        </tr>
                        <tr>
                            <td><label for="role">Statut</label></td>
                            <td>
                                <select name="role" id="role" class="add-user-input-select add-user-input-table" onchange="onRoleChange()">
                                    <c:choose>
                                        <c:when test="${user.role=='PROFESSOR'}">
                                            <option value="professor">Professeur</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="student">Eleve</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                        </tr>
                        <c:choose>
                            <c:when test="${user.role == 'STUDENT'}">
                                <tr id="group-id-tr">
                                    <td><label for="group_id">Numéro de groupe</label></td>
                                    <td><input type="number" id="group_id" name="group_id" class="add-user-input add-user-input-table" value="${user.studentGroup}" disabled="disabled"></td>
                                </tr>
                            </c:when>
                        </c:choose>

                    </table>


                    <div class="add-class">

                        <h4>Ajouter des cours</h4>

                        <div id="class-list">

                        </div>

                        <input type="text" id="add-class-input" class="add-user-input">
                        <input type="button" onclick="addClass()" value="Ajouter" class="add-class-button add-user-input">




                    </div>


                    <input class="add-user-form-button add-user-input" type="submit" value="Confirmer">

                </form>

            </div>
        </div>

    </main>
    <jsp:include page='/assets/components/footer/footer.jsp'/>

</div>
</body>
</html>