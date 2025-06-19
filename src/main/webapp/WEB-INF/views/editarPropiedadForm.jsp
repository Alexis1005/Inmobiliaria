<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Editar Propiedad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .image-preview {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 5px;
        }
    </style>
</head>
<body class="bg-light">
    <div class="container py-5">
        <h1 class="mb-4">Editar Propiedad</h1>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>
        
        <form method="post" action="editarPropiedad" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${propiedad.id_propiedad}">
            
            <!-- Información Básica -->
            <div class="card mb-4">
                <div class="card-header">Información Básica</div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="direccion" class="form-label">Dirección *</label>
                            <input type="text" class="form-control" name="direccion" 
                                   value="${propiedad.direccion}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="precio" class="form-label">Precio *</label>
                            <input type="number" class="form-control" name="precio" 
                                   value="${propiedad.precio}" step="0.01" required>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" name="descripcion" rows="3">${propiedad.descripcion}</textarea>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label for="modalidad" class="form-label">Modalidad *</label>
                            <select class="form-select" name="modalidad" required>
                                <option value="venta" ${propiedad.modalidad == 'venta' ? 'selected' : ''}>Venta</option>
                                <option value="alquiler" ${propiedad.modalidad == 'alquiler' ? 'selected' : ''}>Alquiler</option>
                                <option value="arrendamiento" ${propiedad.modalidad == 'arrendamiento' ? 'selected' : ''}>Arrendamiento</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="estado" class="form-label">Estado *</label>
                            <select class="form-select" name="estado" required>
                                <option value="disponible" ${propiedad.estado == 'disponible' ? 'selected' : ''}>Disponible</option>
                                <option value="vendido" ${propiedad.estado == 'vendido' ? 'selected' : ''}>Vendido</option>
                                <option value="alquilado" ${propiedad.estado == 'alquilado' ? 'selected' : ''}>Alquilado</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="idTipo" class="form-label">Tipo *</label>
                            <select class="form-select" name="idTipo" required>
                                <c:forEach var="tipo" items="${tipos}">
                                    <option value="${tipo.id_tipo}" 
                                        ${propiedad.id_tipo == tipo.id_tipo ? 'selected' : ''}>
                                        ${tipo.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="idAgente" class="form-label">Agente *</label>
                            <select class="form-select" name="idAgente" required>
                                <c:forEach var="agente" items="${agentes}">
                                    <option value="${agente.id_agente}" 
                                        ${propiedad.id_agente == agente.id_agente ? 'selected' : ''}>
                                        ${agente.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="caracteristicasGenerales" class="form-label">Características Generales</label>
                        <textarea class="form-control" name="caracteristicasGenerales" rows="2">${propiedad.caracteristicasGenerales}</textarea>
                    </div>
                </div>
            </div>
            
            <!-- Características Específicas -->
            <div class="card mb-4">
                <div class="card-header">Características Específicas</div>
                <div class="card-body">
                    <c:forEach var="car" items="${caracteristicas}">
                        <div class="mb-3">
                            <label class="form-label">${car.nombre}</label>
                            <input type="text" class="form-control" 
                                   name="detalle_${car.id_caracteristica}" 
                                   value="${car.detalle}">
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- Fotos -->
            <div class="card mb-4">
                <div class="card-header">Fotos</div>
                <div class="card-body">
                    <div class="mb-3">
                        <label class="form-label">Fotos Actuales</label>
                        <div class="row">
                            <c:forEach var="foto" items="${fotos}">
                                <div class="col-md-3 mb-3">
                                    <img src="${pageContext.request.contextPath}/${foto.ruta_foto}" 
                                         class="image-preview"
                                         onerror="this.src='${pageContext.request.contextPath}/images/no-image.jpg'">
                                    <div class="form-check mt-2">
                                        <input class="form-check-input" type="checkbox" 
                                               name="fotosAEliminar" value="${foto.id_foto}" 
                                               id="foto_${foto.id_foto}">
                                        <label class="form-check-label" for="foto_${foto.id_foto}">
                                            Eliminar
                                        </label>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="imagen" class="form-label">Agregar Nuevas Fotos</label>
                        <input type="file" class="form-control" name="imagen" multiple accept="image/*">
                    </div>
                    
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" name="reemplazarTodas" id="reemplazarTodas">
                        <label class="form-check-label" for="reemplazarTodas">
                            Reemplazar todas las fotos existentes
                        </label>
                    </div>
                </div>
            </div>
            
            <!-- Botones -->
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-success me-2">Guardar Cambios</button>
                <a href="editarPropiedad" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>