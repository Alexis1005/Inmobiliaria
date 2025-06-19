<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <title>Gestión de Propiedades</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .property-card {
                border-radius: 10px;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
                padding: 10px;
                min-height: 400px;
                transition: transform 0.2s;
            }
            .property-card:hover {
                transform: translateY(-2px);
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }
            .price {
                color: #28a745;
                font-weight: bold;
                font-size: 1.2em;
            }
            .property-image {
                width: 100%;
                height: 200px;
                object-fit: cover;
                border-radius: 8px;
            }
            .badge-custom {
                font-size: 0.8em;
            }
            .loading {
                display: none;
            }
        </style>
    </head>
    <body class="bg-light">

        <div class="container mt-4">
            <div class="row mb-4">
                <div class="col">
                    <!-- Logo (Izquierda en escritorio, centrado en móviles) -->
                    <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/index.jsp">
                        <button type="button" class="btn btn-outline-success btn-lg">🏠 <strong>Inicio</strong></button>
                    </a>
                    <h2 class="text-primary text-center">Edición de Propiedades</h2>
                </div>
            </div>

            <!-- Filtros -->
            <div class="card mb-4">
                <div class="card-header">
                    <h5 class="mb-0">Filtros de Búsqueda</h5>
                </div>
                <div class="card-body">
                    <form id="filtroForm" action="editarPropiedad" method="get">
                        <div class="row g-3">
                            <div class="col-md-4">
                                <select class="form-select" name="tipo" id="tipo">
                                    <option value="">Tipo de propiedad</option>
                                    <c:forEach var="tipo" items="${tiposPropiedad}">
                                        <option value="${tipo.id_tipo}" 
                                                ${param.tipo == tipo.id_tipo ? 'selected' : ''}>
                                            ${tipo.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-select" name="modalidad" id="modalidad">
                                    <option value="">Modalidad</option>
                                    <option value="venta" ${param.modalidad == 'venta' ? 'selected' : ''}>Venta</option>
                                    <option value="alquiler" ${param.modalidad == 'alquiler' ? 'selected' : ''}>Alquiler</option>
                                    <option value="arrendamiento" ${param.modalidad == 'arrendamiento' ? 'selected' : ''}>Arrendamiento</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-select" name="estado" id="estado">
                                    <option value="">Estado</option>
                                    <option value="disponible" ${param.estado == 'disponible' ? 'selected' : ''}>Disponible</option>
                                    <option value="vendido" ${param.estado == 'vendido' ? 'selected' : ''}>Vendido</option>
                                    <option value="alquilado" ${param.estado == 'alquilado' ? 'selected' : ''}>Alquilado</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12 d-flex gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-search"></i> Buscar
                                </button>
                                <a href="editarPropiedad" class="btn btn-outline-secondary">
                                    <i class="fas fa-times"></i> Limpiar
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Mensaje de éxito/error -->
            <c:if test="${param.success == '1'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle"></i> Propiedad actualizada exitosamente.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i> ${errorMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Resultados -->
            <div class="row mb-3">
                <div class="col">
                    <h5>
                        Propiedades encontradas: 
                        <span class="badge bg-primary">${fn:length(listaPropiedades)}</span>
                    </h5>
                </div>
            </div>

            <!-- Cards de Propiedades -->
            <div class="row" id="propiedadesContainer">
                <c:choose>
                    <c:when test="${empty listaPropiedades}">
                        <div class="col-12">
                            <div class="alert alert-info text-center">
                                <i class="fas fa-info-circle"></i>
                                No se encontraron propiedades con los filtros seleccionados.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="prop" items="${listaPropiedades}">
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card property-card h-100">
                                    <c:set var="listaFotos" value="${fotosMap[prop.id_propiedad]}"/>
                                    <c:choose>
                                        <c:when test="${not empty listaFotos}">
                                            <c:set var="primeraFoto" value="${listaFotos[0].ruta_foto}"/>
                                            <img src="${pageContext.request.contextPath}/${primeraFoto}"
                                                 class="card-img-top"
                                                 style="height:200px;object-fit:cover"
                                                 alt="Imagen de la propiedad"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/imagenes/no-image-available.png"
                                                 class="card-img-top"
                                                 style="height:200px;object-fit:cover"
                                                 alt="Sin imagen disponible"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="card-body d-flex flex-column">
                                        <div class="mb-2">
                                            <span class="badge bg-info badge-custom">${prop.modalidad}</span>
                                            <span class="badge ${prop.estado == 'disponible' ? 'bg-success' : 'bg-warning'} badge-custom">
                                                ${prop.estado}
                                            </span>
                                        </div>

                                        <h5 class="card-title">${prop.direccion}</h5>
                                        <p class="card-text flex-grow-1">${prop.descripcion}</p>
                                        <p class="price mb-2">$${prop.precio}</p>

                                        <div class="mt-auto">
                                            <!-- Reemplazar el botón actual -->
                                            <a href="${pageContext.request.contextPath}/editarPropiedad?id=${prop.id_propiedad}" 
                                               class="btn btn-primary w-100">
                                                <i class="fas fa-edit"></i> Editar Propiedad
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>



        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


    </body>
</html>