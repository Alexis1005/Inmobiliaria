<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="card">
                <div class="card-body">
                    <h3>${caracteristica.id_caracteristica == 0 ? "Nueva" : "Editar"} Característica</h3>
                    <hr />

                    <form action="CaracteristicasController" method="post">
                        <div class="mb-3">
                            <label>Nombre:</label>
                            <select id="nombreSelect" class="form-control" name="nombre">
                                <option value="">Seleccionar caracteristica</option>
                                <c:forEach var="item" items="${listaCaracteristicas}">
                                    <option value="${item}" ${caracteristica.nombre == item ? 'selected' : ''}>
                                        ${item}
                                    </option>

                                </c:forEach>
                            </select>
                            <input id="nombreInput" type="text" class="form-control mt-2" name="nombre_manual" maxlength="100" placeholder="Ingresar nueva caracteristica">
                        </div>

                        <div class="mb-3">
                            <label>Detalle:</label>
                            <select id="detalleSelect" class="form-control" name="detalle">
                                <option value="">Seleccionar detalle</option>
                                <c:forEach var="detalle" items="${listaDetalles}">
                                    <option value="${detalle}" ${caracteristica.detalle == detalle ? 'selected' : ''}>
                                        ${detalle}
                                    </option>
                                </c:forEach>
                            </select>
                            <input id="detalleInput" type="text" class="form-control mt-2" name="detalle_manual" maxlength="100" placeholder="Ingresar nuevo detalle">
                        </div>

                        <div class="mb-3">
                            <input type="hidden" name="id_caracteristica" value="${caracteristica.id_caracteristica}">
                            <input type="hidden" name="accion" value="guardar">
                            <button class="btn btn-primary btn-sm">
                                <i class="fa fa-save"></i> Guardar
                            </button>
                            <a href="CaracteristicasController?accion=listar" class="btn btn-dark btn-sm">
                                <i class="fa fa-arrow-left"></i> Volver atrás
                            </a>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                function toggleInput(selectElement, inputElement) { // si se selecciona una opcion se oculta el input y se borra el contenido
                    selectElement.addEventListener("change", function () {
                        if (this.value === "") {
                            inputElement.style.display = "block";
                            inputElement.focus();
                        } else {
                            inputElement.style.display = "none";
                            inputElement.value = ""; 
                        }
                    });

                    inputElement.addEventListener("input", function () {
                        if (this.value.trim() !== "") {
                            selectElement.value = ""; // Borra la selección si el usuario escribe
                        }
                    });
                }

                toggleInput(document.getElementById("nombreSelect"), document.getElementById("nombreInput"));
                toggleInput(document.getElementById("detalleSelect"), document.getElementById("detalleInput"));

                document.querySelector("form").addEventListener("submit", function (event) {
                    let nombreSelect = document.getElementById("nombreSelect");
                    let nombreInput = document.getElementById("nombreInput");
                    let detalleSelect = document.getElementById("detalleSelect");
                    let detalleInput = document.getElementById("detalleInput");

                    let errores = [];
                    // se verifica que al menos uno de los dos campos tenga un valor 
                    if (!nombreSelect.value && !nombreInput.value.trim()) {
                        errores.push("Seleccione o ingrese una nueva característica.");
                    }
                    if (!detalleSelect.value && !detalleInput.value.trim()) {
                        errores.push("Seleccione o ingrese un nuevo detalle.");
                    }

                    if (errores.length > 0) {
                        alert(errores.join("\n"));
                        event.preventDefault(); // se usa si hay errores y detiene el envio del formulario
                    }
                });
            });
        </script>
    </body>
</html>
