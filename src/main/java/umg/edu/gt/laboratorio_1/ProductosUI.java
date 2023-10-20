/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.laboratorio_1;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasDAO;
import umg.edu.gt.DAO.ConsultasProductosDAO;
import umg.edu.gt.DTO.DatosProductosDTO;


/**
 *
 * @author X
 */

@ManagedBean(name="bkn_Productos")
public class ProductosUI implements Serializable {
   
    private String mensaje;
    private List<DatosProductosDTO> lista;
    private DatosProductosDTO datosInsert = new DatosProductosDTO();
    private Long idRegistro;
       
    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }   
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DatosProductosDTO> getLista() {
        return lista;
    }

    public void setLista(List<DatosProductosDTO> lista) {
        this.lista = lista;
    }

    public DatosProductosDTO getDatosInsert() {
        return datosInsert;
    }

    public void setDatosInsert(DatosProductosDTO datosInsert) {
        this.datosInsert = datosInsert;
    }   
    
     @PostConstruct
    public void init(){
        setMensaje("*******PRODUCTOS******");
        
        ConexionDAO con = new ConexionDAO();
        ConsultasProductosDAO consulta = new ConsultasProductosDAO();
        
        try {
            lista=consulta.findAllCliente();
            System.out.println("La conexion es: "+con.conexionMysql());
            System.out.println("La lista es: "+ lista.size());
            System.out.println("El nombre es:"+ lista.get(0).getNombre());
                  System.out.println("El nombre es:"+ lista.get(1).getNombre());
                   System.out.println("El nombre es:"+ lista.get(2).getNombre());
        } catch (Exception ex) {
            System.out.println("Error en la conexion: "+ex);
        } 
                
    }  //fin de postConstruct  
    
    
    
        //metodo para la insercion de nuevos clientes  
     public void insertaDatos() {

       if (datosInsert!=null && (datosInsert.getNombre()!=null || datosInsert.getDescripcion()!=null ||
       datosInsert.getPrecio()!=null || datosInsert.getCantidad()!=null) ) {

       ConexionDAO con = new ConexionDAO(); 
       try (Connection conexion = con.conexionMysql()) {
             ConsultasProductosDAO consulta = new ConsultasProductosDAO();
             consulta.insertProducto(conexion, datosInsert);
               }    
        catch (Exception ex) {
           System.out.println("ERROR AL ESTABLECER CONEXION " + ex);
       }
           } else {       
            System.out.println("VALORES NO VALIDOS " );
           }   
   }   
    
     
     //metodo para actualizar
      public void actualizarDatos() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            ConsultasProductosDAO consulta = new ConsultasProductosDAO();
            consulta.actualizarProducto(conexion, idRegistro, datosInsert);
        } catch (Exception ex) {
            System.out.println("ERROR EN LA CONEXION: " + ex);
        }
    }
     
    
}
