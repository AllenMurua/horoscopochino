<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lista de Horóscopos</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">

<header>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Lista de Horóscopos</a>
    </div>
  </nav>
</header>

<div class="container mt-5">
  <h1 class="text-center mb-4">LISTADO DE HORÓSCOPOS</h1>


  <table class="table table-hover table-dark">
    <thead class="table-warning">
    <tr>
      <th>Animal</th>
      <th>Fecha de Inicio</th>
      <th>Fecha de Fin</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="horoscopo" items="${horoscopos}">
      <tr>
        <td>${horoscopo.animal}</td>
        <td>${horoscopo.fechaInicio}</td>
        <td>${horoscopo.fechaFin}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<footer class="bg-dark text-white text-center mt-auto">
  <div class="container-fluid">
    <p class="small m-1">© 2024 Gestión de Horóscopos. Todos los derechos reservados.</p>
  </div>
</footer>

</body>
</html>