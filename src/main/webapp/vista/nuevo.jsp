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
                    <form action="CaracteristicasController" method="post" id="formAgregarCaracteristica">
                        <input type="hidden" name="accion" value="guardar">  <!-- 🔥 Agregado -->

                        <div class="mb-3">
                            <label>Nombre:</label>
                            <select id="nombreSelect" class="form-control" name="nombre">
                                <option value="">Seleccionar característica</option>
                                <c:forEach var="item" items="${listaCaracteristicas}">
                                    <option value="${item}" <c:if test="${caracteristica != null && caracteristica.nombre == item}">selected</c:if>>
                                        ${item}
                                    </option>
                                </c:forEach>
                            </select>
                            <input id="nombreInput" type="text" class="form-control mt-2" name="nombre_manual" maxlength="100" placeholder="Ingresar nueva característica">
                        </div>

                        <div class="mb-3">
                            <label>Detalle:</label>
                            <select id="detalleSelect" class="form-control" name="detalle">
                                <option value="">Seleccionar detalle</option>
                                <c:forEach var="detalle" items="${listaDetalles}">
                                    <option value="${detalle}" <c:if test="${caracteristica != null && caracteristica.detalle == detalle}">selected</c:if>>
                                        ${detalle}
                                    </option>
                                </c:forEach>
                            </select>
                            <input id="detalleInput" type="text" class="form-control mt-2" name="detalle_manual" maxlength="100" placeholder="Ingresar nuevo detalle">
                        </div>

                        <button type="submit" class="btn btn-primary btn-sm">
                            <i class="fa fa-save"></i> Guardar
                        </button>
                    </form>

                </div>
            </div>

           
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.getElementById("formAgregarCaracteristica").addEventListener("submit", function (event) {
                    event.preventDefault(); // Evita la recarga de la página

                    let nombre = document.getElementById("nombreSelect").value || document.getElementById("nombreInput").value.trim();
                    let detalle = document.getElementById("detalleSelect").value || document.getElementById("detalleInput").value.trim();

                    if (!nombre || !detalle) {
                        alert("Debe completar ambos campos.");
                        return;
                    }

                    let formData = new FormData();
                    formData.append("accion", "guardar");  // 🔥 Asegurar que se envía la acción
                    formData.append("nombre", nombre);
                    formData.append("detalle", detalle);

                    fetch("CaracteristicasController", {
                        method: "POST",
                        headers: {"X-Requested-With": "XMLHttpRequest"},
                        body: formData
                    })
                            .then(response => response.text())
                            .then(htmlFragment => {
                                document.getElementById("listaCaracteristicasContainer").innerHTML = htmlFragment;
                                document.getElementById("formAgregarCaracteristica").reset();
                                document.getElementById("nombreInput").style.display = "none";
                                document.getElementById("detalleInput").style.display = "none";
                            })
                            .catch(error => console.error("Error:", error));
                });
            });

        </script>
    </body>
</html>
