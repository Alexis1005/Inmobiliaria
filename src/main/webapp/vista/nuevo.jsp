<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Se asume que el JSP principal ya carga los CSS (Bootstrap, FontAwesome, etc.) -->
<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            <h3>${caracteristica.id_caracteristica == null || caracteristica.id_caracteristica == 0 ? "Nueva" : "Editar"} Característica
            </h3>
            <hr />
            <form action="CaracteristicasController" method="post">
                <div class="mb-3">
                    <label>Nombre:</label>
                    <input name="nombre" type="text" maxlength="100"
                           class="form-control" required value="${caracteristica.nombre}">
                </div>
                <div class="mb-3">
                    <label>Detalle:</label>
                    <input name="detalle" type="text" maxlength="100"
                           class="form-control" required value="${caracteristica.detalle}">
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
