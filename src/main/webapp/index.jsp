<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Gestion des Commandes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Gestion des Commandes</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/clients/list">Clients</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/produits/list">Produits</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/commandes/list">Commandes</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <div class="jumbotron">
    <h1 class="display-4">Bienvenue dans l'application de Gestion des Commandes</h1>
    <p class="lead">Cette application vous permet de gérer vos clients, produits et commandes.</p>
    <hr class="my-4">
    <div class="row">
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Gestion des Clients</h5>
            <p class="card-text">Gérez vos clients : ajout, modification, suppression.</p>
            <a href="${pageContext.request.contextPath}/clients/list" class="btn btn-primary">Accéder</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Gestion des Produits</h5>
            <p class="card-text">Gérez vos produits : ajout, modification, suppression.</p>
            <a href="${pageContext.request.contextPath}/produits/list" class="btn btn-primary">Accéder</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Gestion des Commandes</h5>
            <p class="card-text">Gérez vos commandes : création, modification, suivi.</p>
            <a href="${pageContext.request.contextPath}/commandes/list" class="btn btn-primary">Accéder</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>