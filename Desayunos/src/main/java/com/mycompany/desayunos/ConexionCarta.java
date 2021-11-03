package com.mycompany.desayunos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.carta;

/**
 *
 * @author AlejandroVicenteJarn
 */
public class ConexionCarta {

    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost:3306/desayunos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static final String LIST_QUERY = "SELECT * FROM carta";
    static final String INSERT_QUERY = "INSERT INTO carta(nombre,precio) VALUES (?,?)";
    static final String GET_QUERY = "SELECT * FROM carta WHERE id=?";
    static final String REMOVE_QUERY = "DELETE FROM carta WHERE id=?";
    static final String COUNT_QUERY = "select count(id) as total from carta";

    public ArrayList<carta> list() {
        var salida = new ArrayList<carta>();
        try ( Statement st = con.createStatement()) {

            ResultSet resultado = st.executeQuery(LIST_QUERY);

            while (resultado.next()) {
                carta c = new carta();
                c.setId(resultado.getInt("id"));
                c.setNombre(resultado.getString("nombre"));
                c.setPrecio(resultado.getInt("precio"));

                salida.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;

    }

    public Integer save(carta t) {

        try ( PreparedStatement ps = con.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getPrecio());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public carta get(Integer id) {

        try ( PreparedStatement ps = con.prepareStatement(GET_QUERY)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                carta c = new carta();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setPrecio(rs.getInt("precio"));

                return c;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public boolean remove(Integer id) {

        try ( PreparedStatement ps = con.prepareStatement(REMOVE_QUERY)) {
            ps.setInt(1, id);
            return (ps.executeUpdate()==1);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
     public Integer count(){
        try ( Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(COUNT_QUERY);            
            rs.next();
            return(rs.getInt("total"));
            
        }   catch (SQLException ex) {
            Logger.getLogger(ConexionCarta.class.getName()).log(Level.SEVERE, null, ex);            
            return 0;
        }
    }

}
