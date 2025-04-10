<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Propiedad - Moreno Inmobiliaria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h2>Editar Propiedad</h2>
        <form action="${pageContext.request.contextPath}/editarPropiedad" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${propiedad.id_propiedad}">
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="${propiedad.direccion}" required>
            </div>
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="${propiedad.precio}" required>
            </div>
            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado" required>
                    <option value="Disponible" ${propiedad.estado == 'Disponible' ? 'selected' : ''}>Disponible</option>
                    <option value="Alquilado" ${propiedad.estado == 'Alquilado' ? 'selected' : ''}>Alquilado</option>
                    <option value="Vendido" ${propiedad.estado == 'Vendido' ? 'selected' : ''}>Vendido</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="caracteristicasGenerales" class="form-label">Características Generales</label>
                <textarea class="form-control" id="caracteristicasGenerales" name="caracteristicasGenerales" rows="5">${propiedad.caracteristicasGenerales}</textarea>
            </div>
            <div class="mb-3">
                <label for="imagen" class="form-label">Imagen (dejar en blanco para mantener la actual)</label>
                <input type="file" class="form-control" id="imagen" name="imagen">
                <c:if test="${not empty propiedad.imagen}">
                    <p>Imagen actual: ${propiedad.imagen}</p>
                    <img src="${pageContext.request.contextPath}/imagenes/${propiedad.imagen}" alt="Imagen actual" style="max-width: 200px;">
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            <a href="${pageContext.request.contextPath}/principal" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
