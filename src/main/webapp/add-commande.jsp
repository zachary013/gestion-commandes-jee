<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajouter une Commande</title>
</head>
<body>
<h1>Ajouter une Commande</h1>
<form action="${pageContext.request.contextPath}/commandes/add" method="post">
    <label for="clientId">Client:</label>
    <select name="clientId" id="clientId" required>
        <c:forEach var="client" items="${clients}">
            <option value="${client.id}">${client.nom} ${client.prenom}</option>
        </c:forEach>
    </select><br><br>

    <div id="lignesCommande">
        <div class="ligneCommande">
            <select name="produitId" required>
                <c:forEach var="produit" items="${produits}">
                    <option value="${produit.id}">${produit.nom} - ${produit.prix}€</option>
                </c:forEach>
            </select>
            <input type="number" name="quantite" min="1" value="1" required>
        </div>
    </div>

    <button type="button" onclick="ajouterLigne()">Ajouter une ligne</button><br><br>

    <input type="submit" value="Enregistrer la commande">
</form>

<script>
    function ajouterLigne() {
        var container = document.getElementById('lignesCommande');
        var newLine = container.firstElementChild.cloneNode(true);
        container.appendChild(newLine);
    }
</script>
</body>
</html>