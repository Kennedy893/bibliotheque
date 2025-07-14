<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation de la reservation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Confirmation de la reservation</h2>

    <c:if test="${not empty refus}">
        <div class="alert-danger">
            ${refus}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/reserver/confirmation" method="get">
        <label>ID de l'adherent :</label>
        <input type="number" value="${id_adherent}" name="id_adherent"/><br/>

        <label>ID de l'exemplaire :</label>
        <input type="number" value="${id_exemplaire}" name="id_exemplaire"/><br/>

        <label>Date de reservation :</label>
        <input type="date" name="date_reservation"
        value="<fmt:formatDate value='${date_reservation}' pattern='yyyy-MM-dd'/>"/>

        <label>Date de retour prevu :</label>
        <input type="date" value="${date_retour}" name="date_retour"/><br/>

        <br>
        <button type="submit">CONFIRMER la reservation</button>
    </form>
    <br>
    <form action="${pageContext.request.contextPath}/reserver/home" method="get">
        <button type="submit">REFUSER la reservation</button>
    </form>

<hr>

    <h3>Liste des exemplaires</h3>
    <c:if test="${empty listeExemplaires}">
        <p>Aucun exemplaire indisponible pour le moment</p>
    </c:if>

    <c:if test="${not empty listeExemplaires}">
        <table width="200" border="1">
            <thead>
                <tr>
                    <th>ID Exemplaire</th>
                    <th>Titre du livre</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeExemplaires}" var="exemplaire">
                    <tr>
                        <td>${exemplaire.id}</td>
                        <td>${exemplaire.livre.titre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>