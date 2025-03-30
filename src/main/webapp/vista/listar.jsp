
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Caracteristicas</title>
    </head>
    <body>
        <div>
            <div class="container mt-3">
                <div class="card-body">
                    <h3>Características</h3>
                    <hr />
                    
                    <a href="CaracteristicasController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>
                    
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
                        <tbody>
                            <c:forEach items="${caracteristicas}" var="item">
                                <tr>
                                    <td>${item.id_caracteristica}</td>
                                    <td>${item.nombre}</td>
                                    <td>${item.detalle}</td>
                                    <td>
                                        <a href="CaracteristicasController?accion=editar&id_caracteristica=${item.id_caracteristica}" class="btn btn-info btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                            <a href="CaracteristicasController?accion=eliminar&id_caracteristica=${item.id_caracteristica}" 
                                               onclick="return confirm('¿Eliminar característica?');" class="btn btn-danger btn-sm">
                                                <i class="fa fa-trash"></i>
                                            </a>    
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empleados.size() == 0}">
                                <tr>
                                    <td colspan="2">No hay registros</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </body>
</html>
