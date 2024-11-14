<%@ page import="com.example.horoscopochino.modelo.Usuario" %>
<%@ page import="com.example.horoscopochino.dto.UsuarioObtenerDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Consulta de Horóscopo - Horóscopo Chino</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Oculta el div de resultado inicialmente */
    #resultadoHoroscopo {
      display: none;
    }
  </style>
</head>
<body>
<%
  UsuarioObtenerDTO usu = (UsuarioObtenerDTO) session.getAttribute("usuario");

  if (usu == null) {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    return;
  }
%>
<div class="container mt-5 text-center">
  <h2>Horóscopo Chino</h2>
  <h3>
    <%= (usu.getNombre() != null ? usu.getNombre().toUpperCase(java.util.Locale.ROOT) : "Usuario") %>,
    Conoce a tu animal del horóscopo chino:
  </h3>
  <h2>Tu animal es <strong class="text-success"><%=usu.getAnimal()%></strong></h2>



  <!-- Contenedor del resultado del horóscopo -->
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
