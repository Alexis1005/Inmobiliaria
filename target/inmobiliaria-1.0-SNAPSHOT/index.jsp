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
    </head>
    <body style="background-color: #dee2e6">
        <h1>Bienvenido a Inmobiliaria Moreno Galeano</h1>
        <!--muestra que usuario esta logeado  -->
        <p>Usuario: <strong><%= sesion.getAttribute("usuario") %></strong></p> 
        <ul>
            <li><a href="${pageContext.request.contextPath}/subirPropiedad">Subir Propiedad</a></li>
            <li><a href="${pageContext.request.contextPath}/principal">Principal</a></li>
            <li><a href="logout.jsp" class="btn btn-danger">Cerrar sesión</a></li>
        </ul>
    </body>
</html>
