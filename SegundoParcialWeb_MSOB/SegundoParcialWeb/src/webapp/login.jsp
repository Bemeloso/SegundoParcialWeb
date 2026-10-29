<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.gerdoc.model.UsuarioSesion,org.gerdoc.util.Html" %>
<!doctype html><html lang="es"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Sistema escolar</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap.min.css"></head><body class="bg-light"><main class="container py-5"><div class="card p-4 mx-auto" style="max-width:900px"><h1 class="h3 mb-4">SISTEMA ESCOLAR</h1>
<h2 class="h5">Iniciar sesión</h2>
<% if(request.getAttribute("error")!=null){ %><div class="alert alert-danger"><%=Html.escapar(request.getAttribute("error"))%></div><% } %>
<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
<label class="form-label" for="usuario">Usuario</label><input class="form-control mb-3" id="usuario" name="usuario" required autocomplete="username">
<label class="form-label" for="password">Contraseña</label><input class="form-control mb-3" type="password" id="password" name="password" required autocomplete="current-password">
<button class="btn btn-primary" type="submit">Iniciar sesión</button></form></div></main></body></html>
