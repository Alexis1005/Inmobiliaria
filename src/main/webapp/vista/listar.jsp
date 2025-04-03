<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Nota: Al estar incluido en panelAdmin.jsp, no es necesario definir nuevamente el DOCTYPE, head o body -->
<div class="card p-4">
        <h3>Características adicionales</h3>
        <hr />
        
        
        <!-- Se incluye el componente para mensajes (éxito o error) si lo tienes -->
        <jsp:include page="../components/Mensaje.jsp" />
        
        <!-- Tabla de listado de características -->
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
                            <a href="CaracteristicasController?accion=editar&id_caracteristica=${item.id_caracteristica}" 
                               class="btn btn-info btn-sm">
                                <i class="fa fa-edit"></i>
                            </a>
                            <a href="CaracteristicasController?accion=eliminar&id_caracteristica=${item.id_caracteristica}" 
                               onclick="return confirm('¿Eliminar característica?');" class="btn btn-danger btn-sm">
                                <i class="fa fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${caracteristicas == null || caracteristicas.size() == 0}">
                    <tr>
                        <td colspan="4" class="text-center">No hay registros</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
</div>
