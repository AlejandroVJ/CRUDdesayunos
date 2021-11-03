package com.mycompany.desayunos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.pedidos;

/**
 *
 * @author AlejandroVicenteJarn
 */
public class ConexionPedidos implements Serializable {

    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost:3306/desayunos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearPedido() {
        String nombre;
        String estado;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserta tu nombre: ");
        nombre = sc.next();
        System.out.println("Inserta el estado del producto: ");
        estado = sc.next();
        System.out.println("Inserta el Id del producto que desea: ");
        int cartaId = sc.nextInt();

        try ( PreparedStatement ps = con.prepareStatement(CREATE_PEDIDO, RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, estado);
            ps.setInt(3, cartaId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPedidos.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    
    public void marcarPedido(){
        int pedidoId;
        Scanner sc = new Scanner (System.in);
        System.out.println("Inserta el id del pedido que se va a recoger: ");
        pedidoId = sc.nextInt();
        
        try (PreparedStatement ps = con.prepareStatement(MARACAR_PEDIDO)){
            ps.setInt(1, pedidoId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(ConexionPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }  
    
    public int borrarPedido (){
        int pedido;
        int r = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserta el id del pedido que quieres eliminar: ");
        pedido = sc.nextInt();
        
        try (PreparedStatement ps = con.prepareStatement(DELETE_PEDIDO)) {
            ps.setInt(1, pedido);
            r = ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(ConexionPedidos.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
    }
    
    public ArrayList<pedido> listaComanda(){
        var lista = new ArrayList<pedido>();
        
        try (Statement st = con.createStatement()){
            ResultSet resultado = st.executeQuery(LIST_COMANDAS);
            
            while (resultado.next()){
                Pedido ped = new Pedido();
                ped.setId(resultado.getInt("id"));
                ped.setCliente(resultado.getString("cliente"));
                ped.setFecha(resultado.getString("fecha"));
                ped.setEstado(resultado.getString("estado"));
                ped.CartaId(resultado.getInt("carta_id"));
                
                
                salida.add(ped);
            }
            
        } catch (SQLException e) {
              Logger.getLogger(ConexionPedidos.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return salida;
    
    
    
    
    }

}

