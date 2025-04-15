<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Propiedades en Cards</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/CSS/card.css">

        <style>
            .property-card {
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }
            .property-card img {
                width: 100%;
                height: 280px; /* Ajusta el alto deseado */
                object-fit: cover; /* Mantiene la proporción */
            }
            .detalles-propiedad div {

                padding: 5px 10px;
                border-radius: 5px;
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 5px;
            }
            .card-img-top {
                height: 200px;
                object-fit: cover;
            }
            .price {
                font-size: 1.2em;
                font-weight: bold;
                color: #28a745;
            }

        </style>
    </head>

    <body>
        <nav class="nav nav-pills nav-fill bg-success">
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;" aria-current="page"
               href="adminAgregar.jsp">Agregar propiedad</a>
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;"
               href="adminVer.jsp">Ver propiedades</a>
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;"
               href="adminEditar.jsp">Editar propiedades</a>
        </nav>

        <div class="container mt-4">
            <div class="card shadow">
                <div class="card-body">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Tipo de propiedad</label>
                            <select class="form-select">
                                <option selected disabled>Seleccione...</option>
                                <option value="casa">Casa</option>
                                <option value="depto">Departamento</option>
                                <option value="campo">Campo</option>
                                <option value="terreno">Terreno</option>
                                <option value="galpon">Galpón</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-bold">Estado</label>
                            <select class="form-select">
                                <option selected disabled>Seleccione...</option>
                                <option value="venta">Venta</option>
                                <option value="alquiler">Alquiler</option>
                                <option value="alquiler">Arrendamiento</option>
                                <option value="vendido">Vendido</option>
                                <option value="alquilado">Alquilado</option>
                            </select>
                        </div>
                    </div>

                    <div class="mt-4 text-end">
                        <button class="btn btn-success px-4">Buscar</button>
                        <button class="btn btn-outline-secondary px-4">Limpiar</button>
                    </div>
                </div>
            </div>

            <!-- Cards de propiedades -->
            <div class="row g-3">
                     <c:forEach var="propiedad" items="${propiedades}">
                        <div class="col-lg-4 col-md-6 col-sm-12 py-3">
                            <div class="card property-card">

                                <c:if test="${not empty propiedad.imagen}">
                                    <img  src="${pageContext.request.contextPath}/${propiedad.imagen}" alt="Imagen de la propiedad" />
                                </c:if>

                                <!-- Resto de la tarjeta -->
                                <div class="card-body">
                                    <div class="detalles-propiedad">
                                        <h5 class="card-title">${propiedad.direccion}</h5>
                                        <p class="price"><strong>Precio:</strong> ${propiedad.precio}</p>
                                        <p class="card-text"><strong>Estado:</strong> ${propiedad.estado}</p>
                                        <a href="${pageContext.request.contextPath}/detallePropiedad?id=${propiedad.id_propiedad}" class="btn btn-primary">Ver Detalles</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
            </div>
        </div>

        <!-- Fila para las cards -->
        <!-- Bootstrap JS (opcional) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>