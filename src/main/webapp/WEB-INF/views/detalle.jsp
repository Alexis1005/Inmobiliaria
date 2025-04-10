<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Detalle de Propiedad</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .carousel-inner img {
                height: 400px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Detalle de Propiedad</h2>
            <c:if test="${not empty propiedad}">
                <div class="card">
                    <!-- Carrusel de fotos -->
                    <!-- Carrusel de fotos -->
<div id="carouselFotos" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <c:forEach var="foto" items="${fotos}" varStatus="loop">
            <div class="carousel-item ${loop.first ? 'active' : ''}">
                <img src="${pageContext.request.contextPath}/imagenes/${fn:replace(foto.rutaFoto, ' ', '%20')}" class="d-block w-100" alt="Foto de la propiedad">
            </div>
        </c:forEach>
    </div>
    <!-- Controles si hay más de una foto -->
    <c:if test="${not empty fotos and fn:length(fotos) > 1}">
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselFotos" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Anterior</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselFotos" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Siguiente</span>
        </button>
    </c:if>
</div>


                    <div class="card-body">
                        <h5 class="card-title">${propiedad.direccion}</h5>
                        <p class="card-text"><strong>Precio:</strong> ${propiedad.precio}</p>
                        <p class="card-text"><strong>Descripción:</strong> ${propiedad.descripcion}</p>
                        <p class="card-text"><strong>Estado:</strong> ${propiedad.estado}</p>
                        <p class="card-text"><strong>Modalidad:</strong> ${propiedad.modalidad}</p>
                        <c:if test="${not empty propiedad.caracteristicas}">
                            <h6>Características:</h6>
                            <ul>
                                <c:forEach var="caracteristica" items="${propiedad.caracteristicas}">
                                    <li>${caracteristica}</li>
                                    </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty propiedad}">
                <div class="alert alert-warning" role="alert">
                    Propiedad no encontrada.
                </div>
            </c:if>
            <a href="${pageContext.request.contextPath}/principal" class="btn btn-primary mt-3">Volver al Inicio</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>