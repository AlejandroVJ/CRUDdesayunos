package com.mycompany.desayunos;

import java.util.Scanner;

import models.carta;

/**
 *
 * @author AlejandroVicenteJarn
 */
public class Principal {

    public static void main(String[] args) {
       
        ConexionCarta conexionc = new ConexionCarta();
        ConexionPedidos conexionp = new ConexionPedidos();
        Scanner sc = new Scanner(System.in);
        int opciones = 0;
        //Menú selector
        do {
            System.out.println("Elige una opción: \n"
                    + "La carta: 1\n"
                    + "Crear tu pedido: 2\n"
                    + "Marca tu pedido como recogido: 3\n"
                    + "Eliminar tu pedido: 4\n"
                    + "Todas las comandas pendientes: 5\n"
                    + "Salir: 6\n");
            opciones = sc.nextInt();
            switch (opciones) {
                case 1:
                    System.out.println(conexionc.list());
                    break;
                case 2:
                    conexionp.crearPedido();
                    break;
                case 3:
                    conexionp.marcarPedido();
                    break;
                case 4:
                    conexionp.borrarPedido();
                    break;
                case 5:
                    System.out.println(conexionp.listaComanda());
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    opciones = 0;
                    System.out.println("Selecciona una opción");

            }
        }while (opciones !=6);
    }
}
