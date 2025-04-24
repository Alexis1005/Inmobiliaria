<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login - Inmobiliaria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body style="background-color: #dee2e6;" class="d-flex justify-content-center align-items-center vh-100">

        <div class="bg-white p-4 rounded w-100" style="max-width: 400px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);">
            <h2 class="text-center text-success mb-4">Iniciar Sesión</h2>

            <!-- validacion y manejo de errores -->
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger text-center">
                    Datos incorrectos.
                </div>
            <% } %>

            <form action="login" method="post">
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" name="usuario" class="form-control" required autofocus>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="clave" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Entrar</button>
            </form>
        </div>

    </body>
</html>