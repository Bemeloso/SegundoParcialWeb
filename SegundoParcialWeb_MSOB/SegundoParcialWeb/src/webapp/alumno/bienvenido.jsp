<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.gerdoc.model.UsuarioSesion,org.gerdoc.util.Html" %>
<!doctype html><html lang="es"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Sistema escolar</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap.min.css"></head><body class="bg-light"><main class="container py-5"><div class="card p-4 mx-auto" style="max-width:900px"><h1 class="h3 mb-4">SISTEMA ESCOLAR</h1>
<% UsuarioSesion u=(UsuarioSesion)session.getAttribute("usuario"); %>
<p>Bienvenido, <%=Html.escapar(u.getNombre())%></p><p>Rol: <%=Html.escapar(u.getRol())%></p>
<a class="btn btn-primary" href="<%=request.getContextPath()%>/ConsultaServlet">Consultar mis calificaciones</a><form action="<%=request.getContextPath()%>/LogoutServlet" method="post" class="mt-3"><button class="btn btn-outline-secondary">Cerrar sesión</button></form></div></main></body></html>
