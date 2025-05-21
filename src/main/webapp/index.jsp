<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio - Inmobiliaria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body style="background-color: #dee2e6;">

        <div class="container mt-5">
            <div class="text-center mb-4">
                <h1 class="mb-3">Inmobiliaria Moreno Galeano</h1>
                <h5 class="text-muted">Panel del Agente</h5>
                <p class="mt-3">Bienvenido, <strong><%= sesion.getAttribute("usuario") %></strong></p>
            </div>

            <div class="row justify-content-center g-4">
                <div class="col-md-3 text-center">
                    <a href="${pageContext.request.contextPath}/subirPropiedad" class="btn btn-info btn-lg w-100 text-white fw-bold">
                        📤 Subir Propiedad
                    </a>
                </div>
                <div class="col-md-3 text-center">
                    <a href="${pageContext.request.contextPath}/principal" class="btn btn-success btn-lg w-100 fw-bold">
                        🏠 Principal
                    </a>
                </div>
                <div class="col-md-3 text-center">
                    <a href="${pageContext.request.contextPath}/logout.jsp" class="btn btn-warning btn-lg w-100 text-white fw-bold">
                        🔒 Cerrar Sesión
                    </a>
                </div>
            </div>

        </div>

    </body>
</html>