<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <title>Formulario Características</title>
    </head>
    <body>
        <div class="container mt-3">
            <!-- Formulario para agregar nueva característica -->
            <div class="card mb-4">
                <div class="card-body">
                    <h3>Nueva Característica</h3>
                    <hr />
                    <form action="${pageContext.request.contextPath}/CaracteristicasController?accion=guardar" method="POST">
                        <input type="hidden" name="id_caracteristica" value="${caracteristica.id_caracteristica}">

                        <div class="mb-3">
                            <label>Nombre:</label>
                            <select class="form-control" name="nombre">
                                <option value="">Seleccionar característica</option>
                                <c:forEach var="item" items="${listaCaracteristicas}">
                                    <option value="${item}">${item}</option>
                                </c:forEach>
                            </select>
                            <input type="text" class="form-control mt-2" name="nombre_manual" maxlength="100" placeholder="Ingresar nueva característica">
                        </div>

                        <div class="mb-3">
                            <label>Detalle:</label>
                            <select class="form-control" name="detalle">
                                <option value="">Seleccionar detalle</option>
                                <c:forEach var="item" items="${sessionScope.caracteristicasTemporales}">
                                    <li>${item.nombre} - ${item.detalle}</li>
                                    </c:forEach>

                            </select>
                            <input type="text" class="form-control mt-2" name="detalle_manual" maxlength="100" placeholder="Ingresar nuevo detalle">
                        </div>

                        <button type="submit" class="btn btn-primary btn-sm">
                            <i class="fa fa-save"></i> Guardar
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
