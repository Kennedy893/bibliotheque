<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pret - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h3>Liste des prets</h3>
    <table width="200" border="1">
        <thead>
            <tr>
                <th>ID Pret</th>
                <th>Nom de l'adherent</th>
                <th>Titre de l'exemplaire</th>
                <th>Date de pret</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listePrets}" var="pret">
                <tr>
                    <td>${pret.id}</td>
                    <td>${pret.adherent.nom}</td>
                    <td>${pret.exemplaire.livre.titre}</td>
                    <td>${pret.date_pret}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>

    <h3>Historiques Statut Pret</h3>
    <table width="200" border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Statut du pret</th>
                <th>ID pret</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listeStatutPret}" var="statut_pret">
                <tr>
                    <td>${statut_pret.id}</td>
                    <td>${statut_pret.daty}</td>
                    <td>
                        <c:choose>
                            <c:when test="${statut_pret.statut == 0}">
                                Pret en cours
                            </c:when>
                            <c:when test="${statut_pret.statut == 1}">
                                Livre rendu
                            </c:when>
                            <c:otherwise>
                                Pret prolonge
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${statut_pret.pret.id}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>