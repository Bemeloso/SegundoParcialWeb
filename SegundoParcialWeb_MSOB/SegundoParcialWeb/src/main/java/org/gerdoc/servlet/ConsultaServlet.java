package org.gerdoc.servlet;
import org.gerdoc.dao.AccesoDao;
import org.gerdoc.model.UsuarioSesion;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  UsuarioSesion u=(UsuarioSesion)req.getSession().getAttribute("usuario");
  try {req.setAttribute("filas",new AccesoDao().consultar(u));}
  catch(Exception | LinkageError e){getServletContext().log("Error de consulta",e);req.setAttribute("error","No se pudo consultar la información.");}
  req.getRequestDispatcher(u.getRol().equals("ALUMNO")?"/alumno/calificaciones.jsp":"/profesor/alumnos.jsp").forward(req,resp);
 }
}
