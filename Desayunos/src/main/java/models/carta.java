package models;

import java.io.Serializable;

/**
 *
 * @author AlejandroVicenteJarn
 */
public class carta implements Serializable {

    private Integer id;
    private String nombre;
    private Integer precio;

    public carta() {
    }

    public carta(Integer id, String nombre, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "carta{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + '}';
    }

}
