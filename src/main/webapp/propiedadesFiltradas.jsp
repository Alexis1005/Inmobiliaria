<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Propiedades Filtradas - Moreno Inmobiliaria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .property-card {
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
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
    <div class="container">
        <h1 class="text-center my-4">Propiedades Filtradas</h1>
        <div class="row">
            <c:forEach var="propiedad" items="${propiedadesFiltradas}">
                <div class="col-lg-4 col-md-6 col-sm-12 py-3">
                    <div class="card property-card">
                        <c:if test="${not empty propiedad.imagen}">
                            <img src="${pageContext.request.contextPath}/${propiedad.imagen}" class="card-img-top" alt="Imagen de la propiedad">
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title">${propiedad.direccion}</h5>
                            <p class="price">$${propiedad.precio}</p>
                            <p class="card-text"><strong>Estado:</strong> ${propiedad.estado}</p>
                            <a href="${pageContext.request.contextPath}/detallePropiedad?id=${propiedad.idPropiedad}" class="btn btn-primary">Ver Detalles</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty propiedadesFiltradas}">
                <p class="text-center">No se encontraron propiedades con los criterios seleccionados.</p>
            </c:if>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
            crossorigin="anonymous"></script>
</body>
</html>
