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
import umg.edu.gt.DAO.ConsultasOrdenesDAO;

import umg.edu.gt.DTO.DatosOrdenesDTO;

/**
 *
 * @author X
 */


@ManagedBean(name="bkn_Ordenes")
public class OrdenesUI implements Serializable{

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DatosOrdenesDTO> getLista() {
        return lista;
    }

    public void setLista(List<DatosOrdenesDTO> lista) {
        this.lista = lista;
    }

    public DatosOrdenesDTO getDatosInsert() {
        return datosInsert;
    }

    public void setDatosInsert(DatosOrdenesDTO datosInsert) {
        this.datosInsert = datosInsert;
    }

    public Long getIdInsert() {
        return idInsert;
    }

    public void setIdInsert(Long idInsert) {
        this.idInsert = idInsert;
    }
    
    private Long idInsert;
    private String mensaje; 
    private List<DatosOrdenesDTO> lista;
    private DatosOrdenesDTO datosInsert = new DatosOrdenesDTO();
    
       
     @PostConstruct
    public void init(){
        
    setMensaje("*******   ORDENES   ******");
      
        ConexionDAO con = new ConexionDAO();
        ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();
        
        try {
            lista=consulta.findAllCliente();
            System.out.println("La conexion es: "+con.conexionMysql());
            System.out.println("La lista es: "+ lista.size());
         
        } catch (Exception ex) {
            System.out.println("Error en la conexion: "+ex);
        } 
              
    
    }  //fin de postConstruct 
    
    
    
     //metodo para la insercion 
  public void insertaDatos() {

    if (datosInsert!=null && (datosInsert.getCliente_id()!=null || datosInsert.getFecha()!=null ||
    datosInsert.getTotal()!=null ) ) {
      
    ConexionDAO con = new ConexionDAO(); 
    try (Connection conexion = con.conexionMysql()) {
          ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();
          consulta.insertOrdenes(conexion, datosInsert);
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
           ConsultasOrdenesDAO consulta = new ConsultasOrdenesDAO();
            consulta.actualizarOrden(conexion, idInsert, datosInsert);
        } catch (Exception ex) {
            System.out.println("ERROR EN LA CONEXION: " + ex);
        }
    }  
    
    
}
