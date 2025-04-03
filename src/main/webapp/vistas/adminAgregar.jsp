<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/card.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <title>Panel Administrador - Agregar Propiedad</title>
    </head>
    <body>
        <nav class="nav nav-pills nav-fill bg-success">
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;" aria-current="page"
               href="/inmobiliaria/propiedadesController?accion=mostrarFormulario">Agregar propiedad</a>
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;"
               href="adminVer.jsp">Ver propiedades</a>
            <a class="nav-link fs-4 m-2 border border-2 border-light rounded" style="color: whitesmoke;"
               href="adminEditar.jsp">Editar propiedades</a>
        </nav>
        <div class="container">
            <!-- Mostrar mensaje de error si existe -->
            <c:if test="${not empty mensaje}">
                <div class="alert alert-danger mt-3" role="alert">
                    ${mensaje}
                </div>
            </c:if>
            <form action="/inmobiliaria/propiedadesController" method="post">
                <input type="hidden" name="accion" value="insertar">
                <input type="hidden" name="id_agente" value="1">
                <a href="adminAgregar.jsp"></a>

                <div class="text-center p-3 border border-light rounded col-md-6 m-auto">
                    <h4>Seleccione las imágenes</h4>
                    <input type="file" class="form-control mt-2" id="uploadImage" name="imagenes" accept="image/*" multiple>
                </div>

                <div class="row justify-content-center align-items-center">
                    <div class="col-md-6 mb-3">
                        <label class="fs-4" for="tipo">Tipo de propiedad</label>
                        <select class="form-select" id="tipo" name="id_tipo" required>
                            <option selected disabled>Seleccione el tipo</option>
                            <c:forEach items="${tiposPropiedades}" var="tipo">
                                <option value="${tipo.id_tipo}">${tipo.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="fs-4" for="modalidad">Modalidad</label>
                        <select class="form-select" id="modalidad" name="modalidad" required>
                            <option selected disabled>Seleccione la modalidad</option>
                            <c:forEach items="${modalidades}" var="modalidad">
                                <option value="${modalidad}">${modalidad}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center align-items-center">
                    <div class="col-md-6 mb-3">
                        <label for="titulo" class="fs-4">Título</label>
                        <input type="text" class="form-control" id="titulo" name="descripcion" placeholder="Título de la propiedad" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="ubicacion" class="fs-4">Ubicación</label>
                        <input type="text" id="ubicacion" class="form-control" name="direccion" placeholder="Ciudad, Provincia" required>
                    </div>
                </div>
                <div class="row justify-content-center align-items-center">
                    <div class="col-md-6 mb-3">
                        <label for="descripcion_general" class="fs-4">Descripción General</label>
                        <textarea class="form-control" id="descripcion_general" name="descripcion_general" placeholder="Agregue toda la información necesaria"></textarea>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="estado" class="fs-4">Estado de la propiedad</label>
                        <select class="form-select" id="estado" name="estado" required>
                            <option selected disabled>Seleccione el estado</option>
                            <c:forEach items="${estados}" var="estado">
                                <option value="${estado}">${estado}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-md-6 mb-3">
                        <label for="precio" class="fs-4">Precio</label>
                        <input type="number" step="0.01" id="precio" class="form-control" name="precio" required>
                    </div>
                </div>
                 
                <!-- Se incluyen el formulario de características (nuevo.jsp) y el listado (listar.jsp) -->
                
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-success mt-3 mb-3" style="width: 80%;">Agregar propiedad</button>
                </div>
            </form>
                <jsp:include page="/vista/nuevo.jsp"/>
                 <!-- Contenedor para la lista de características -->
            <div id="listaCaracteristicasContainer">
                <jsp:include page="/vista/listar.jsp" />
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
    </body>
</html>
