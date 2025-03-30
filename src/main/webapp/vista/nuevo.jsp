<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <title>Formulario Caracteristicas</title>
    </head>
    <body>

        <div class="container mt-3">
            <div class="card">

                <div class="card-body">
                    <h3>${caracteristica.id_caracteristica == 0 ? "Nueva": "Editar"} Característica</h3>
                    <hr />
                    
                    <form action="CaracteristicasController" method="post">
                        <div class="mb-3">
                            <label>Nombre:</label>
                            <input name="nombre" type="text" maxlength="100"
                                   class="form-control" required="" value="${caracteristica.nombre}">
                        </div>
                         <div class="mb-3">
                            <label>Detalle:</label>
                            <input name="detalle" type="text" maxlength="100"
                                   class="form-control" required="" value="${caracteristica.detalle}">
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
    </body>
</html>
