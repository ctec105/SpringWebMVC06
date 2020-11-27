<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta harset="UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
    </head>
    <body>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<c:url value="/home.htm"/>">Listado de Productos</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Editar</li>
                        </ol>
                    </nav>    

                    <div class="card">
                        <div class="card-header">
                            Actualizar Usuario
                        </div>
                        <div class="card-body">
                            <form:form method="post" commandName="usuarios">

                                <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                                <div class="form-group">
                                    <form:label path="nombre">Nombre</form:label>
                                    <form:input path="nombre" cssClass="form-control"/>
                                    <form:errors path="nombre"/>
                                </div>

                                <div class="form-group">
                                    <form:label path="correo">Correo</form:label>
                                    <form:input path="correo" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <form:label path="telefono">Teléfono</form:label>
                                    <form:input path="telefono" cssClass="form-control"/>
                                </div>
                                <hr>

                                <input type="submit" value="Actualizar" class="btn btn-success btn-block"/>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
