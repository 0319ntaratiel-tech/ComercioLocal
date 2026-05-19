/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;


import java.util.ArrayList;
import modelos.Pedido;

/**
 * Clase contenedora donde se almacenaran los diferentes datos insertados , 
 * asi como la informacion de los pedidos que esten en la base de datos
 * @author Natalia
 */
public class ContenedorPedido {
    //creamos una lista para almacenar los datos de la base de datos
    private static ArrayList<Pedido> almacenPedidos = new ArrayList<>();
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private static ArrayList<Pedido> almacenPedidosNuevos = new ArrayList<>();
    
    
    
    //metodos
    /**
     * metodo que agrega pedidos a la lista
     * @param ped 
     */
    public static  void agregarPedido(Pedido ped){
        almacenPedidos.add(ped);
    }
    
    /**
     * metodo que agrega pedidos insertados a la lista
     * @param ped 
     */
    public static  void agregarPedidoNuevos(Pedido ped){
        almacenPedidosNuevos.add(ped);
    }
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public static void mostrarDatosPedidos(){
        for (Pedido p : almacenPedidosNuevos) {
            System.out.println(p);
        }
    }
    
    //getter

    /**
     * metodo que devuelve la lista de pedidos
     * @return almacenPedidos
     */
    public static ArrayList<Pedido> getAlmacenPedidos() {
        return almacenPedidos;
    }
    
    
}
