package views;

import controlers.UsuarioControlers;
import java.util.List;
import models.Clientes;



public class ClienteView {  
    
    public static void main(String[] args){
    
        
    	//String cliente = new UsuarioControlers().createCliente("Kevin"," kev@gmail.com", "ciudad", "76543647");
   
    	//String cliente = new UsuarioControlers().EliminarCliente(12);
    	
    	// String cliente = new UsuarioControlers().ActualizarCliente(11, "Raul");
    	 
    	 List<Clientes> cliente = new UsuarioControlers().getClientes();
    	   
    	System.out.println(cliente);
    
    
    }
    
}
