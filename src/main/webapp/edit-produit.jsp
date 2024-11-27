<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modifier un Produit</title>
</head>
<body>
<h1>Modifier un Produit</h1>
<form action="${pageContext.request.contextPath}/produits/edit" method="post">
    <input type="hidden" name="id" value="${produit.id}">

    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" value="${produit.nom}" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50">${produit.description}</textarea><br><br>

    <label for="prix">Prix:</label>
    <input type="number" id="prix" name="prix" min="0" step="0.01" value="${produit.prix}" required><br><br>

    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" min="0" value="${produit.stock}" required><br><br>

    <input type="submit" value="Mettre à jour le produit">
</form>
</body>
</html>