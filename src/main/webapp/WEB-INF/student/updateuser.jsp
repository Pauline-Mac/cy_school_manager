<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ page import="models.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Modification des informations du compte</title>
  <link href="${pageContext.request.contextPath}/assets/global/global.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/layouts/left_sidebar.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/pages/admin/users/users.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/button/button.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/header/header.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/left_sidebar/left_sidebar.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/admin/users_table/users_table.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/components/footer/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
  User user = (User)session.getAttribute("user_data");
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  String s = formatter.format(user.getBirthDate());

%>


<div class="layout">

  <main class="container">
    <jsp:include page='/assets/components/admin/header/header.jsp'/>

    <div class="inner-container">

      <h1>Modification de vos données personnelles</h1>

      <form action="update-user" method="post">

        <table class="users-table">
          <tr>
            <td><label for="firstname">Prénom</label></td>
            <td><input name="firstname" id="firstname" value="<%out.print(user.getFirstName());%>"></td>
          </tr>
          <tr>
            <td><label for="lastname">Nom</label></td>
            <td>
              <input name="lastname" id="lastname" value="<%out.print(user.getLastName());%>">
            </td>
          </tr>
          <tr>
            <td><label for="email">Email</label></td>
            <td><input name="email" id="email" type="email" value="<%out.print(user.getEmail());%>"></td>
          </tr>
          <tr>
            <td><label for="birthdate">Date de naissance</label></td>
            <td><input name="birthdate" id="birthdate" type="date" value="<%out.print(s);%>"></td>
          </tr>
          <tr>
            <td><label for="tel">Numéro de téléphone</label></td>
            <td><input name="tel" id="tel" type="tel" value="<%out.print(user.getPhone());%>"></td>
          </tr>
        </table>

        <input class="button" type="submit" value="Envoyer">

      </form>



    </div>

    <jsp:include page='/assets/components/footer/footer.jsp'/>
  </main>

</div>

</body>
</html>
