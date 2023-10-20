/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DTO;

/**
 *
 * @author X
 */
public class DatosDetallesOrdenesDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(Long orden_id) {
        this.orden_id = orden_id;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    //PARA LA TABLA DETALLES_ORDENES
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    private Long id;
    private Long  orden_id;
    private Long producto_id;
    private String cantidad;
    private String precio;

   
    
    
    
}
