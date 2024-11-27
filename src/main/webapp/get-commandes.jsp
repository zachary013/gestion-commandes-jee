<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Liste des Commandes</title>
</head>
<body>
<h1>Liste des Commandes</h1>
<a href="${pageContext.request.contextPath}/commandes/add">Ajouter une nouvelle commande</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Client</th>
        <th>Total</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="commande" items="${commandes}">
        <tr>
            <td>${commande.id}</td>
            <td><fmt:formatDate value="${commande.dateCommande}" pattern="dd/MM/yyyy HH:mm"/></td>
            <td>${commande.client.nom} ${commande.client.prenom}</td>
            <td>
                <c:set var="total" value="0" />
                <c:forEach var="ligne" items="${commande.lignes}">
                    <c:set var="total" value="${total + (ligne.quantite * ligne.prix)}" />
                </c:forEach>
                <fmt:formatNumber value="${total}" type="currency" currencySymbol="€"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/commandes/edit?id=${commande.id}">Modifier</a>
                |
                <a href="#" onclick="deleteCommande(${commande.id})">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteCommande(id) {
        if (confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')) {
            fetch('${pageContext.request.contextPath}/commandes/delete?id=' + id, { method: 'DELETE' })
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