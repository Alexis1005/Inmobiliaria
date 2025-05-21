<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Propiedades Filtradas - Moreno Inmobiliaria</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">       
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sliderinterno.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sobreNosotros.css">

<body>
    <jsp:include page="/WEB-INF/views/navDetalles.jsp"></jsp:include>
        <div class="container my-4">
            <div class="row row-cols-1 row-cols-md-3 g-4 mx-0">
            <c:forEach var="propiedad" items="${propiedadesFiltradas}">
                <div class="col">
                    <div class="card tarjeta shadow">
                        <c:if test="${not empty propiedad.imagen}">
                            <img src="${pageContext.request.contextPath}/${propiedad.imagen}"
                                 class="card-img-top"
                                 style="height:200px;object-fit:cover"
                                 alt="Imagen de la propiedad"/>
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title text-start fw-semibold text-dark">
                                ${propiedad.descripcion}
                            </h5>
                            <p class="card-text text-start text-muted">
                                ${propiedad.direccion}
                            </p>
                            <hr class="my-2" style="color:#ff7300"/>
                            <c:set var="detalles" value="${detallesMap[propiedad.id_propiedad]}"/>
                            <!-- Definimos las características a mostrar -->
                            <c:set var="caracteristicasStr" value="ambientes,superficie,baños,dormitorios" />
                            <c:set var="caracteristicas" value="${fn:split(caracteristicasStr, ',')}" />

                            <div class="row mt-3 mb-0">
                                <c:forEach var="caract" items="${caracteristicas}">
                                    <div class="col-6 mb-3 divIcono">
                                        <div class="d-flex flex-column align-items-center">
                                            <!-- Seleccionamos el ícono según la característica -->
                                            <c:choose>
                                                <c:when test="${caract == 'ambientes'}">
                                                    <i class="fas fa-home fa-lg mb-3" style="color:#ff8533"></i>
                                                </c:when>
                                                <c:when test="${caract == 'superficie'}">
                                                    <i class="fas fa-expand-arrows-alt fa-lg mb-3" style="color:#ff8533"></i>
                                                </c:when>
                                                <c:when test="${caract == 'baños'}">
                                                    <i class="fas fa-bath fa-lg mb-3" style="color:#ff8533"></i>
                                                </c:when>
                                                <c:when test="${caract == 'dormitorios'}">
                                                    <i class="fas fa-bed fa-lg mb-3" style="color:#ff8533"></i>
                                                </c:when>
                                            </c:choose>

                                            <span class="small text-muted mb-4">
                                                <!-- Determinamos el valor según el tipo de propiedad -->
                                                <c:choose>
                                                    <c:when test="${fn:toLowerCase(propiedad.nombreTipo) == 'garaje'}">
                                                        –
                                                    </c:when>
                                                    <c:when test="${fn:toLowerCase(propiedad.nombreTipo) == 'terreno'}">
                                                        <!-- Para terrenos, mostramos solo 'superficie' y "–" para los demás -->
                                                        <c:if test="${caract == 'superficie'}">
                                                            <c:set var="valor" value="–" />
                                                            <c:forEach var="det" items="${detalles}">
                                                                <c:if test="${fn:toLowerCase(fn:trim(det.nombre)) == 'superficie'}">
                                                                    <c:set var="valor" value="${det.detalle}" />
                                                                </c:if>
                                                            </c:forEach>
                                                            <strong>Superficie</strong>: ${valor}
                                                        </c:if>
                                                        <c:if test="${caract != 'superficie'}">
                                                            –
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <!-- Para casas y departamentos, mostramos el detalle si está disponible -->
                                                        <c:set var="valor" value="–" />
                                                        <c:forEach var="det" items="${detalles}">
                                                            <c:if test="${fn:toLowerCase(fn:trim(det.nombre)) == caract or 
                                                                          (caract == 'dormitorios' and fn:toLowerCase(fn:trim(det.nombre)) == 'habitaciones')}">
                                                                <c:set var="valor" value="${det.detalle}" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <strong>
                                                            ${fn:toUpperCase(fn:substring(caract, 0, 1))}${fn:toLowerCase(fn:substring(caract, 1, fn:length(caract)))}
                                                        </strong>: ${valor}
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="d-flex flex-column align-items-end mt-1">
                                <p class="card-text" style="color:#ff7300">
                                    <strong>${propiedad.precio}</strong>
                                </p>
                                <button class="btn detalleDos btn-custom text-light fw-bold"
                                        onclick="window.location.href = '${pageContext.request.contextPath}/detallePropiedad?id=${propiedad.id_propiedad}'">
                                    Ver Detalles <i class="fas fa-arrow-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Mensaje cuando no hay propiedades -->
        <div class="d-flex justify-content-center align-items-center my-5">
            <c:if test="${empty propiedadesFiltradas}">
                <p class="text-center">No se encontraron propiedades con los criterios seleccionados.</p>
            </c:if>
        </div>
    </div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
crossorigin="anonymous"></script>
</body>