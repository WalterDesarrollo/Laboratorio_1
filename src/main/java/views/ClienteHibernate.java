/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controlers.UsuarioControlers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.Clientes;


@ManagedBean(name="bkn_Cliente")
public class ClienteHibernate implements Serializable{
    
      private List<Clientes> lista = new ArrayList<>();
      private Clientes datosInsert = new Clientes();

    public Clientes getDatosInsert() {
        return datosInsert;
    }

    public void setDatosInsert(Clientes datosInsert) {
        this.datosInsert = datosInsert;
    }
      
      

    public List<Clientes> getLista() {
        return lista;
    }

    public void setLista(List<Clientes> lista) {
        this.lista = lista;
    }
    
    
     @PostConstruct
    public void init(){
        
        UsuarioControlers usuarioControlers = new UsuarioControlers();
        List<Clientes> clientes = usuarioControlers.getClientes();
        
         for(Clientes cliente : clientes){
            lista.add(cliente);
        }               
    }
    
    
    //metodo para insertar cliente
    public void insertarCliente(){
    
   String nombre = datosInsert.getNombre();
   String correo = datosInsert.getCorreo();
   String direccion = datosInsert.getDireccion();
   String telefono = datosInsert.getTelefono();
        
    String cliente = new UsuarioControlers().createCliente(nombre, correo, direccion, telefono);
    
    }
    
    //metodo para actualizar cliente
    
    public void actualizarCliente(){
        
   int id = datosInsert.getId();
   String nombre = datosInsert.getNombre();
   String correo = datosInsert.getCorreo();
   String direccion = datosInsert.getDireccion();
   String telefono = datosInsert.getTelefono();
        
      String cliente = new UsuarioControlers().ActualizarCliente(id, nombre, correo, direccion, telefono);     
    }
   
    public void eliminarCliente(){
        
    int id = datosInsert.getId();
    
    String cliente = new UsuarioControlers().EliminarCliente(id);
    
    }
    
   
}
