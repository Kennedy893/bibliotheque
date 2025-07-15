<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste exemplaires - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h3>Evolution Nombre des exemplaires</h3>
    <table width="200" border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Nombre des exemplaires</th>
                <th>Nom du livre</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listeExemplaires}" var="exemplaire">
                <tr>
                    <td>${exemplaire.id}</td>
                    <td>${exemplaire.daty}</td>
                    <td>${exemplaire.nb_exemplaires}</td>
                    <td>${exemplaire.livre.titre}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>