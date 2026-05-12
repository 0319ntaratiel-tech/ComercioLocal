/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;


import java.util.ArrayList;
import modelos.Pedido;

/**
 *
 * @author isard
 */
public class ContenedorPedido {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private static ArrayList<Pedido> almacenPedidos;
    private static ArrayList<Pedido> almacenPedidosNuevos;
    
    //constructor

    public ContenedorPedido() {
        this.almacenPedidos = new ArrayList<>();
        this.almacenPedidosNuevos = new ArrayList<>();
    }
    
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
