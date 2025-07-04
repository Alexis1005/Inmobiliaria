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
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body style="background-color: #dee2e6;" class="min-vh-100 d-flex align-items-center">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-10 col-xl-8">
                    <!-- Header -->
                    <div class="text-center mb-5">
                        <h1 class="mb-3 fw-bold text-primary">Inmobiliaria Moreno Galeano</h1>
                        <h5 class="text-muted mb-3">Panel del Agente</h5>
                        <p class="fs-5">Bienvenido, <strong class="text-primary">Juan Moreno</strong></p>
                    </div>

                    <!-- Botones del Dashboard -->
                    <div class="row g-3 justify-content-center">
                        <!-- Principal -->
                        <div class="col-12 col-sm-6 col-md-3">
                            <a href="${pageContext.request.contextPath}/principal" 
                               class="btn btn-success btn-lg w-100 fw-bold d-flex flex-column align-items-center py-3 shadow-sm">
                                <span class="fs-1 mb-2">🏠</span>
                                <span>Principal</span>
                            </a>
                        </div>

                        <!-- Subir Propiedad -->
                        <div class="col-12 col-sm-6 col-md-3">
                            <a href="${pageContext.request.contextPath}/subirPropiedad" 
                               class="btn btn-info btn-lg w-100 text-white fw-bold d-flex flex-column align-items-center py-3 shadow-sm">
                                <span class="fs-1 mb-2">📤</span>
                                <span>Subir Propiedad</span>
                            </a>
                        </div>

                        <!-- Editar Propiedades -->
                        <div class="col-12 col-sm-6 col-md-3">
                            <a href="${pageContext.request.contextPath}/editarPropiedad" 
                               class="btn btn-warning btn-lg w-100 text-white fw-bold d-flex flex-column align-items-center py-3 shadow-sm">
                                <span class="fs-1 mb-2">📝</span>
                                <span>Editar Propiedades</span>
                            </a>
                        </div>

                        <!-- Cerrar Sesión -->
                        <div class="col-12 col-sm-6 col-md-3">
                            <a href="${pageContext.request.contextPath}/logout.jsp" 
                               class="btn btn-danger btn-lg w-100 text-white fw-bold d-flex flex-column align-items-center py-3 shadow-sm">
                                <span class="fs-1 mb-2">🔒</span>
                                <span>Cerrar Sesión</span>
                            </a>
                        </div>
                    </div>

                    <!-- Footer opcional -->
                    <div class="text-center mt-5">
                        <small class="text-muted">© 2024 Inmobiliaria Moreno Galeano - Panel de Administración</small>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>