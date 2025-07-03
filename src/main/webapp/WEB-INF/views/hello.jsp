<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring MVC - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Page d'Accueil</h2>
    <h4>Se connecter en tant que</h4>
    <a href="${pageContext.request.contextPath}/login/admin">Admin</a>
    <a href="${pageContext.request.contextPath}/login/bibliothecaire">Bibliothecaire</a>
    <a href="${pageContext.request.contextPath}/login/adherent">Adherent</a>
</body>
</html>