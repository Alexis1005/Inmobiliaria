<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subir Propiedad</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Subir Nueva Propiedad</h2>
            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editarModal" 
                    onclick="cargarDatosPropiedad('${propiedad.id_propiedad}', '${propiedad.direccion}', '${propiedad.precio}', '${propiedad.estado}', '${propiedad.caracteristicasGenerales}', '${propiedad.imagen}')">
                Editar
            </button>
            <form action="${pageContext.request.contextPath}/subirPropiedad" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" required>
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label">Precio</label>
                    <input type="number" step="0.01" class="form-control" id="precio" name="precio" required>
                </div>

                <div class="mb-3">
                    <label for="estado" class="form-label">Estado</label>
                    <select class="form-select" id="estado" name="estado" required>
                        <option value="disponible">Disponible</option>
                        <option value="vendido">Vendido</option>
                        <option value="alquilado">Alquilado</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="modalidad" class="form-label">Modalidad</label>
                    <select class="form-select" id="modalidad" name="modalidad" required>
                        <option value="Venta">Venta</option>
                        <option value="Alquiler">Alquiler</option>
                    </select>
                </div>
                <!-- Tipo de Propiedad -->
                <div class="mb-3">
                    <label for="idTipo" class="form-label">Tipo de Propiedad</label>
                    <select class="form-select" id="idTipo" name="idTipo" required>
                        <option value="">Seleccione un tipo</option>
                        <c:forEach var="tipo" items="${tiposPropiedad}">
                            <option value="${tipo.id_tipo}">${tipo.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Agente -->
                <div class="mb-3">
                    <label for="idAgente" class="form-label">Agente</label>
                    <select class="form-select" id="idAgente" name="idAgente" required>
                        <option value="">Seleccione un agente</option>
                        <c:forEach var="agente" items="${agentes}">
                            <option value="${agente.id_agente}">${agente.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Características dinámicas -->
                <div class="mb-3">
                    <label class="form-label">Características</label>
                    <div id="caracteristicasContainer">
                        <!-- Aquí se cargarán las características dinámicamente -->
                    </div>
                    <div class="mb-3">
                        <label for="caracteristicasGenerales" class="form-label">Características Generales</label>
                        <textarea class="form-control" id="caracteristicasGenerales" name="caracteristicasGenerales" rows="5"></textarea>
                    </div>
                    <button type="button" class="btn btn-secondary mt-2" data-bs-toggle="modal" data-bs-target="#agregarCaracteristicaModal">
                        Agregar Característica
                    </button>
                </div>
                <div class="mb-3">
                    <label for="imagenes" class="form-label">Imágenes</label>
                    <input type="file" class="form-control" id="imagenes" name="imagenes" multiple accept="image/*">
                </div>
                <button type="submit" class="btn btn-primary">Subir Propiedad</button>
            </form>
            <a href="${pageContext.request.contextPath}/propiedades" class="btn btn-secondary mt-3">Volver a Propiedades</a>
        </div>

        <!-- Modal para agregar nueva característica -->
        <div class="modal fade" id="agregarCaracteristicaModal" tabindex="-1" aria-labelledby="agregarCaracteristicaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="agregarCaracteristicaModalLabel">Agregar Característica</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="agregarCaracteristicaForm">
                            <div class="mb-3">
                                <label for="nuevaCaracteristicaNombre" class="form-label">Nombre de la Característica</label>
                                <input type="text" class="form-control" id="nuevaCaracteristicaNombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="nuevaCaracteristicaDetalle" class="form-label">Detalle (ej. "2 baños", "100 m²")</label>
                                <input type="text" class="form-control" id="nuevaCaracteristicaDetalle">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" onclick="agregarCaracteristica()">Agregar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal para Editar Propiedades -->
    <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editarModalLabel">Editar Propiedad</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="editarForm" action="${pageContext.request.contextPath}/editarPropiedad" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="editId" name="id">
                        <div class="mb-3">
                            <label for="editDireccion" class="form-label">Dirección</label>
                            <input type="text" class="form-control" id="editDireccion" name="direccion" required>
                        </div>
                        <div class="mb-3">
                            <label for="editPrecio" class="form-label">Precio</label>
                            <input type="number" step="0.01" class="form-control" id="editPrecio" name="precio" required>
                        </div>
                        <div class="mb-3">
                            <label for="editEstado" class="form-label">Estado</label>
                            <select class="form-select" id="editEstado" name="estado" required>
                                <option value="Disponible">Disponible</option>
                                <option value="Alquilado">Alquilado</option>
                                <option value="Vendido">Vendido</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="editCaracteristicasGenerales" class="form-label">Características Generales</label>
                            <textarea class="form-control" id="editCaracteristicasGenerales" name="caracteristicasGenerales" rows="5"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="editImagen" class="form-label">Imagen (dejar en blanco para mantener la actual)</label>
                            <input type="file" class="form-control" id="editImagen" name="imagen">
                            <img id="imagenActual" src="" alt="Imagen actual" style="max-width: 200px; display: none;">
                            <input type="hidden" id="editImagenActual" name="imagenActual">
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


        <!-- Scripts -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            // Cargar características cuando cambie el tipo de propiedad
                            document.getElementById('idTipo').addEventListener('change', function () {
                                var tipoId = this.value;
                                if (tipoId) {
                                    fetch('${pageContext.request.contextPath}/caracteristicas?tipoId=' + tipoId)
                                            .then(response => response.json())
                                            .then(data => {
                                                console.log('Características cargadas:', data);
                                                var container = document.getElementById('caracteristicasContainer');
                                                container.innerHTML = '';
                                                data.forEach(caracteristica => {
                                                    var div = document.createElement('div');
                                                    div.className = 'mb-2';
                                                    div.innerHTML = '<label class="form-label">' + caracteristica.nombre + '</label>' +
                                                            '<input type="text" class="form-control" name="detalle_' + caracteristica.idCaracteristica + '" placeholder="Ingrese detalle para ' + caracteristica.nombre + '" value="' + (caracteristica.detalle || '') + '">';
                                                    container.appendChild(div);
                                                });
                                            })
                                            .catch(error => console.error('Error al cargar características:', error));
                                } else {
                                    document.getElementById('caracteristicasContainer').innerHTML = '';
                                }
                            });

                            // Agregar nueva característica
                            function agregarCaracteristica() {
                                var nombre = document.getElementById('nuevaCaracteristicaNombre').value;
                                var detalle = document.getElementById('nuevaCaracteristicaDetalle').value;
                                var tipoId = document.getElementById('idTipo').value;

                                if (!nombre || !tipoId) {
                                    alert('Por favor, seleccione un tipo de propiedad y escriba un nombre para la característica.');
                                    return;
                                }

                                console.log('Enviando datos:', {nombre: nombre, detalle: detalle, tipoId: tipoId});

                                fetch('${pageContext.request.contextPath}/agregarCaracteristica', {
                                    method: 'POST',
                                    headers: {'Content-Type': 'application/json'},
                                    body: JSON.stringify({nombre: nombre, detalle: detalle, tipoId: tipoId})
                                })
                                        .then(response => {
                                            console.log('Estado de la respuesta:', response.status);
                                            if (!response.ok) {
                                                return response.text().then(text => {
                                                    throw new Error('Error del servidor: ' + response.status + ' - ' + text);
                                                });
                                            }
                                            return response.json();
                                        })
                                        .then(data => {
                                            console.log('Datos recibidos:', data);
                                            if (data.success) {
                                                alert('Característica agregada con éxito');
                                                var modal = bootstrap.Modal.getInstance(document.getElementById('agregarCaracteristicaModal'));
                                                modal.hide();
                                                document.getElementById('nuevaCaracteristicaNombre').value = '';
                                                document.getElementById('nuevaCaracteristicaDetalle').value = '';
                                                document.getElementById('idTipo').dispatchEvent(new Event('change'));
                                            } else {
                                                alert('Error al agregar la característica');
                                            }
                                        })
                                        .catch(error => {
                                            console.error('Error:', error);
                                            alert('Error al agregar la característica: ' + error.message);
                                        });
                            }
                          
        // Script para cargar los datos de la propiedad en el modal
        function cargarDatosPropiedad(id, direccion, precio, estado, caracteristicasGenerales, imagen) {
            document.getElementById('editId').value = id;
            document.getElementById('editDireccion').value = direccion;
            document.getElementById('editPrecio').value = precio;
            document.getElementById('editEstado').value = estado;
            document.getElementById('editCaracteristicasGenerales').value = caracteristicasGenerales || '';
            document.getElementById('editImagenActual').value = imagen;
            
            const imagenActual = document.getElementById('imagenActual');
            if (imagen) {
                imagenActual.src = '${pageContext.request.contextPath}/imagenes/' + imagen;
                imagenActual.style.display = 'block';
            } else {
                imagenActual.style.display = 'none';
            }
        }
        // Script para el formulario de filtrado
        document.addEventListener('DOMContentLoaded', function() {
            const modalidad = document.getElementById('modalidad');
            const tipoPropiedad = document.getElementById('tipoPropiedad');
            
            if (modalidad) {
                modalidad.addEventListener('change', function() {
                    console.log('Modalidad cambiada:', this.value);
                });
            }
            if (tipoPropiedad) {
                tipoPropiedad.addEventListener('change', function() {
                    console.log('Tipo de propiedad cambiado:', this.value);
                });
            }
        });
    </script>
        </script>
    </body>
</html>