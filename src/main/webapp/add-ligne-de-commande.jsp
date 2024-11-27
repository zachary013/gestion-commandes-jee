<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajouter une Ligne de Commande</title>
</head>
<body>
<h1>Ajouter une Ligne de Commande</h1>
<form action="${pageContext.request.contextPath}/lignes-de-commande/add" method="post">
    <label for="commandeId">Commande:</label>
    <select name="commandeId" id="commandeId" required>
        <c:forEach var="commande" items="${commandes}">
            <option value="${commande.id}">Commande #${commande.id}</option>
        </c:forEach>
    </select><br><br>

    <label for="produitId">Produit:</label>
    <select name="produitId" id="produitId" required>
        <c:forEach var="produit" items="${produits}">
            <option value="${produit.id}">${produit.nom} - ${produit.prix}€</option>
        </c:forEach>
    </select><br><br>

    <label for="quantite">Quantité:</label>
    <input type="number" id="quantite" name="quantite" min="1" value="1" required><br><br>

    <label for="prix">Prix unitaire:</label>
    <input type="number" id="prix" name="prix" min="0" step="0.01" required><br><br>

    <input type="submit" value="Ajouter la ligne de commande">
</form>
</body>
</html>