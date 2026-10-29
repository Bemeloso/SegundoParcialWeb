package org.gerdoc.dao;
import org.gerdoc.model.UsuarioSesion;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;
public class AccesoDao {
 public UsuarioSesion autenticar(String usuario, String password) {
  try (Session s=HibernateUtil.getSessionFactory().openSession()) {
   Object[] r=(Object[])s.createNativeQuery("SELECT R.NOMBRE, COALESCE(A.NOMBRE,P.NOMBRE), COALESCE(U.ALUMNO_ID,U.PROFESOR_ID) FROM USUARIO U JOIN ROL R ON R.ID=U.ROL_ID LEFT JOIN ALUMNO A ON A.ID=U.ALUMNO_ID LEFT JOIN PROFESOR P ON P.ID=U.PROFESOR_ID WHERE BINARY U.USERNAME=:usuario AND BINARY U.PASSWORD=:password AND U.ACTIVO=TRUE")
    .setParameter("usuario",usuario).setParameter("password",password).uniqueResult();
   if(r==null || r[1]==null || r[2]==null) return null;
   String rol=r[0].toString();
   if(!rol.equals("ALUMNO") && !rol.equals("PROFESOR"))return null;
   return new UsuarioSesion(((Number)r[2]).longValue(),r[1].toString(),rol);
  }
 }
 @SuppressWarnings("unchecked")
 public List<Object[]> consultar(UsuarioSesion usuario) {
  boolean alumno=usuario.getRol().equals("ALUMNO");
  String sql=alumno ? "SELECT M.NOMBRE, AM.CALIFICACION FROM ALUMNO_MATERIA AM JOIN MATERIA M ON M.ID=AM.MATERIA_ID WHERE AM.ALUMNO_ID=:id ORDER BY M.NOMBRE" : "SELECT M.NOMBRE, A.NOMBRE, AM.CALIFICACION FROM PROFESOR_MATERIA PM JOIN MATERIA M ON M.ID=PM.MATERIA_ID JOIN ALUMNO_MATERIA AM ON AM.MATERIA_ID=M.ID JOIN ALUMNO A ON A.ID=AM.ALUMNO_ID WHERE PM.PROFESOR_ID=:id ORDER BY M.NOMBRE,A.NOMBRE";
  try(Session s=HibernateUtil.getSessionFactory().openSession()) {
   return s.createNativeQuery(sql).setParameter("id",usuario.getPersonaId()).list();
  }
 }
}
