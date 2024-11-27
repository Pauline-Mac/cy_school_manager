<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="notes-main-div">
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
</div>