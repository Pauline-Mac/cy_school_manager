<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CY School Manager</title>
    <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/layouts/student_left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/pages/student/schedule/schedule.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/student/header/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/student/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">

    <style>
        .schedule-container {
            overflow-y: auto; /* Permet le défilement vertical */
            max-height: 70vh; /* Limite la hauteur à 70% de la fenêtre */
            border: 1px solid #ccc; /* Ajoute une bordure pour mieux distinguer */
            background-color: #fff;
        }

        .schedule {
            display: grid;
            grid-template-columns: 100px repeat(5, 1fr); /* Horaires + 5 jours */
            grid-auto-rows: 60px; /* Une hauteur fixe pour chaque ligne */
            gap: 1px;
            background-color: #ccc;
        }

        .schedule-main {
            margin-bottom: 20px;
        }

        .schedule-header {
            display: contents;
        }

        .day-header, .time-header {
            background-color: #f4f4f4;
            padding: 10px;
            font-weight: bold;
            text-align: center;
        }

        .time-header {
            text-align: right;
        }

        .schedule-block {
            padding: 10px;
            text-align: center;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .empty-block {
            background-color: #fff;
        }
    </style>
</head>
<body>
<div class="layout">
    <jsp:include page='/assets/components/student/header/header.jsp'/>

    <main class="container">
        <%@ include file='/assets/components/student/left_sidebar/left_sidebar.jsp' %>
        <div class="inner-container">
            <div class="schedule-main">
                <h1>Mon emploi du temps</h1><br>
<%--                <a href="/student/schedule/export"><div class="button">Télécharger mon Emploi du Temps PDF</div></a>--%>
                <!-- Conteneur scrollable pour l'emploi du temps -->
                <div class="schedule-container">
                    <div class="schedule">
                        <div class="schedule-header">
                            <div class="time-header"></div>
                            <div class="day-header">Lundi</div>
                            <div class="day-header">Mardi</div>
                            <div class="day-header">Mercredi</div>
                            <div class="day-header">Jeudi</div>
                            <div class="day-header">Vendredi</div>
                        </div>

                        <!-- Création dynamique de l'emploi du temps -->
                        <%
                            String[] days = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
                            for (int hour = 8; hour <= 18; hour++) {
                        %>
                        <!-- Colonne des heures -->
                        <div class="time-header"><%= hour %>h - <%= hour + 1 %>h</div>

                        <!-- Rendu des cours -->
                        <% for (String day : days) {
                            boolean courseFound = false;
                            if (request.getAttribute("courses") != null) {
                                List<Map<String, Object>> courses = (List<Map<String, Object>>) request.getAttribute("courses");
                                for (Map<String, Object> course : courses) {
                                    if (course.get("startHour").equals(hour) && course.get("day").equals(day)) {
                                        courseFound = true;
                        %>
                        <div class="schedule-block"
                             style="grid-row: span <%= course.get("duration") %>; background-color: <%= course.get("color") %>;">
                            <%= course.get("name") %>
                        </div>
                        <%
                                        break;
                                    }
                                }
                            }
                            if (!courseFound) {
                        %>
                        <div class="empty-block"></div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </div>
                <!-- Fin du conteneur de l'emploi du temps -->
            </div>

            <div class="notes-main">
                <h1>Liste des cours</h1><br>
                <c:choose>
                    <c:when test="${empty classes}">
                        Aucun cours assigné
                    </c:when>
                </c:choose>
                <c:forEach var="course" items="${classes}">
                    <ul>
                        <li>${course.className}</li>

                    </ul>
                </c:forEach>
            </div>

        </div>
    </main>
    <jsp:include page='/assets/components/footer/footer.jsp'/>
</div>
</body>
</html>
