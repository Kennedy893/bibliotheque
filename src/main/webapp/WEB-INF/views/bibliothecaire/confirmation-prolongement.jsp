<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation du prolongement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Confirmation du prolongement</h2>

    <form action="${pageContext.request.contextPath}/prolonger/confirmation" method="get">
        <label>ID du pret a prolonger :</label>
        <input type="number" value="${id_pret}" name="id_pret"/><br/>

        <label>Surplus de jours :</label>
        <input type="number" value="${surplus_jours}" name="surplus"/><br/>

        <label>Date de prolongement :</label>
        <input type="date" name="daty"/><br/>

        <button type="submit">Confirmer le prolongement</button>
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