<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajouter un Produit</title>
</head>
<body>
<h1>Ajouter un Produit</h1>
<form action="${pageContext.request.contextPath}/produits/add" method="post">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50"></textarea><br><br>

    <label for="prix">Prix:</label>
    <input type="number" id="prix" name="prix" min="0" step="0.01" required><br><br>

    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" min="0" required><br><br>

    <input type="submit" value="Ajouter le produit">
</form>
</body>
</html>