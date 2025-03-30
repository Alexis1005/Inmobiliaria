<%-- 
    Document   : propiedad
    Created on : 13 mar. 2025, 19:44:15
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/carrusel.css">
        <link rel="stylesheet" href="/CSS/nav.css">
        <title>Detalle propiedad</title>
    </head>

    <body style="background-color: #19875409;">
        <!-------------- carrousel -------------- -->
        <div class="container d-flex align-items-center justify-content-center">
            <div class="section section-custom text-center w-100">


                <div id="carouselExampleIndicators" class="carousel slide my-2 shadow-lg w-75 m-auto"
                     data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                                class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="/img/b.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="/img/b.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="/img/g.jpg" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
        <!--------------- DESCRIPCIÓN (lado izquierdo de la columna)--------------->

        <div class="container mt-3">
            <div class="row g-2">
                <div class="col-md-8">
                    <h1>Importante casa Céntrica</h1>
                    <div class="row g-2 ">
                        <h3>Información Básica</h3>
                        <div class="col-md-6">
                            <ul class="lista-custom">
                                <li>Operación: <strong>Venta</strong></li>
                                <li>Dormitorio: <strong>3</strong></li>
                                <li>Ambientes: <strong>5</strong></li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <ul class="lista-custom">
                                <li>Baños: <strong>Si</strong></li>
                                <li>Garaje: <strong>Si</strong></li>
                                <li>Patio: <strong>Si</strong></li>
                            </ul>
                        </div>
                    </div>
                    <hr class="w-75">
                    <p>Descripción general de la Casa:</p>
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                    <h3>USD$ <strong>300000</strong></h3>
                    <hr class="w-75">
                </div>

                <!--------------- formulario (lado derecho de la columna)-------------------->

                <div class="col-md-4 mt-5 mb-3 position-relative">
                    <h4 class="position-absolute top-0 start-50 translate-middle bg-white px-3 rounded text-success">
                        Contacto
                    </h4>
                    <form method="post" action="" class="form-custom border border-2 border-success p-4 w-100 rounded">
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre completo</label>
                            <input type="text" class="form-control" id="nombre">
                        </div>
                        <div class="mb-3">
                            <label for="tel" class="form-label">Teléfono</label>
                            <input type="text" class="form-control" id="tel">
                        </div>
                        <div class="mb-3">
                            <label for="correo" class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" id="correo">
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="mensajec">Mensaje</label>
                            <textarea class="form-control" id="mensajec"
                                      placeholder="Déjenos un mensaje y nos comunicaremos con usted!"
                                      aria-label="First name"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success">Enviar</button>
                    </form>
                </div>
            </div>

        </div>

        <!------------Ubicación----------->
        <div class="container m-0 p-0">
            <h3 style="margin-left: 6%;"> Ubicación</h3>
            <iframe class="w-100"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3357.975758132772!2d-58.89181492518789!3d-32.68669017370155!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95b04493ad873fff%3A0x8bd5c03a1ec6c838!2sTijuana%20Bar%20%26%20Resto!5e0!3m2!1ses-419!2sar!4v1739203694793!5m2!1ses-419!2sar"
                    width="100%" height="400" style="border:0;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    </body>

</html>
