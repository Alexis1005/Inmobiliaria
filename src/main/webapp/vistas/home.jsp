<%-- 
    Author     : Alexis
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Inicio</title>
    </head>
<body>
    <c:forEach items="${propiedades}" var="item">
        <div class="card h-100 custom-hover mt-3">
            <img src="/img/c.jpg" class="card-img-top" alt="Casa 1" style="height: 180px;">
            <div class="card-body">
                <h5 class="card-title">${item.descripcion}</h5>
                <p class="card-text">${item.descripcion}</p>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><strong>Precio:</strong> ${item.precio}</li>
                </ul>
            </div>
        </div>
    </c:forEach>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
