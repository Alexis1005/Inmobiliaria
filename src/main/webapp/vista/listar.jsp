<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Nota: Este archivo se utiliza como fragmento, por lo que no incluye <html>, <head> ni <body> -->
<div class="card p-4">
    <h3>Características agregadas</h3>
    <hr />

    <!-- Se incluye el componente para mensajes (éxito o error) -->
    <jsp:include page="../components/Mensaje.jsp" />

    <table class="table table-bordered table-striped mt-2">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Detalle</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody id="listaCaracteristicas">
            <c:forEach items="${caracteristicas}" var="item">
                <tr id="fila-${item.id}">
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.detalle}</td>
                    <td>
                        <!-- Botón para eliminar -->
                        <button type="button" class="btn btn-danger btn-sm" onclick="eliminarCaracteristica(${item.id})">
                            <i class="fa fa-trash"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty caracteristicas}">
                <tr id="sinRegistros">
                    <td colspan="4" class="text-center">No hay registros</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<script>
    function eliminarCaracteristica(id) {
        if (confirm('¿Eliminar característica?')) {
            fetch(`CaracteristicasController?accion=eliminar&id_caracteristica=${id}`, {
                method: 'GET'
            })
            .then(response => response.text())
            .then(data => {
                let fila = document.getElementById(`fila-${id}`);
                if (fila) fila.remove();

                // Si ya no hay filas, agregar mensaje "No hay registros"
                let tabla = document.getElementById("listaCaracteristicas");
                if (tabla.children.length === 0) {
                    let filaVacia = document.createElement("tr");
                    filaVacia.id = "sinRegistros";
                    filaVacia.innerHTML = '<td colspan="4" class="text-center">No hay registros</td>';
                    tabla.appendChild(filaVacia);
                }
            })
            .catch(error => console.error('Error al eliminar:', error));
        }
    }
</script>
