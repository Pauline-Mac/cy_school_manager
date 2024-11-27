<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ page import="org.hibernate.Session" %>
<%@ page import="models.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon compte</title>
    <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/layouts/student_left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/pages/admin/users/users.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/popup/popup.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/popup/popup_success.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/components/popup/popup_error.css" rel="stylesheet" type="text/css">

</head>
<body>
<%

    User user = (User)session.getAttribute("user_data");
    Date date = user.getBirthDate();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    System.out.println(localDate.toString());
//    LocalDateTime l =

//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String s = localDate.format(formatter);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String s = formatter.format(user.getBirthDate());

//    s = "" + user.getBirthDate().getYear() + '-' + user.getBirthDate().getMonth() + '-' + user.getBirthDate().getDay();
%>


<div class="layout">

    <main class="container">


        <jsp:include page='/assets/components/admin/header/header.jsp'/>

        <div class="inner-container">

            <%
                Boolean userUpdateSuccess = (Boolean) session.getAttribute("user_update_success");
                if (userUpdateSuccess != null) {
                    if (userUpdateSuccess) {
                        %>
            <jsp:include page="/assets/components/popup/popup_user_update_success.jsp"/>
            <%
                    }
                    else {
                        %>
            <jsp:include page="/assets/components/popup/popup_user_update_error.jsp"/>
            <%
                    }
                }

                session.removeAttribute("user_update_success");
            %>



            <h1>Vos informations personnelles</h1>


            <table class="users-table">
                <tr>
                    <td>Prénom</td>
                    <td><%out.print(user.getFirstName());%></td>
                </tr>
                <tr>
                    <td>Nom</td>
                    <td><%out.print(user.getLastName());%></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%out.print(user.getEmail());%></td>
                </tr>
                <tr>
                    <td>Date de naissance</td>
                    <td><%out.print(s);%></td>
                </tr>
                <tr>
                    <td>Numéro de téléphone</td>
                    <td><%out.print(user.getPhone());%></td>
                </tr>
            </table>
            <br>


            <a class="button" href="update-user">Modifier ses informations personnelles</a>


        </div>

        <jsp:include page='/assets/components/footer/footer.jsp'/>
    </main>

</div>
</body>
</html>
