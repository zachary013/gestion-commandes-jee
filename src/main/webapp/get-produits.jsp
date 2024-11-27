<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h1>Liste des Produits</h1>
<a href="${pageContext.request.contextPath}/produits/add">Ajouter un nouveau produit</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Prix</th>
        <th>Stock</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="produit" items="${produits}">
        <tr>
            <td>${produit.id}</td>
            <td>${produit.nom}</td>
            <td>${produit.description}</td>
            <td><fmt:formatNumber value="${produit.prix}" type="currency" currencySymbol="€"/></td>
            <td>${produit.stock}</td>
            <td>
                <a href="${pageContext.request.contextPath}/produits/edit?id=${produit.id}">Modifier</a>
                |
                <a href="#" onclick="deleteProduit(${produit.id})">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteProduit(id) {
        if (confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')) {
            fetch('${pageContext.request.contextPath}/produits/delete?id=' + id, { method: 'DELETE' })
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