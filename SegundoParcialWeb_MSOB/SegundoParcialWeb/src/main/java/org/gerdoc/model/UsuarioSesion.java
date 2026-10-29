package org.gerdoc.model;
import java.io.Serializable;
public class UsuarioSesion implements Serializable {
 private final long personaId;
 private final String nombre, rol;
 public UsuarioSesion(long personaId, String nombre, String rol) { this.personaId=personaId; this.nombre=nombre; this.rol=rol; }
 public long getPersonaId(){return personaId;}
 public String getNombre(){return nombre;}
 public String getRol(){return rol;}
}
