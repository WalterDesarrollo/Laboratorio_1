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

import umg.edu.gt.DTO.DatosProductosDTO;

/**
 *
 * @author X
 */
public class ConsultasProductosDAO {
    
    
     ConexionDAO con = new ConexionDAO();
    
    public List<DatosProductosDTO> findAllCliente(){
           List<DatosProductosDTO> lista = new ArrayList<DatosProductosDTO>();   
   
        try {
              String query= "SELECT id, nombre, descripcion, cantidad, precio FROM productos"; 
            
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()){
            DatosProductosDTO dato = new DatosProductosDTO();
            dato.setId(r.getLong("id"));
            dato.setNombre(r.getString("nombre"));
            dato.setDescripcion(r.getString("descripcion"));
            dato.setPrecio(r.getString("precio"));
            dato.setCantidad(r.getString("cantidad"));
            lista.add(dato);
            }
                    
        } catch (Exception ex) {
           System.out.println("Error al realizar la consulta...");
        }
 
   System.out.println("El tamaño de la lista es: "+ lista.size());
    return lista;
    
    }
    
    
    public void insertProducto(Connection conexion, DatosProductosDTO producto) {

    if (producto != null &&(
        producto.getNombre() != null && !producto.getNombre().isEmpty() ||
        producto.getDescripcion() != null && !producto.getDescripcion().isEmpty() ||
        producto.getPrecio() != null && !producto.getPrecio().isEmpty()  ||
        producto.getCantidad() != null && !producto.getCantidad().isEmpty())) {

        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getPrecio());
            pst.setString(4, producto.getCantidad());

            pst.executeUpdate();
            System.out.println("Producto ingresado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    } else {
        System.out.println("ERROR EN DATOS PROPORCIONADOS");
    }
}
    
    //metodos actualizacion 
     
    public void actualizarProducto(Connection conexion, Long idProducto, DatosProductosDTO datosUp) {
        String sql = "UPDATE productos SET ";
        
        if (datosUp.getNombre() != null && !datosUp.getNombre().isEmpty()) {
            sql += "nombre = ?, ";
        }
        if (datosUp.getDescripcion() != null && !datosUp.getDescripcion().isEmpty()) {
            sql += "descripcion = ?, ";
        }
        if (datosUp.getPrecio() != null && !datosUp.getPrecio().isEmpty()) {
            sql += "precio = ?, ";
        }
        if (datosUp.getCantidad() != null && !datosUp.getCantidad().isEmpty()) {
            sql += "cantidad = ?, ";
        }
    
        
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstm = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (datosUp.getNombre() != null && !datosUp.getNombre().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getNombre());
            }
            if (datosUp.getDescripcion() != null && !datosUp.getDescripcion().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getDescripcion());
            }
            if (datosUp.getPrecio() != null && !datosUp.getPrecio().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getPrecio());
            }
            if (datosUp.getCantidad() != null && !datosUp.getCantidad().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getCantidad());
            }
            pstm.setLong(parameterIndex, idProducto);
    
            pstm.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }
    
    
    
}
