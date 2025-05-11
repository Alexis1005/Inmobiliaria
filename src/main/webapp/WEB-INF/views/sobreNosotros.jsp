<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sobre Nosotros</title>

        <!-- Bootstrap y estilos -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/sobreNosotros.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/sliderinterno.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <style>
            body{
                background-color: #e6f0ff; /* celeste clarito */
            }
        </style>
    </head>
    <body>

        <nav class="nav nav-pills nav-fill navDetalle p-0 m-0" style="height: 100px; background-color: #5dade2;">
            <div class="container h-100">
                <div class="row w-100 align-items-center">
                    <!-- Columna izquierda: Logo -->
                    <div class="col-4 text-start">
                        <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/principal">
                            <img src="${pageContext.request.contextPath}/imagenes/logo.png" alt="Logo" title="Inicio" width="140px" height="110px"
                                 class="d-inline-block align-text-center footer-logonav">
                        </a>
                    </div>

                    <!-- Columna central: Título -->
                    <div class="col-4 text-center textoNav">
                        <h2 class="m-0" style="color: whitesmoke;">
                            Sobre nosotros
                        </h2>
                        <br>
                        <p class="textoNav" style="color: white;">Cococé quienes somos y que nos mueve</p>
                    </div>
                </div>

                <!-- Menú desplegable posicionado con clases utilitarias -->
                <div class="dropdown position-absolute top-0 end-0 mt-3 me-3" style="z-index: 1000;">
                    <button class="navbar-toggler menu-btn" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" style="color: white!important;">
                        <i class="fa-solid fa-bars fa-2x hambur" style="color: whitesmoke!important;"></i>
                    </button>

                    <div class="collapse navbar-collapse position-absolute top-100 end-0 p-3 menu-content" id="navbarNav">
                        <ul class="navbar-nav hamburguesa text-center mx-3">
                            <li class="nav-item my-3 bordecito">
                                <a class="nav-link text-white fs-4 fw-bold" href="#">Inicio</a>
                            </li>
                            <li class="nav-item my-3 bordecito">
                                <a class="nav-link text-white fs-4 fw-bold" href="${pageContext.request.contextPath}/sobreNosotros">La Empresa</a>
                            </li>
                            <li class="nav-item mt-3 mb-3 bordecito">
                                <a class="nav-link text-white fs-4 fw-bold" href="#">Contacto</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container py-5 ">
            <div class="card shadow mb-4">
                <div class="row g-0">
                    <div class="col-md-4 bg-light d-flex align-items-center justify-content-center">
                        <h3 class="section-title">La Empresa</h3>
                    </div>
                    <div class="col-md-8 p-4">
                        <p class="section-description">
                            En Inmobiliaria Moreno Galeano, estamos comprometidos con brindar un servicio cercano, transparente y confiable. Nuestro entusiasmo, dedicación y profesionalismo nos diferencian. Acompañamos a cada cliente de forma personalizada en todo el proceso para que vivan una experiencia positiva.
                        </p>
                    </div>
                </div>
            </div>

            <div class="card shadow mb-4">
                <div class="row g-0">
                    <div class="col-md-4 bg-light d-flex align-items-center justify-content-center">
                        <h3 class="section-title">Misión</h3>
                    </div>
                    <div class="col-md-8 p-4">
                        <p class="section-description">
                            Brindar un servicio inmobiliario confiable, personalizado y transparente, enfocados en las necesidades de nuestros clientes. Nuestro objetivo es construir relaciones de confianza y respeto, simplificando cada etapa del proceso inmobiliario.
                        </p>
                    </div>
                </div>
            </div>

            <div class="card shadow mb-4">
                <div class="row g-0">
                    <div class="col-md-4 bg-light d-flex align-items-center justify-content-center">
                        <h3 class="section-title">Visión</h3>
                    </div>
                    <div class="col-md-8 p-4">
                        <p class="section-description">
                            Convertirnos en una inmobiliaria reconocida por nuestro compromiso, calidad y atención personalizada. Queremos ser parte de los proyectos y sueños de nuestros clientes, y contribuir activamente al crecimiento de nuestra comunidad.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" 
        crossorigin="anonymous"></script>
    </body>
</html>
