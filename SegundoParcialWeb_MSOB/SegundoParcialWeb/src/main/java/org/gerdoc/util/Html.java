package org.gerdoc.util;
public class Html {
 public static String escapar(Object valor){
  if(valor==null)return "Sin calificación";
  return valor.toString().replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");
 }
}
