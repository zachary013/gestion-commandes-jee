<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Modifier une Commande</title>
</head>
<body>
<h1>Modifier une Commande</h1>
<form action="${pageContext.request.contextPath}/commandes/edit" method="post">
    <input type="hidden" name="id" value="${commande.id}">

    <label for="clientId">Client:</label>
    <select name="clientId" id="clientId" required>
        <c:forEach var="client" items="${clients}">
            <option value="${client.id}" ${commande.client.id == client.id ? 'selected' : ''}>${client.nom} ${client.prenom}</option>
        </c:forEach>
    </select><br><br>

    <div id="lignesCommande">
        <c:forEach var="ligne" items="${commande.lignes}" varStatus="status">
            <div class="ligneCommande">
                <select name="produitId" required>
                    <c:forEach var="produit" items="${produits}">
                        <option value="${produit.id}" ${ligne.produit.id == produit.id ? 'selected' : ''}>${produit.nom} - ${produit.prix}€</option>
                    </c:forEach>
                </select>
                <input type="number" name="quantite" min="1" value="${ligne.quantite}" required>
                <button type="button" onclick="supprimerLigne(this)">Supprimer</button>
            </div>
        </c:forEach>
    </div>

    <button type="button" onclick="ajouterLigne()">Ajouter une ligne</button><br><br>

    <input type="submit" value="Mettre à jour la commande">
</form>

<script>
    function ajouterLigne() {
        var container = document.getElementById('lignesCommande');
        var newLine = container.firstElementChild.cloneNode(true);
        newLine.querySelector('input[name="quantite"]').value = 1;
        container.appendChild(newLine);
    }

    function supprimerLigne(button) {
        var ligne = button.parentNode;
        ligne.parentNode.removeChild(ligne);
    }
</script>
</body>
</html>