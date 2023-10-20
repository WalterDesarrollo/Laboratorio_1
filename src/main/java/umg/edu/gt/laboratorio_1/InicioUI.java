/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.laboratorio_1;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import umg.edu.gt.DAO.ConexionDAO;
import umg.edu.gt.DAO.ConsultasDAO;
import umg.edu.gt.DTO.DatosDTO;


//esto es una notacion, para llamarlo dedede el xhtml
@ManagedBean(name="bkn_Inicio")
public class InicioUI implements Serializable{
    private String mensaje;
    private String dato1;
    private String dato2;
    private String dato3;
    private boolean band;
    private List<DatosDTO> lista;
    private DatosDTO datosInsert = new DatosDTO();


    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }
    private Long idRegistro;


 
 
    @PostConstruct
    public void init(){
        setMensaje("*******  CLIENTES  *******");
        
        ConexionDAO con = new ConexionDAO();
        ConsultasDAO consulta = new ConsultasDAO();
        
        try {
            lista=consulta.findAllCliente();
            System.out.println("La conexion es: "+con.conexionMysql());
            System.out.println("La lista es: "+ lista.size());
            System.out.println("El nombre es:"+ lista.get(0).getNombre());
                  System.out.println("El nombre es:"+ lista.get(1).getNombre());
                   System.out.println("El nombre es:"+ lista.get(2).getNombre());
        } catch (Exception ex) {
            System.out.println("Error en la conexion: "+ex);
        } 
            
    
    
    }  //fin de postConstruct  
    
   
 
    
   //metodo para la insercion de nuevos clientes  
  public void insertaDatos() {

    if (datosInsert!=null && (datosInsert.getNombre()!=null || datosInsert.getCorreo()!=null ||
    datosInsert.getDireccion()!=null || datosInsert.getTelefono()!=null) ) {
      
    ConexionDAO con = new ConexionDAO(); 
    try (Connection conexion = con.conexionMysql()) {
          ConsultasDAO consulta = new ConsultasDAO();
          consulta.insertCliente(conexion, datosInsert);
            }    
     catch (Exception ex) {
        System.out.println("ERROR AL ESTABLECER CONEXION " + ex);
    }
        } else {       
         System.out.println("VALORES NO VALIDOS " );
        }   
}   
    
    
    
    public void actualizarDatos() {
        ConexionDAO con = new ConexionDAO();
        try (Connection conexion = con.conexionMysql()) {
            ConsultasDAO consulta = new ConsultasDAO();
            consulta.actualizarCliente(conexion, idRegistro, datosInsert);
        } catch (Exception ex) {
            System.out.println("ERROR EN LA CONEXION: " + ex);
        }
    }
     
    
    
    public String getMensaje() {
        return mensaje;
    }
    public void mensaje1(){
        mensaje="Hola mundo desde un metodo ------>";
    }
    /**
     * @param mensaje the mensaje to set
     */
    public void motrarDatos(){
        System.out.println("Dato1:"+getDato1());
        System.out.println("Dato2:"+getDato2());
        System.out.println("Nombre:"+dato1+"Apellido:"+dato2);
        dato3="Nombre:  "+dato1+"-- Apellido: -"+dato2;
        band= true;
    }
    
      
    
    
    //prueba de retorno proyecto WS creado en clase
      public void consumeWS() throws IOException, InterruptedException{
        String url= "http://localhost:8080/LabWD/webresources/DesarrolloWeb/primerWS"; //url obtenido del proyecto 
        HttpClient client = HttpClient.newHttpClient();
        // Crear una solicitud GET sin parámetros
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Imprimir el cuerpo de la respuesta
        System.out.println(response.body());
        // Crear una solicitud POST con parámetros
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
               .build();
        // Enviar la solicitud y obtener la respuesta
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Imprimir el cuerpo de la respuesta
        System.out.println(response.body());
        
    }
      
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the dato1
     */
    public String getDato1() {
        return dato1;
    }

    /**
     * @param dato1 the dato1 to set
     */
    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    /**
     * @return the dato2
     */
    public String getDato2() {
        return dato2;
    }

    /**
     * @param dato2 the dato2 to set
     */
    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    /**
     * @return the dato3
     */
    public String getDato3() {
        return dato3;
    }

    /**
     * @param dato3 the dato3 to set
     */
    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }

    /**
     * @return the band
     */
    public boolean isBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(boolean band) {
        this.band = band;
    }
    
  public List<DatosDTO> getLista() {
        return lista;
    }

    public void setLista(List<DatosDTO> lista) {
        this.lista = lista;
    }
    
     public DatosDTO getDatosInsert() {
        return datosInsert;
    }

    public void setDatosInsert(DatosDTO datosInsert) {
        this.datosInsert = datosInsert;
    }
 

}