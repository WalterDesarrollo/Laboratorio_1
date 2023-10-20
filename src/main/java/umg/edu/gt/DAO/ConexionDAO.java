/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author X
 */
public class ConexionDAO {
    
    private String url = "jdbc:mysql://localhost:3306/ventas"; // URL de la base de datos
    private String usuario = "root";
    private String contraseña = "";

   
    
    
    public Connection conexionMysql() throws Exception{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
        System.out.println("la conexion es exitosa: " + conexion);
        return conexion;
    }
    
    //guetters y setters de los atributos de la conexion
     public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
