<%-- 
    Document   : adminEditar
    Created on : 13 mar. 2025, 19:43:16
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Propiedades en Cards</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/CSS/card.css">
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

            <!-- Fila para las cards -->
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <!-- Ejemplo de Card 1 (Datos estáticos) -->
                <div class="col">
                    <div class="card h-100 custom-hover mt-3">
                        <img src="/img/c.jpg" class="card-img-top" alt="Casa 1" style="height: 180px;">
                        <div class="card-body">
                            <h5 class="card-title">Casa Moderna en Centro</h5>
                            <p class="card-text">Amplia casa con 3 habitaciones, 2 baños y jardín. Ideal para familias.</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Precio:</strong> $250,000</li>
                                <li class="list-group-item"><strong>Habitaciones:</strong> 3</li>
                                <li class="list-group-item"><strong>Ubicación:</strong> Centro</li>
                            </ul>
                            <button class="btn btn-success w-100 edit-btn" data-bs-toggle="modal"
                                    data-bs-target="#editModal">
                                Editar
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Ejemplo de Card 2 (Datos estáticos) -->
                <div class="col">
                    <div class="card h-100 shadow custom-hover mt-3">
                        <img src="/img/l.jpg" class="card-img-top" alt="Casa 2" style="height: 180px;">
                        <div class="card-body">
                            <h5 class="card-title">Departamento en la Playa</h5>
                            <p class="card-text">Departamento luminoso con vista al mar y acceso a piscina.</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Precio:</strong> $180,000</li>
                                <li class="list-group-item"><strong>Habitaciones:</strong> 2</li>
                                <li class="list-group-item"><strong>Ubicación:</strong> Zona Costera</li>
                            </ul>
                            <button class="btn btn-success w-100 edit-btn" data-bs-toggle="modal"
                                    data-bs-target="#editModal">
                                Editar
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100 custom-hover mt-3">
                        <img src="/img/b.jpg" class="card-img-top" alt="Casa 2" style="height: 180px;">
                        <div class="card-body">
                            <h5 class="card-title">Departamento en la Playa</h5>
                            <p class="card-text">Departamento luminoso con vista al mar y acceso a piscina.</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Precio:</strong> $180,000</li>
                                <li class="list-group-item"><strong>Habitaciones:</strong> 2</li>
                                <li class="list-group-item"><strong>Ubicación:</strong> Zona Costera</li>
                            </ul>
                            <button class="btn btn-success w-100 edit-btn" data-bs-toggle="modal"
                                    data-bs-target="#editModal">
                                Editar
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Modal de Edición -->
                <div class="modal fade" id="editModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Editar Propiedad</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <form id="editForm">
                                    <div class="mb-3">
                                        <label for="tipo">Tipo de propiedad</label>
                                        <select class="form-select" id="tipo">
                                            <option value="1">Casa</option>
                                            <option value="2">Departamento</option>
                                            <option value="3">Campo</option>
                                            <option value="4">Terreno</option>
                                            <option value="5">Galpón</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="estado">Estado de la propiedad</label>
                                        <select class="form-select" id="estado">
                                            <option value="1">Venta</option>
                                            <option value="2">Alquiler</option>
                                            <option value="3">Arrendamiento</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="titulo">Título</label>
                                        <input type="text" class="form-control" id="titulo">
                                    </div>
                                    <div class="mb-3">
                                        <label for="ubicacion">Ubicación</label>
                                        <input type="text" id="ubicacion" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label for="descripcion">Descripción General</label>
                                        <textarea class="form-control" id="descripcion"                                        ></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Precio</label>
                                        <input type="text" id="editPrecio" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Habitaciones</label>
                                        <input type="number" id="editHabitaciones" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ubicación</label>
                                        <input type="text" id="editUbicacion" class="form-control">
                                    </div>
                                    <button type="submit" class="btn btn-success w-100">Guardar Cambios</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- Bootstrap JS (opcional) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>