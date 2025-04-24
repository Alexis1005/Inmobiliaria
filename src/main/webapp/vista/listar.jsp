<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card p-4">
    <h3>Características agregadas</h3>
    <hr />

    <jsp:include page="../components/Mensaje.jsp" />

    <table class="table table-bordered table-striped mt-2">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Detalle</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${caracteristicas}" var="item">
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.detalle}</td>
                    <td>
                        <form action="CaracteristicasController" method="post">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="id_caracteristica" value="${item.id_caracteristica}">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="fa fa-trash"></i> Eliminar
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty caracteristicas}">
                <tr>
                    <td colspan="4" class="text-center">No hay registros</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
