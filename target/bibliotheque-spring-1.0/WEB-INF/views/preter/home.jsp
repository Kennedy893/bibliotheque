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
    <c:if test="${not empty message}">
        <div class="${messageType == 'error' ? 'alert-danger' : 'alert-success'}">
            ${message}
        </div>
    </c:if>

    <h2>Preter un exemplaire</h2>

    <form action="${pageContext.request.contextPath}/preter/save" method="post">
        
        <label>ID de l'adherent :</label>
        <input type="number" name="id_adherent"/><br/>

        <label>ID de l'exemplaire :</label>
        <input type="number" name="id_exemplaire"/><br/>

        <label>Date de pret :</label>
        <input type="date" name="date_pret"/><br/>

        <label>Date de retour prevu :</label>
        <input type="date" name="date_retour_prevu"/><br/>

        <button type="submit">Confirmer le pret</button>

    </form>

    <h3>Liste des adherents</h3>
    <table width="100" border="1">
        <thead>
            <tr>
                <th>ID Adherent</th>
                <th>Nom</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listeAdherents}" var="adherent">
                <tr>
                    <td>${adherent.id}</td>
                    <td>${adherent.nom}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h3>Liste des exemplaires</h3>
    <table width="100" border="1">
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
</body>
</html>