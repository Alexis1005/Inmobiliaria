<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Propiedades</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .property-card {
                border-radius: 10px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                padding: 10px;
                min-height: 400px;
            }
            .price {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body class="bg-light">

        <div class="container mt-4">

            <!-- Formulario de filtrado (GET a /editarPropiedad) -->
            <form class="mb-4" action="editarPropiedad" method="get">
                <div class="row g-2">
                    <div class="col-md-5">
                        <select id="tipoPropiedad" class="form-select" name="tipo">
                            <option value="" disabled selected>Tipo de propiedad</option>
                            <!-- Ejemplo: si tienes lista de TiposPropiedad en request -->
                            <c:forEach var="t" items="${tiposPropiedad}">
                                <option value="${t.id_tipo}">${t.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <select class="form-select" name="modalidad">
                            <option value="" disabled selected>MODALIDAD</option>
                            <option value="venta">Venta</option>
                            <option value="alquiler">Alquiler</option>
                            <option value="arrendamiento">Arrendamiento</option>
                        </select>
                    </div>
                    <div class="col-md-2 d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-success">Buscar</button>
                        <a href="editarPropiedad" class="btn btn-secondary">Limpiar</a>
                    </div>
                </div>
            </form>

            <!-- Aquí van las cards resultantes -->
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach var="prop" items="${listaPropiedades}">
                    <div class="col d-flex justify-content-center">
                        <div class="card mb-4 property-card" style="width: 18rem;">
                            <c:if test="${not empty prop.imagen}">
                                <img src="${pageContext.request.contextPath}/${prop.imagen}"
                                     class="card-img-top" style="height:200px;object-fit:cover" />
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title">${prop.direccion}</h5>
                                <p class="card-text">${prop.descripcion}</p>
                                <p class="card-text price">$${prop.precio}</p>
                                <p class="card-text"><strong>Modalidad:</strong> ${prop.modalidad}</p>
                                <p class="card-text"><strong>Estado:</strong> ${prop.estado}</p>

                                <!-- Mostrar características si existen -->
                                <c:if test="${not empty detallesMap[prop.id_propiedad]}">
                                    <ul class="list-unstyled">
                                        <c:forEach var="det" items="${detallesMap[prop.id_propiedad]}">
                                            <li><strong>${det.nombre}</strong>: ${det.detalle}</li>
                                                </c:forEach>
                                    </ul>
                                </c:if>

                                <button class="btn btn-primary editar-btn"
                                        data-id="${prop.id_propiedad}"
                                        data-direccion="${fn:escapeXml(prop.direccion)}"
                                        data-descripcion="${fn:escapeXml(prop.descripcion)}"
                                        data-precio="${prop.precio}"
                                        data-modalidad="${prop.modalidad}"
                                        data-estado="${prop.estado}">
                                    Editar
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>

        <!-- Modal de edición -->
        <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" method="post" action="editarPropiedad" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editarModalLabel">Editar Propiedad</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" id="editar-id">
                        <div class="mb-3">
                            <label for="editar-direccion" class="form-label">Dirección</label>
                            <input type="text" class="form-control" name="direccion" id="editar-direccion">
                        </div>
                        <div class="mb-3">
                            <label for="editar-descripcion" class="form-label">Descripción</label>
                            <textarea class="form-control" name="descripcion" id="editar-descripcion"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="editar-precio" class="form-label">Precio</label>
                            <input type="number" class="form-control" name="precio" id="editar-precio">
                        </div>
                        <div class="mb-3">
                            <label for="editar-modalidad" class="form-label">Modalidad</label>
                            <select class="form-select" name="modalidad" id="editar-modalidad">
                                <option value="venta">Venta</option>
                                <option value="alquilado">Alquiler</option>
                                <option value="alquilado">Arrendamiento</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="editar-estado" class="form-label">Estado</label>
                            <select class="form-select" name="estado" id="editar-estado">
                                <option value="disponible">Disponible</option>
                                <option value="vendido">Vendido</option>
                                <option value="alquilado">Alquilado</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="editar-imagen" class="form-label">Imagen</label>
                            <input type="file" class="form-control" name="imagen" id="editar-imagen">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Guardar Cambios</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- JS de Bootstrap y script para llenar el modal -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            const editarModal = new bootstrap.Modal(document.getElementById('editarModal'));

            document.querySelectorAll('.editar-btn').forEach(btn => {
                btn.addEventListener('click', () => {
                    document.getElementById('editar-id').value = btn.dataset.id;
                    document.getElementById('editar-direccion').value = btn.dataset.direccion;
                    document.getElementById('editar-descripcion').value = btn.dataset.descripcion;
                    document.getElementById('editar-precio').value = btn.dataset.precio;
                    document.getElementById('editar-modalidad').value = btn.dataset.modalidad;
                    document.getElementById('editar-estado').value = btn.dataset.estado;
                    editarModal.show();
                });
            });
        </script>
    </body>
</html>
