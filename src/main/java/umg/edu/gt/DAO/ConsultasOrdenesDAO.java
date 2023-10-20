/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import umg.edu.gt.DTO.DatosOrdenesDTO;

/**
 *
 * @author X
 */
public class ConsultasOrdenesDAO {
    
    
     ConexionDAO con = new ConexionDAO();
    
    public List<DatosOrdenesDTO> findAllCliente(){
           List<DatosOrdenesDTO> lista = new ArrayList<DatosOrdenesDTO>();   
   
        try {
              String query= "SELECT id, cliente_id, fecha, total FROM ordenes"; 
            
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()){
            DatosOrdenesDTO dato = new DatosOrdenesDTO();
            dato.setId(r.getLong("id"));
            dato.setCliente_id(r.getLong("cliente_id"));
            dato.setFecha(r.getString("fecha"));
            dato.setTotal(r.getString("total"));
         
            lista.add(dato);
            }
                    
        } catch (Exception ex) {
           System.out.println("Error al realizar la consulta...");
        }
 
   System.out.println("El tamaño de la lista es: "+ lista.size());
    return lista;
    
    }
    
    
     public void insertOrdenes(Connection conexion, DatosOrdenesDTO orden) {

    if(orden != null && ( orden.getCliente_id()!=null ||
                            orden.getFecha()!=null && !orden.getFecha().isEmpty() ||
                            orden.getTotal()!=null && !orden.getTotal().isEmpty()  )){
        
    String sql = "INSERT INTO ordenes (cliente_id, fecha, total) VALUES (?, ?, ?)";

    try (PreparedStatement pst = conexion.prepareStatement(sql)) {
        
        pst.setLong(1, orden.getCliente_id());
        pst.setString(2, orden.getFecha());
        pst.setString(3, orden.getTotal());
       
       
            pst.executeUpdate();
            System.out.println("Cliente ingresado correctamente");
     
    } catch (SQLException e) {
        System.out.println("Error al insertar el cliente: " + e.getMessage());
    }
  }else {
         System.out.println("ERROR EN DATOS PROPORCIONADOS ");
    } 
}  
             //metodo de actualizacion    
       public void actualizarOrden(Connection conexion, Long idDetalle, DatosOrdenesDTO datosUp) {
        String sql = "UPDATE ordenes SET ";
        
        if (datosUp.getCliente_id() != null ) {
            sql += "cliente_id = ?, ";
        }
        if (datosUp.getFecha() != null && !datosUp.getFecha().isEmpty() ) {
            sql += "fecha = ?, ";
        }
        if (datosUp.getTotal() != null && !datosUp.getTotal().isEmpty()) {
            sql += "total = ?, ";
        }
                  
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstm = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (datosUp.getCliente_id() != null ) {
                pstm.setLong(parameterIndex++, datosUp.getCliente_id());
            }
            if (datosUp.getFecha() != null && !datosUp.getFecha().isEmpty() ) {
                pstm.setString(parameterIndex++, datosUp.getFecha());
            }
            if (datosUp.getTotal() != null && !datosUp.getTotal().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getTotal());
            }
            
            pstm.setLong(parameterIndex, idDetalle);
            pstm.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }

     
    
}
