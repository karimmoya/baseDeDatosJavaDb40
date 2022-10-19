/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaunidad8pgr;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import javax.swing.JTextArea;
/**
 *
 * @author KarimHacker
 */
public class BaseDatosArticulo {
 
  private final String path = "C:\\Db4o\\BaseDatosArticulo.yap";
  private ObjectContainer bd;
  
  public void BaseDatosArticulo (){}
  
  public void altas (Articulo a) {
      
      bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),path);
      bd.store(a);
      bd.close();
      
  }
  
  public void mostrar (javax.swing.JTextArea jTextArea1) {
        
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),path);
        Articulo a = new Articulo ();
        ObjectSet<Articulo> resultado = bd.queryByExample (a);
     while (resultado.hasNext()){
        Articulo ar = (Articulo) resultado.next();
        jTextArea1.append(ar.getCodigo()+ "\t"+ ar.getNombre()+"\t" + ar.getCantidad()+"\t" + ar.getDescripcion()+"\n");
     
     }
     
  }
  public String modificar (int codigo, String nombre, double cantidad,  String Descripcion){
       
        bd =Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),path);
        String r="";
        Articulo articuloConsulta = new Articulo(codigo,null,0,null);
        
        ObjectSet<Articulo> resultadoConsulta = bd.queryByExample(articuloConsulta);
        
	if (resultadoConsulta.size()==0){
                r="No se puede modificar";
        }
    else {
            Articulo art = resultadoConsulta.next();
            art.setCantidad(cantidad);
            art.setNombre(nombre);
            art.setDescripcion(Descripcion);
            bd.store(art);
            r="Modificación realizada";
        }
        
        bd.close();
    
    return r;
      
  
  
  
  
  }
      
        public String borrar (int codigo){
        bd =Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),path);
        String r="";
        Articulo articuloConsulta = new Articulo(codigo,null,0,null);
        
        ObjectSet<Articulo> resultadoConsulta = bd.queryByExample(articuloConsulta);
        
	if (resultadoConsulta.size()==0){
                r="No se puede borrar";
        }
    else {
            Articulo art = resultadoConsulta.next();
            
            bd.delete(art);
            r="Borrado realizada";
        }
        
        bd.close();
    return r;
        }
  public String buscar (int codigo){
        bd =Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),path);
        String r="";
        Articulo articuloConsulta = new Articulo(codigo,null,0,null);
        ObjectSet<Articulo> resultadoConsulta = bd.queryByExample(articuloConsulta);
   if (resultadoConsulta.size()==0){
        r="No se ha encontrado el artículo";
    }
   else {
        Articulo ar = resultadoConsulta.next();
        r=ar.getCodigo()+"\t" + ar.getNombre() + "\t" + ar.getCantidad() + "\t" + ar.getDescripcion()+"\n";
   }

        bd.close();
   return r;

}

    void buscar(JTextArea jTextArea1) {
        
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
