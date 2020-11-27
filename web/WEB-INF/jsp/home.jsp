<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <h1>Ingrese sus datos</h1>
                    
                    <a href="<c:url value="/add.htm"/>" class="btn btn-success">Agregar</a>
                    
                    <br/><br/>
                    
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <td>ID</td>
                                <td>Nombre</td>
                                <td>Email</td>
                                <td>Teléfono</td>
                                <td>Opciones</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${datos}" var="dato">
                                <tr>
                                    <td><c:out value="${dato.id}"/></td>
                                    <td><c:out value="${dato.nombre}"/></td>
                                    <td><c:out value="${dato.correo}"/></td>
                                    <td><c:out value="${dato.telefono}"/></td>
                                    <td>
                                        <a href="<c:url value="/edit.htm?id=${dato.id}"/>"><i class="fas fa-pencil-alt"></i></a>
                                        <a href="<c:url value="/delete.htm?id=${dato.id}"/>"><i class="fas fa-trash-alt"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
