<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Propiedades</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Lista de Propiedades</h2>
        <table id="propiedadesTable" class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Dirección</th>
                    <th>Precio</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="propiedad" items="${propiedades}">
                    <tr>
                        <td>${propiedad.id_propiedad}</td>
                        <td>${propiedad.direccion}</td>
                        <td>${propiedad.precio}</td>
                        <td>${propiedad.estado}</td>
                        <td>
                            <button type="button" class="btn btn-warning" onclick="editarPropiedad('${propiedad.id_propiedad}')">
                                Editar
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#propiedadesTable').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.11.5/i18n/Spanish.json"
                }
            });
        });

        function editarPropiedad(id) {
            window.location.href = '${pageContext.request.contextPath}/editarPropiedad?id=' + id;
        }
    </script>
</body>
</html>