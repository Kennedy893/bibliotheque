<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prolongement - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <c:if test="${not empty message}">
        <div class="${messageType == 'error' ? 'alert-danger' : 'alert-success'}">
            ${message}
            <c:if test="${not empty alerte}">
                ${alerte}
            </c:if>
        </div>
    </c:if>

    <h2>Prolonger un pret</h2>

    <form action="${pageContext.request.contextPath}/rendre/save" method="post">
        
        <label>ID du pret :</label>
        <input type="number" name="id_pret"/><br/>
<%-- 
        <label>Nouvelle date de retour :</label>
        <input type="date" name="date_retour"/><br/> --%>

        <label>Surplus de jours :</label>
        <input type="number" name="surplus_jours"/><br/>

        <button type="submit">Confirmer le retour</button>

    </form>

    <h3>Liste des prets</h3>
    <table width="100" border="1">
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