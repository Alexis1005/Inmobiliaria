<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav class="nav nav-pills nav-fill navDetalle p-0 m-0" style="height: 100px; background-color: #5dade2;">
    <div class="container h-100">
        <div class="row w-100 align-items-center">
            <!-- Columna izquierda: Logo -->
            <div class="col-4 text-start">
                <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/principal">
                    <img src="${pageContext.request.contextPath}/imagenes/logo.png" alt="Logo" title="Inicio" width="140px" height="110px"
                         class="d-inline-block align-text-center footer-logonav logoR">
                </a>
            </div>

            <!-- Columna central: Título -->
            <div class="col-4 text-center textoNav">
                <c:choose>
                    <c:when test="${not empty nombreTipoPropiedad and not empty modalidadFiltrada}">
                        <c:set var="tipoCapitalizado" value="${fn:toUpperCase(fn:substring(nombreTipoPropiedad, 0, 1))}${fn:substring(nombreTipoPropiedad, 1, fn:length(nombreTipoPropiedad))}" />
                        <c:set var="modalidadCapitalizada" value="${modalidadFiltrada}"/>
                        <h2 class="m-0 textoF" style="color: whitesmoke;">
                            <c:out value="${tipoCapitalizado}" />s en <c:out value="${modalidadCapitalizada}" />
                        </h2>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <!-- Menú desplegable posicionado con clases utilitarias -->
        <div class="dropdown position-absolute top-0 end-0 mt-3 me-3" style="z-index: 1000;">
                <button class="navbar-toggler menu-btn" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav">
                <i class="fa-solid fa-bars fa-2x hambur" style="color: whitesmoke;"></i>
            </button>

            <div class="collapse navbar-collapse position-absolute top-100 end-0 p-3 menu-content" id="navbarNav">
                <ul class="navbar-nav hamburguesa text-center mx-3">
                    <li class="nav-item my-3 bordecito">
                        <a class="nav-link text-white fs-4 fw-bold" href="${pageContext.request.contextPath}/principal">Inicio</a>
                    </li>
                    <li class="nav-item my-3 bordecito">
                        <a class="nav-link text-white fs-4 fw-bold" href="${pageContext.request.contextPath}/sobreNosotros">La Empresa</a>
                    </li>
                    <li class="nav-item mt-3 mb-3 bordecito">
                        <a class="nav-link text-white fs-4 fw-bold" href="#foot">Contacto</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>