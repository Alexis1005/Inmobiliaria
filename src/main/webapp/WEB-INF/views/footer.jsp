<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- fondo verde, texto blanco y espacio vertical interno -->
<footer class="footer bg-success text-white py-4" id="foot"> 
    <div class="container">
        <div class="row m-0">
            <!-- Sección izquierda: Logo, redes sociales y aviso legal -->
            <div class="col-md-12 text-center text-md-start p-0 m-0 contenedorGeneral">

                <!-- Íconos de redes sociales -->
                <div class="social-icons">
                    <a href="https://www.facebook.com/juan.moreno.9400984" class="text-white fs-3" target="_blank" rel="noopener noreferrer">
                        <i class="bi bi-facebook"></i>
                    </a>
                    <a href="https://www.instagram.com/juanmoreno_inmobiliaria" class="text-white fs-3" target="_blank" rel="noopener noreferrer">
                        <i class="bi bi-instagram"></i>
                    </a>
                </div>
                <div>
                    <a href="index.html" class="logo-container">
                        <img src="${pageContext.request.contextPath}/imagenes/logo.png" alt="Moreno Galeano" class="footer-logo">
                    </a>
                    <p class="mb-0 small matricula2"><strong>Matrícula Corredor Inmobiliario N° 1716</strong></p>
                    <p class="mb-0 small matricula"><strong>Matrícula Martillero Público N° 1390</strong></p>
                </div>

                <!-- Aviso Legal -->
                <div class="col-12 aviso-legal p-0">
                    <p class="small small2">
                        Todas las medidas enunciadas son meramente orientativas. Las medidas exactas serán las que se expresen 
                        en el respectivo título de propiedad de cada inmueble. Los precios enunciados son meramente orientativos
                        y no contractuales.
                    </p>
                </div>
            </div>

            <!-- Sección derecha: Información de contacto -->
            <div class="col-md-4 text-center text-md-end contact-info">
                <p class="mb-3"><i class="bi bi-telephone-fill me-2"></i> (3446) 647684</p>
                <p class="mb-3"><i class="bi bi-envelope-fill me-2"></i> juancarlosmoreno_urdi@hotmail.com</p>
                <p><i class="bi bi-geo-alt-fill me-2"></i> 23 de Septiembre 146, Urdinarrain, Entre Ríos</p>
            </div>
        </div>
        <!-- Línea divisoria -->
        <div class="lineaDivisoria">
            <hr class="border-white lineaDivisoria mb-3" style="border-width: 3px;">
        </div>
        <!-- Derechos de Autor -->
        <div class="d-flex justify-content-between derechos small">
            <span>Copyright 2025 &copy; Inmobiliaria Moreno Galeano.</span>
            <span>By <a href="#" class="text-white text-decoration-none hover-link"><strong>KGV</strong> Software</a></span>
        </div>
    </div>
</footer>


