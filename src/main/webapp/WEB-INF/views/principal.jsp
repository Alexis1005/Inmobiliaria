<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Moreno Inmobiliaria</title>
        <!-- Bootstrap CSS con el hash corregido -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sliderinterno.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sliderinterno.css">

    </head>

    <body>

        <!-- Contenedor principal con el carrusel -->
        <div class="carousel-container position-relative">
            <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/imagenes/estacion.jpg" class="d-block w-100 img-carousel" alt="estacion">

                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/imagenes/tren.jpg" class="d-block w-100 img-carousel" alt="tren">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/imagenes/bayos.jpeg" class="d-block w-100 img-carousel" alt="bayos">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/imagenes/arenas.jpeg" class="d-block w-100 img-carousel" alt="arenas">
                    </div>
                </div>
            </div>

            <!-- Encabezado (Logo, Buscador y Menú) -->
            <div class="container-fluid position-absolute top-0 start-50 translate-middle-x w-100 d-flex
                 flex-column flex-md-row align-items-center justify-content-between p-3" style="z-index: 10;">

                <!-- Logo (Izquierda en escritorio, centrado en móviles) -->
                <a class="navbar-brand position-relative">
                    <img src="${pageContext.request.contextPath}/imagenes/logo.png" class="logo_nav img-fluid"  style="height: 11rem" alt="alt"/> 
                </a>

                <!-- Buscador (Centrado en Desktop, Abajo en Móvil) -->
                <div class="busqueda position-absolute top-0 start-50 translate-middle-x mt-3 d-flex
                     flex-column align-items-center w-100" style="z-index: 10;">
                    <h1 class="animated-title mb-2" style="color: #eff4f4">Encuentra tu lugar ideal</h2>
                        <form id="filtroForm" action="${pageContext.request.contextPath}/filtrarPropiedades" method="get" target="_blank">
                            <div class="d-flex flex-wrap justify-content-center align-items-center mt-2">
                                <div class="me-2">
                                    <select id="modalidad" name="modalidad" class="form-select navegacion">
                                        <option value="" disabled selected>MODALIDAD</option>
                                        <option value="venta">Venta</option>
                                        <option value="alquiler">Alquiler</option>
                                        <option value="alquiler">Arrendamiento</option>
                                    </select>
                                </div>
                                <div class="me-2">
                                    <select id="idTipo" name="idTipo" class="form-select navegacion">
                                        <option value="" disabled selected>TIPO DE PROPIEDAD</option>
                                        <c:forEach var="tipo" items="${tiposPropiedad}">
                                            <option value="${tipo.id_tipo}">${tipo.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-success">Buscar</button>
                            </div>
                        </form>
                </div>

                <!-- Menú desplegable (Derecha en escritorio, arriba en móviles) -->
                <div class="dropdown position-absolute top-0 end-0 mt-3 me-3" style="z-index: 1000;">
                    <button class="navbar-toggler menu-btn" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav">
                        <i class="fa-solid fa-bars fa-2x hambur" style="color: whitesmoke;"></i>
                    </button>

                    <div class="collapse navbar-collapse position-absolute top-100 end-0 p-3 menu-content" id="navbarNav"
                         style="background-color: rgb(100, 96, 96); height: auto; width: 250px; border-radius: 0.25rem; z-index: 10000;">
                        <ul class="navbar-nav text-center mx-3">
                            <li class="nav-item my-3 bordecito">
                                <a class="nav-link text-white fs-4 fw-bold" href="#">Inicio</a>
                            </li>
                            <li class="nav-item my-3 bordecito">
                                <a class="nav-link  text-white fs-4 fw-bold" href="#">La Empresa</a>
                            </li>
                            <li class="nav-item mt-3 mb-3 bordecito">
                                <a class="nav-link text-white fs-4 fw-bold" href="#">Contacto</a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <!-- Flecha para seguir navegando (CENTRADA DENTRO del carrusel) -->
            <div class="scroll-down position-absolute start-50 translate-middle-x mb-3"
                 style="z-index: 100; bottom: 5%; padding: 1%; background-color: white;
                 border-radius: 50%; cursor: pointer;">
                <i class="fa-solid fa-chevron-down fa-2x icono-scroll" style="color: black;"></i>
            </div>
        </div>

        <!-- Icono de WhatsApp -->
        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 100;">
            <i class="fab fa-whatsapp fa-2x bg-success text-light p-3 rounded-circle shadow"></i>
        </div>




        <div class="container">
            <div class="text-center bg-success text-white py-3 fs-4">
                <p class="mb-0">Propiedades disponibles</p>
            </div>
            <div class="row row-cols-1 row-cols-md-3 g-4 my-4">
                <c:forEach var="propiedad" items="${propiedades}">
                    <div class="col">
                        <div class="card tarjeta shadow">
                            <c:if test="${not empty propiedad.imagen}">
                                <img src="${pageContext.request.contextPath}/${propiedad.imagen}" class="card-img-top" style="height: 200px; object-fit: cover" alt="Imagen de la propiedad" />
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title text-start fw-semibold text-dark">${propiedad.descripcion}</h5>
                                <p class="card-text text-start text-muted">${propiedad.direccion}</p>
                                <hr class="my-2"style="color: #ff7300">
                                <div class="row my-3">
                                    <div class="col-6 mb-3">
                                        <div class="d-flex flex-column align-items-center my-3">
                                            <i class="fas fa-home fa-lg mb-2" style="color: #ff7300"></i>
                                            <span class="small text-muted">3 Ambientes</span>
                                        </div>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <div class="d-flex flex-column align-items-center my-3">
                                            <i class="fas fa-expand-arrows-alt fa-lg mb-2" style="color: #ff7300"></i>
                                            <span class="small text-muted">60 m²</span>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="d-flex flex-column align-items-center">
                                            <i class="fas fa-bath fa-lg mb-2" style="color: #ff7300"></i>
                                            <span class="small text-muted">3 Baños</span>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="d-flex flex-column align-items-center">
                                            <i class="fas fa-bed fa-lg mb-2" style="color: #ff7300"></i>
                                            <span class="small text-muted">3 Dormitorios</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex flex-column align-items-end mt-1">
                                    <p class="card-text" style="color: #ff7300"><strong>${propiedad.precio}</strong></p>
                                    <button class="btn btn-custom text-light fw-bold" onclick="window.location.href = '${pageContext.request.contextPath}/detallePropiedad?id=${propiedad.id_propiedad}'" style="background-color: #ff7300">
                                        Ver Detalles <i class="fas fa-arrow-right"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
                crossorigin="anonymous">
        </script>
        <script>
            // Desplazamiento al hacer clic en la flecha
            document.querySelector('.scroll-down').addEventListener('click', function () {
                window.scrollBy({top: window.innerHeight, behavior: 'smooth'});
            });
            // Redirección de WhatsApp
            document.querySelector('.whatsapp-icon').addEventListener('click', function () {
                window.open('https://wa.me/123456789', '_blank');
            });
            // Script para el formulario de filtrado
            document.addEventListener('DOMContentLoaded', function () {
                const modalidad = document.getElementById('modalidad');
                const tipoPropiedad = document.getElementById('tipoPropiedad');

                if (modalidad) {
                    modalidad.addEventListener('change', function () {
                        console.log('Modalidad cambiada:', this.value);
                    });
                }
                if (tipoPropiedad) {
                    tipoPropiedad.addEventListener('change', function () {
                        console.log('Tipo de propiedad cambiado:', this.value);
                    });
                }
            });
        </script>
    </body>
</html>