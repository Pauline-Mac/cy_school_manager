<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="professor-notes-table">
    <tr><th>Nom du cours</th><th>Nombre d'étudiants</th></tr>
    <c:forEach var="classes" items="${classes}">
    <tr>
        <td>
            <a href=""><div class="item">${classes.className}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${classes.countStudent}</div></a>
        </td>
    </tr>
    </c:forEach>
</table>