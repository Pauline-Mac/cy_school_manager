<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html; charset=UTF-8" />

<table class="professor-notes-table">
    <tr><th>Date</th><th>Matière</th><th>Nom et class de l'étudiant</th><th>Note</th></tr>
    <c:forEach var="notes" items="${notes}">
    <tr>
        <td>
            <a href=""><div class="item">${notes.createdAt}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${notes.enrollment.course.className}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${notes.enrollment.student.firstName} ${notes.enrollment.student.lastName} ${notes.enrollment.student.studentGroup.studentGroupName}</div></a>
        </td>
        <td>
            <a href=""><div class="item">${notes.value}</div></a>
        </td>
    </tr>
    </c:forEach>
</table>