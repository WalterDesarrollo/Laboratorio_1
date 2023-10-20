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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import umg.edu.gt.DTO.DatosDTO;

/**
 *
 * @author X
 */
public class ConsultasDAO {
    ConexionDAO con = new ConexionDAO();
    
    public List<DatosDTO> findAllCliente(){
           List<DatosDTO> lista = new ArrayList<DatosDTO>();   
   
        try {
              String query= "SELECT id, nombre, correo, direccion, telefono FROM clientes"; 
            
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()){
            DatosDTO dato = new DatosDTO();
            dato.setId(r.getLong("id"));
            dato.setNombre(r.getString("nombre"));
            dato.setDireccion(r.getString("direccion"));
            dato.setCorreo(r.getString("correo"));
            dato.setTelefono(r.getString("telefono"));
            lista.add(dato);
            }
                    
        } catch (Exception ex) {
           System.out.println("Error al realizar la consulta...");
        }
 
   System.out.println("El tamaño de la lista es: "+ lista.size());
    return lista;
    
    }
    
   
    
    public void insertCliente(Connection conexion, DatosDTO cliente) {

    if(cliente != null && ( cliente.getNombre()!=null && !cliente.getNombre().isEmpty()||
                            cliente.getCorreo()!=null && !cliente.getCorreo().isEmpty() ||
                            cliente.getDireccion()!=null && !cliente.getDireccion().isEmpty() ||
                            cliente.getTelefono()!=null &&  !cliente.getTelefono().isEmpty() )){
        
    String sql = "INSERT INTO clientes (nombre, correo, direccion, telefono) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pst = conexion.prepareStatement(sql)) {
        
        pst.setString(1, cliente.getNombre());
        pst.setString(2, cliente.getCorreo());
        pst.setString(3, cliente.getDireccion());
        pst.setString(4, cliente.getTelefono());
       
            pst.executeUpdate();
            System.out.println("Cliente ingresado correctamente");
     
    } catch (SQLException e) {
        System.out.println("Error al insertar el cliente: " + e.getMessage());
    }
  }else {
         System.out.println("ERROR EN DATOS PROPORCIONADOS ");
    } 
}  
             
    //metodo para actualizar datos 
    
    
    public void actualizarCliente(Connection conexion, Long idCliente, DatosDTO datosUp) {
        String sql = "UPDATE clientes SET ";
        
        if (datosUp.getNombre() != null && !datosUp.getNombre().isEmpty()) {
            sql += "nombre = ?, ";
        }
        if (datosUp.getCorreo() != null && !datosUp.getCorreo().isEmpty()) {
            sql += "correo = ?, ";
        }
        if (datosUp.getDireccion() != null && !datosUp.getDireccion().isEmpty()) {
            sql += "direccion = ?, ";
        }
        if (datosUp.getTelefono() != null && !datosUp.getTelefono().isEmpty()) {
            sql += "telefono = ?, ";
        }
    
        
        sql = sql.substring(0, sql.length() - 2);
    
        sql += " WHERE id = ?";
        
        try (PreparedStatement pstm = conexion.prepareStatement(sql)) {
            int parameterIndex = 1;
            if (datosUp.getNombre() != null && !datosUp.getNombre().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getNombre());
            }
            if (datosUp.getCorreo() != null && !datosUp.getCorreo().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getCorreo());
            }
            if (datosUp.getDireccion() != null && !datosUp.getDireccion().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getDireccion());
            }
            if (datosUp.getTelefono() != null && !datosUp.getTelefono().isEmpty()) {
                pstm.setString(parameterIndex++, datosUp.getTelefono());
            }
            pstm.setLong(parameterIndex, idCliente);
    
            pstm.executeUpdate();
    
            System.out.println("Datos actualizados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar datos: " + e.getMessage());
        }
    }
    
    
}
    

   

