package org.gerdoc.filter;
import org.gerdoc.model.UsuarioSesion;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
@WebFilter("/*")
public class AccesoFilter implements Filter {
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException {
  HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;
  String ruta=req.getServletPath();
  resp.setHeader("Cache-Control","no-store");
  if(ruta.equals("/login.jsp") || ruta.equals("/LoginServlet") || ruta.startsWith("/assets/")){chain.doFilter(req,resp);return;}
  HttpSession sesion=req.getSession(false);
  UsuarioSesion u=sesion==null?null:(UsuarioSesion)sesion.getAttribute("usuario");
  if(u==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
  if((ruta.startsWith("/alumno/")&&!u.getRol().equals("ALUMNO")) || (ruta.startsWith("/profesor/")&&!u.getRol().equals("PROFESOR"))){resp.sendError(403);return;}
  if(u.getRol().equals("ALUMNO") && !ruta.startsWith("/alumno/") && !ruta.equals("/ConsultaServlet") && !ruta.equals("/LogoutServlet") && !ruta.equals("/index.jsp") && !ruta.equals("/")){resp.sendError(403);return;}
  chain.doFilter(req,resp);
 }
}
