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

        <div class="container py-5 ">
                <a class="navbar-brand position-absolute d-md-block" style="top:1rem; left: 1rem" href="${pageContext.request.contextPath}/principal">
                    <img src="${pageContext.request.contextPath}/imagenes/logo.png" class="logo_nav img-fluid" style="height: 11rem" alt="Inicio"/> 
                </a>
                <div class="text-center mb-5 ">
                    <h1 class="display-5 fw-bold text-primary">Sobre Nosotros</h1>
                    <p class="lead text-muted">Conocé quiénes somos y qué nos mueve.</p>
                </div>
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
