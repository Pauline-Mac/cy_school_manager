<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html; charset=UTF-8" />

<table class="user-notes-table">
    <tr><th>Date</th><th>Matière</th><th>Libellé</th><th>Note</th></tr>
    <c:forEach var="matiere" items="${matieres}">
    <tr>
        <td>
            <a href=""><div class="item">${matiere.date}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${matiere.nom}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${matiere.libelle}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${matiere.note}</div></a>
        </td>
    </tr>
    </c:forEach>
</table>