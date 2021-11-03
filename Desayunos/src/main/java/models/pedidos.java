package models;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author AlejandroVicenteJarn
 */
public class pedidos implements Serializable {

    private Integer id;
    private String cliente;
    private Date fecha;
    private String estado;
    int cartaId;

    public pedidos() {
        
    }

    public pedidos(Integer id, String cliente, Date fecha, String estado, int cartaId) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.cartaId = cartaId;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCartaId() {
        return cartaId;
    }

    public void setCartaId(int cartaId) {
        this.cartaId = cartaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "pedidios{" + "id=" + id + ", cliente=" + cliente + ", fecha=" + fecha + ", estado=" + estado + ", cartaId=" + cartaId + '}';
    }
    
    

   
   
    }
 
    
  

