<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring MVC - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hello.css">
</head>
<body>
    <h2>Login Bibliothecaire</h2>
    <form action="${pageContext.request.contextPath}/traitement-login/bibliothecaire" method="post">
        <input type="text" name="pseudo" placeholder="Identifiant" required>
        <input type="password" name="mdp" placeholder="Mot de passe" required>
        <button type="submit">Connexion</button>
    </form>
</body>
</html>