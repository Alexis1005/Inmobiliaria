<!-- desactiva la creacion automatica de sesion -->
<%@ page session="false" %>
<%
    // Obtener la sesión actual sin crear una nueva
    HttpSession sesion = request.getSession(false);
    
    // se invalida si existe una sesion
    if (sesion != null) {
        sesion.invalidate();
    }

    // redireccion al login
    response.sendRedirect("login.jsp");
%>
