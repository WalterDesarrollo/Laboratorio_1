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

import umg.edu.gt.DTO.DatosDetallesOrdenesDTO;


/**
 *
 * @author X
 */
public class ConsultaDetalleOrdenDAO {
      
    ConexionDAO con = new ConexionDAO();
    
    public List<DatosDetallesOrdenesDTO> findAllCliente(){
           List<DatosDetallesOrdenesDTO> lista = new ArrayList<DatosDetallesOrdenesDTO>();   
   
        try {
              String query= "SELECT id, orden_id, producto_id, cantidad, precio FROM detalles_ordenes"; 
            
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()){
            DatosDetallesOrdenesDTO dato = new DatosDetallesOrdenesDTO();
            dato.setId(r.getLong("id"));
            dato.setOrden_id(r.getLong("orden_id"));
            dato.setProducto_id(r.getLong("producto_id"));
            dato.setCantidad(r.getString("cantidad"));
            dato.setPrecio(r.getString("precio"));
            lista.add(dato);
            }
                    
        } catch (Exception ex) {
           System.out.println("Error al realizar la consulta...");
        }
 
   System.out.println("El tamaño de la lista es: "+ lista.size());
    return lista;
    
    }
    
  public void insertDetalle(Connection conexion, DatosDetallesOrdenesDTO detalle) {

   if( detalle.getOrden_id()!=null ||
                            detalle.getProducto_id()!=null ||
                            detalle.getCantidad()!=null && !detalle.getCantidad().isEmpty() ||
                            detalle.getPrecio()!=null &&  !detalle.getPrecio().isEmpty() ){

        String sql = "INSERT INTO detalles_ordenes (orden_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setLong(1, detalle.getOrden_id());
            pst.setLong(2, detalle.getProducto_id());
            pst.setString(3, detalle.getCantidad());
            pst.setString(4, detalle.getPrecio());

            pst.executeUpdate();
            System.out.println("DETALLE DE ORDEN ingresado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    } else {
        System.out.println("ERROR EN DATOS PROPORCIONADOS");
    }
}
    
    //metodos actualizacion 
    
    public void actualizarDetalle(Connection conexion, Long idDetalle, DatosDetallesOrdenesDTO datosUp) {
        String sql = "UPDATE detalles_ordenes SET ";
        
        if (datosUp.getOrden_id() != null ) {
            sql += "orden_id = ?, ";
        }
        if (datosUp.getProducto_id() != null ) {
            sql += "producto_id = ?, ";
        }
        if (datosUp.getCantidad() != null && !datosUp.getCantidad().isEmpty()) {
            sql += "cantidad = ?, ";
        }
        if (datosUp.getPrecio() != null && !datosUp.getPrecio().isEmpty()) {
            sql += "precio = ?, ";
        }
            
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstm = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (datosUp.getOrden_id() != null ) {
                pstm.setLong(parameterIndex++, datosUp.getOrden_id());
            }
            if (datosUp.getProducto_id() != null ) {
                pstm.setLong(parameterIndex++, datosUp.getProducto_id());
            }
            if (datosUp.getCantidad() != null && !datosUp.getCantidad().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getCantidad());
            }
            if (datosUp.getPrecio() != null && !datosUp.getPrecio().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getPrecio());
            }
            pstm.setLong(parameterIndex, idDetalle);
    
            pstm.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }


    
    
}
    
    
    
    
    

