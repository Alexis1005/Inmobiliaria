<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sliderinterno.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sobreNosotros.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>Detalle propiedad</title>
    </head>
    <body>
        <!--    <!-- Carrusel de imágenes -->
        <div class="container d-flex align-items-center justify-content-center">
            <div class="section section-custom text-center w-100">
                <div id="carouselExampleIndicators" class="carousel slide my-2 shadow-lg w-75 m-auto" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <c:forEach var="foto" items="${fotos}" varStatus="status">
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}"
                                    class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}"
                                    aria-label="Slide ${status.count}"></button>
                        </c:forEach>
                    </div>
                    <div class="carousel-inner">
                        <c:forEach var="foto" items="${fotos}" varStatus="status">
                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                <img src="${pageContext.request.contextPath}/${foto.ruta_foto}" 
                                     class="img-fluid w-100 rounded imagen-ampliable" 
                                     alt="Foto de la propiedad" style="max-height:300px; object-fit:cover; background-color:#f8f9fa;">
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>

        <!--    <!-- Descripción (lado izquierdo) -->
        <div class="container mt-3">
            <div class="row g-2">
                <div class="col-md-8">
                    <div class="d-flex align-items-center mb-2">
                        <h1 class="me-3 mb-0">${propiedad.descripcion}</h1>
                        <c:if test="${propiedad.estado != 'disponible'}">
                            <c:choose>
                                <c:when test="${propiedad.estado == 'vendido'}">
                                    <span class="badge bg-danger text-light text-uppercase fs-4">${propiedad.estado}</span>
                                </c:when>
                                <c:when test="${propiedad.estado == 'alquilado' || propiedad.estado == 'arrendado'}">
                                    <span class="badge bg-warning text-light text-uppercase fs-4">${propiedad.estado}</span>
                                </c:when>
                            </c:choose>
                        </c:if>

                    </div>

                    <div class="row g-2">
                        <h3>Información Básica</h3>
                        <ul class="lista-custom caracteristicas-grid">
                            <li>Operación: <strong>${propiedad.modalidad}</strong></li>
                                <c:forEach var="caracteristica" items="${caracteristicas}">
                                <li>${caracteristica.nombre}: <strong>${caracteristica.detalle}</strong></li>
                                </c:forEach>
                        </ul>

                    </div>
                    <hr class="w-75">
                    <p>Descripción general de la ${tipoPropiedad.nombre}:</p>
                    <p>${propiedad.caracteristicasGenerales}</p>
                    <h3>USD$ <strong><fmt:formatNumber value="${propiedad.precio}" type="number" maxFractionDigits="0"/></strong></h3>
                    <hr class="w-75">
                </div>

                <!-- Formulario de contacto (lado derecho) -->
                <div class="col-md-4 mt-5 mb-3 position-relative">
                    <h4 class="position-absolute top-0 start-50 translate-middle bg-white px-3 rounded text-success">Contacto</h4>
                    <form method="post" action="${pageContext.request.contextPath}/enviarContacto" class="form-custom border border-2 border-success p-4 w-100 rounded">
                        <!-- Campo oculto para el ID de la propiedad -->
                        <input type="hidden" name="id_propiedad" value="${propiedad.id_propiedad}" />
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre completo</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>
                        <div class="mb-3">
                            <label for="tel" class="form-label">Teléfono</label>
                            <input type="text" class="form-control" id="tel" name="tel" required>
                        </div>
                        <div class="mb-3">
                            <label for="correo" class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" id="correo" name="correo" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="mensajec">Mensaje</label>
                            <textarea class="form-control" id="mensajec" name="mensaje" placeholder="Déjenos un mensaje y nos comunicaremos con usted!" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-success">Enviar</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal con carrusel completo -->
        <!-- Modal -->
        <div class="modal fade" id="modalCarrusel" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-centered">
                <div class="modal-content bg-dark text-white position-relative">

                    <!-- Botones de navegación, colocados directamente en el .modal-content -->
                    <button class="carousel-control-prev custom-control" type="button" data-bs-target="#modalCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Anterior</span>
                    </button>
                    <button class="carousel-control-next custom-control" type="button" data-bs-target="#modalCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Siguiente</span>
                    </button>

                    <div class="modal-body p-0">
                        <!-- Carrusel -->
                        <div id="modalCarousel" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <c:forEach var="foto" items="${fotos}" varStatus="status">
                                    <div class="carousel-item ${status.first ? 'active' : ''}">
                                        <img src="${pageContext.request.contextPath}/${foto.ruta_foto}" class="d-block w-100"
                                             style="max-height: 85vh; object-fit: contain;" alt="Imagen ampliada">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer border-0 bg-dark justify-content-center">
                        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!--     Ubicación -->
        <div class="w-100 px-3 px-md-5 my-4">
            <h3 class="text-center text-md-start mb-3">Ubicación</h3>
            <iframe class="w-100 rounded" src="https://www.google.com/maps?q=${propiedad.direccion}&output=embed"
                    height="400" style="border:0;" allowfullscreen loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script>
        document.querySelectorAll('.imagen-ampliable').forEach((img, index) => {
            img.addEventListener('click', () => {
                const modal = new bootstrap.Modal(document.getElementById('modalCarrusel'));
                modal.show();

                // Activar el slide correspondiente dentro del modal
                const slides = document.querySelectorAll('#modalCarousel .carousel-item');
                slides.forEach(slide => slide.classList.remove('active'));
                if (slides[index]) {
                    slides[index].classList.add('active');
                }
            });
        });
    </script>
</html>