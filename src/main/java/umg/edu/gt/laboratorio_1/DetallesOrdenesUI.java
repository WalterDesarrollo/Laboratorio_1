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
import umg.edu.gt.DAO.ConsultaDetalleOrdenDAO;

import umg.edu.gt.DTO.DatosDetallesOrdenesDTO;



@ManagedBean(name="bkn_Detalles")
public class DetallesOrdenesUI implements Serializable{
 private String mensaje; 
 private List<DatosDetallesOrdenesDTO> lista;
  private DatosDetallesOrdenesDTO nuevoDato = new DatosDetallesOrdenesDTO();
   private Long idRegistro;
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

    public DatosDetallesOrdenesDTO getNuevoDato() {
        return nuevoDato;
    }

    public void setNuevoDato(DatosDetallesOrdenesDTO nuevoDato) {
        this.nuevoDato = nuevoDato;
    }  

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }
    
     
      @PostConstruct
    public void init(){
        
    setMensaje("*******   DETALLE DE ORDENES   ******");
      
        ConexionDAO con = new ConexionDAO();
        ConsultaDetalleOrdenDAO consulta = new ConsultaDetalleOrdenDAO();
        
        try {
            lista=consulta.findAllCliente();
            System.out.println("La conexion es: "+con.conexionMysql());
            System.out.println("La lista es: "+ lista.size());
         
        } catch (Exception ex) {
            System.out.println("Error en la conexion: "+ex);
        } 
              
    
    }  //fin de postConstruct 
     
        
        //metodo para la insercion de detalle orden
     public void insertaDatosOrdenes() {

       if (nuevoDato !=null &&(nuevoDato.getOrden_id()!=null || nuevoDato.getProducto_id()!=null ||
       nuevoDato.getCantidad()!=null || nuevoDato.getPrecio()!=null) ) {

       ConexionDAO con = new ConexionDAO(); 
       try (Connection conexion = con.conexionMysql()) {
             ConsultaDetalleOrdenDAO consulta = new ConsultaDetalleOrdenDAO();
             consulta.insertDetalle(conexion, nuevoDato);
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
           ConsultaDetalleOrdenDAO consulta = new ConsultaDetalleOrdenDAO();
            consulta.actualizarDetalle(conexion, idRegistro, nuevoDato);
        } catch (Exception ex) {
            System.out.println("ERROR EN LA CONEXION: " + ex);
        }
    }  
    
    
    
     public List<DatosDetallesOrdenesDTO> getLista() {
        return lista;
    }

    public void setLista(List<DatosDetallesOrdenesDTO> lista) {
        this.lista = lista;
    }

    
}
