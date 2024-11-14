<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lista de Usuarios</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">

<header>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Gestión de Usuarios</a>
    </div>
  </nav>
</header>

<div class="container mt-5">
  <h1 class="text-center mb-4">LISTADO DE USUARIOS</h1>

  <div class="d-flex justify-content-center mb-3">
    <form action="./" method="post">
      <button type="submit" class="btn btn-dark">Insertar nuevo Usuario</button>
    </form>
  </div>

  <div class="d-flex justify-content-center mb-3">
    <form class="d-flex" role="search">
      <input type="search" class="form-control me-2" placeholder="Buscar" aria-label="Buscar">
      <button class="btn btn-outline-dark" type="submit">Buscar</button>
    </form>
  </div>


  <c:if test="${not empty eliminarSatisfactorio}">
    <div class="alert alert-success text-center">
      Usuario eliminado con éxito.
    </div>
  </c:if>

  <table class="table table-hover table-dark">
    <thead class="table-warning">
    <tr>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>Animal   </th>
      <th>Actualizar</th>
      <th>Eliminar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="usuario" items="${usuarios}">
      <tr>
        <td>${usuario.nombre}</td>
        <td>${usuario.username}</td>
        <td>${usuario.animal}</td>
        <td>
          <form action="" method="post">
              <%--<input type="hidden" name="name" value="${usuario.name}">--%>
            <button type="submit" class="btn btn-warning">Actualizar</button>
          </form>
        </td>
        <td>
          <form action="" method="post">
              <%--<input type="hidden" name="name" value="${usuario.name}">--%>
            <button type="submit" class="btn btn-danger">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="AdminDashboard.jsp">Volver</a>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<footer class="bg-dark text-white text-center mt-auto">
  <div class="container-fluid">
    <p class="small m-1">© 2024 Gestión de Usuarios. Todos los derechos reservados.</p>
  </div>
</footer>

</body>
</html>
