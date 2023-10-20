package controlers;

import java.util.List;
import models.Clientes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

// crud con hibernate 
public class UsuarioControlers {
    
 public  String createCliente(String nombre, String correo, String direccion, String telefono){    
   SessionFactory sessionFactory   = new
   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(Clientes.class).
           buildSessionFactory();
       
   Session session = sessionFactory.openSession();
   
       try {
           
      Clientes clientes = new Clientes(nombre, correo, direccion, telefono);  
      session.beginTransaction(); //para abrir la conexion a la base de datos 
      
      session.save(clientes);
      
      session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
      
      sessionFactory.close(); //para cerrar todo
      
       return "Cliente Creado";            
       } catch (Exception e) {
           e.printStackTrace();
       }
   
     return "Error al crear Clientes";
       
    }
  
   //metodo para eliminar clientes 
 
 	public String EliminarCliente(int id) {
 		
 	   SessionFactory sessionFactory   = new
 	   Configuration().configure("hibernate.cfg.xml").
           addAnnotatedClass(Clientes.class).
           buildSessionFactory();
 			       
            Session session = sessionFactory.openSession();
 			   
 	 try {
 			           
        session.beginTransaction(); //para abrir la conexion a la base de datos 
 			      
        Clientes cliente = session.get(Clientes.class, id); //para obtener el id del cliente a eliminar
 			      
        session.delete(cliente);
 			      
 	session.getTransaction().commit(); //para que grabe los cambios en la bse de datos 
 			      
        sessionFactory.close(); //para cerrar todo
 			      
        return "Cliente Eliminado correctamente";    
 			       
        } catch (Exception e) {
 	   e.printStackTrace();
       }
 				
 		return "Error al eliminar CLIENTE";
 	}
    
        //metodo de actualizacion de clientes

    public String ActualizarCliente(int id, String nombre, String correo, String direccion, String telefono) {

      // Crear una instancia de la SessionFactory.
      SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

      // Crear una sesión.
      Session session = sessionFactory.openSession();

      // Iniciar una transacción.
      Transaction transaction = session.beginTransaction();

      // Obtener el cliente de la base de datos.
      Clientes cliente = session.get(Clientes.class, id);

      // Actualizar los datos del cliente.
      if (nombre != null && !nombre.isEmpty()) {
        cliente.setNombre(nombre);
      }
      if (correo != null && !correo.isEmpty()) {
        cliente.setCorreo(correo);
      }
      if (direccion != null && !direccion.isEmpty()) {
        cliente.setDireccion(direccion);
      }
      if (telefono != null && !telefono.isEmpty()) {
        cliente.setTelefono(telefono);
      }

      // Guardar los cambios en la base de datos.
      session.update(cliente);

      // Confirmar la transacción.
      transaction.commit();

      // Cerrar la sesión y la SessionFactory.
      session.close();
      sessionFactory.close();

      // Devolver un mensaje de éxito o error.
      if (cliente != null) {
        return "Cliente actualizado correctamente";
      } else {
        return "Error al actualizar cliente";
      }
    }
        
        //para mostrar los clientes 
  public List<Clientes> getClientes() {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Clientes.class)
            .buildSessionFactory();

    Session session = sessionFactory.openSession();

    try {
        session.beginTransaction();

        Query query = session.createQuery("from Clientes");

        List<Clientes> clientes = query.list();

        session.getTransaction().commit();

        sessionFactory.close();

        return clientes;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
        
 	
 	
}// fin de la clase
