package org.gerdoc.servlet;
import org.gerdoc.dao.AccesoDao;
import org.gerdoc.model.UsuarioSesion;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  req.setCharacterEncoding("UTF-8");
  String nombre=req.getParameter("usuario"), password=req.getParameter("password");
  if(nombre==null || nombre.trim().isEmpty() || password==null || password.isEmpty()) {
   error(req,resp,"Ingresa tu usuario y contraseña.");return;
  }
  try {
   UsuarioSesion usuario=new AccesoDao().autenticar(nombre.trim(),password);
   if(usuario==null){error(req,resp,"Usuario o contraseña incorrectos, o usuario inactivo.");return;}
   HttpSession anterior=req.getSession(false);if(anterior!=null)anterior.invalidate();
   req.getSession(true).setAttribute("usuario",usuario);
   resp.sendRedirect(req.getContextPath()+"/"+(usuario.getRol().equals("ALUMNO")?"alumno":"profesor")+"/bienvenido.jsp");
  }catch(Exception | LinkageError e){getServletContext().log("Error de autenticación",e);error(req,resp,"No se pudo conectar con la base de datos. Revisa la configuración.");}
 }
 private void error(HttpServletRequest req,HttpServletResponse resp,String mensaje)throws ServletException,IOException {
  req.setAttribute("error",mensaje);req.getRequestDispatcher("/login.jsp").forward(req,resp);
 }
}
