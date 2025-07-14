<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <c:if test="${not empty message}">
        <div class="${messageType == 'error' ? 'alert-danger' : 'alert-success'}">
            ${message}
        </div>
    </c:if>

    <h2>Reserver un livre</h2>

    <form action="${pageContext.request.contextPath}/reserver/save" method="post">
        
        <label>ID de l'adherent :</label>
        <input type="number" name="id_adherent"/><br/>

        <label>ID de l'exemplaire :</label>
        <input type="number" name="id_exemplaire"/><br/>

        <label>Date de reservation :</label>
        <input type="date" name="date_reservation"/><br/>

        <button type="submit">CONFIRMER la reservation</button>

    </form>

    <h3>Liste des prets</h3>
    <table width="200" border="1">
        <thead>
            <tr>
                <th>ID Pret</th>
                <th>Nom de l'adherent</th>
                <th>Titre de l'exemplaire</th>
                <th>Date de pret</th>
                <th>Date de retour prevu</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listePrets}" var="pret">
                <tr>
                    <td>${pret.id}</td>
                    <td>${pret.adherent.nom}</td>
                    <td>${pret.exemplaire.livre.titre}</td>
                    <td>${pret.date_pret}</td>
                    <td>${pret.date_retour_prevu}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>