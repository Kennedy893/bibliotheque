<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring MVC - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Acuueil BIBLIOTHECAIRE</h2>
    <a href="${pageContext.request.contextPath}/login/inscription">Inscription</a>
    <a href="${pageContext.request.contextPath}/preter/home">Enregistrement d'un pret</a>
    <a href="${pageContext.request.contextPath}/rendre/home">Rendre un livre</a>
    <a href="${pageContext.request.contextPath}/bibliothecaire/confirmation">Confirmer un prolongement de pret</a>
</body>
</html>