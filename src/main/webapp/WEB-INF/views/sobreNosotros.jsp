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
        <link href="https://fonts.googleapis.com/css2?family=Lato&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <style>
            body{
                background-color: #e6f0ff; /* celeste clarito */
            }
        </style>
    </head>
    <body>

        <nav class="nav nav-pills nav-fill navDetalle p-0 m-0" style="height: auto; background-color: #5dade2;">
            <div class="container h-100">
                <div class="row w-100 align-items-center">
                    <!-- Columna izquierda: Logo -->
                    <div class="col-4 text-start">
                        <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/principal">
                            <img src="${pageContext.request.contextPath}/imagenes/logo.png" alt="Logo" width="140px" height="110px"
                                 class="d-inline-block align-text-center footer-logonav">
                        </a>
                    </div>

                    <!-- Columna central: Título -->
                    <div class="col-4 text-center">
                        <h2 class="m-0 tituloSobreNosotros">
                            Sobre nosotros
                        </h2>
                        <br>
                        <p class="subtituloSobreNosotros" style="color: white">Quiénes somos y qué nos mueve</p>
                    </div>
                </div>
            </div>
        </nav>
        <div class="card shadow mb-4">
            <div class="row g-0">
                <!-- Título a la izquierda -->
                <div class="col-md-4 bg-light d-flex align-items-center justify-content-center">
                    <h3 class="section-title">La Empresa</h3>
                </div>

                <!-- Contenido con imagen -->
                <div class="col-md-8 p-4">
                    <div class="row">
                        <div class="col-md-8">
                            <p class="section-description">
                                En Inmobiliaria Moreno Galeano iniciamos nuestro camino en el año 2024, consolidándonos como una de las empresas más jóvenes del sector inmobiliario en nuestra ciudad. Nacimos con una fuerte vocación de servicio, impulsados por el deseo de acompañar a cada cliente en uno de los momentos más importantes de su vida: encontrar un lugar para vivir, invertir o concretar un proyecto personal. Cada operación inmobiliaria es, para nosotros, una oportunidad para construir confianza, aportar valor y formar parte de los sueños de quienes nos eligen.
                            </p>
                        </div>
                        <div class="col-md-4 d-flex flex-column align-items-center justify-content-center">
                            <img src="${pageContext.request.contextPath}/imagenes/sbn.jpg" alt="Juan Carlos Moreno Galeano" class="rounded shadow" style="width: 180px; height: 220px; object-fit: cover;">
                            <small class="mt-2 fw-bold text-center">Juan Carlos Moreno Galeano</small>
                            <small class="mt-2 fw-bold text-center text-muted">Martillero público y corredor inmobiliario</small>
                        </div>
                    </div>
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
                        Brindar un servicio inmobiliario transparente, cercano y centrado en las personas, adaptándonos a las necesidades específicas de cada cliente. Nuestra misión es simplificar cada etapa del proceso inmobiliario, construyendo relaciones sólidas basadas en el respeto, la honestidad y la confianza mutua.
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
                        Ser reconocidos como una inmobiliaria moderna, confiable y comprometida, destacada por la calidad humana de su equipo y por una atención verdaderamente personalizada. Aspiramos a crecer junto a nuestros clientes y contribuir activamente al desarrollo de nuestra comunidad, siendo parte de sus proyectos, sueños y logros.
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid mb-5">
        <div class="card shadow">
            <div class="card-body">
                <h4 class="mb-3 text-center">Nuestra Oficina</h4>
                <div style="width: 100%; height: 500px;">
                    <iframe 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d26863.09437045055!2d-58.92562842469012!3d-32.68905570895351!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95b045f45d04e705%3A0xb7c6ebb169f4cb72!2s23%20de%20Septiembre%20146%2C%20E2826%20Urdinarrain%2C%20Entre%20R%C3%ADos!5e0!3m2!1ses-419!2sar!4v1752890001993!5m2!1ses-419!2sar" 
                        width="100%" 
                        height="100%" 
                        style="border:0;" 
                        allowfullscreen="" 
                        loading="lazy" 
                        referrerpolicy="no-referrer-when-downgrade">
                    </iframe>
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