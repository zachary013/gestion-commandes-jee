<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Liste des Lignes de Commande</title>
</head>
<body>
<h1>Liste des Lignes de Commande</h1>
<a href="${pageContext.request.contextPath}/lignes-de-commande/add">Ajouter une nouvelle ligne de commande</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Commande ID</th>
        <th>Produit</th>
        <th>Quantité</th>
        <th>Prix unitaire</th>
        <th>Total</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="ligne" items="${lignesDeCommande}">
        <tr>
            <td>${ligne.id}</td>
            <td>${ligne.commande.id}</td>
            <td>${ligne.produit.nom}</td>
            <td>${ligne.quantite}</td>
            <td><fmt:formatNumber value="${ligne.prix}" type="currency" currencySymbol="€"/></td>
            <td><fmt:formatNumber value="${ligne.quantite * ligne.prix}" type="currency" currencySymbol="€"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/lignes-de-commande/edit?id=${ligne.id}">Modifier</a>
                |
                <a href="#" onclick="deleteLigneDeCommande(${ligne.id})">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteLigneDeCommande(id) {
        if (confirm('Êtes-vous sûr de vouloir supprimer cette ligne de commande ?')) {
            fetch('${pageContext.request.contextPath}/lignes-de-commande/delete?id=' + id, { method: 'DELETE' })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Erreur lors de la suppression');
                    }
                });
        }
    }
</script>
</body>
</html>