<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring MVC - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Inscription ADHERENT</h2>

    <form action="${pageContext.request.contextPath}/traitement-login/inscription" method="post">
        
        <label>Nom d'utilisateur :</label>
        <input type="text" name="nom"/><br/>

        <label>Mot de passe :</label>
        <input type="password" name="mdp"/><br/>

        <label>Type d'utilisateur :</label>
        <select name="typeAdherent">
            <c:forEach var="type" items="${typeAdherents}">
                <option value="${type.id}">${type.type_adherent}</option>
            </c:forEach>
        </select><br/>

        <label>Date d'inscription :</label>
        <input type="date" name="dateInscription"/><br/>

        <label>Date d'expiration :</label>
        <input type="date" name="dateExpiration"/><br/>

        <button type="submit">S'inscrire</button>
    </form>
</body>
</html>