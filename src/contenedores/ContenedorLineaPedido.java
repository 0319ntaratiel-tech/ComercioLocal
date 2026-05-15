/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.HashMap;
import java.util.Map;
import modelos.LineaPedido;

/**
 *
 * @author isard
 */
public class ContenedorLineaPedido {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private static Map<Integer , LineaPedido> almacenLineasPedidos =  new HashMap<>();
    private static Map<Integer , LineaPedido> almacenLineasPedidosNuevos = new HashMap<>();
    private static int contador = 1;
    
   
    //metodos
    
    /**
     * metodo que agrega una linea de pedido a la lista
     * @param lp 
     */
    public static void agregarLineaPedido(LineaPedido lp){
        
        almacenLineasPedidos.put(contador, lp);
        contador++;
    }
    
    /**
     * metodo que agrega las lineas de pedido insertadas a la lista
     * @param lp 
     */
    public static void agregarLineaPedidoNuevos(LineaPedido lp){
        
        almacenLineasPedidosNuevos.put(contador, lp);
        contador++;
    }
    
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public static void mostrarLineaPedido(){
        for (LineaPedido lp : almacenLineasPedidosNuevos.values()) {
            System.out.println(lp);
        }
    }
    //getter

    /**
     * metodo que devuelve la lista de lineas pedidos
     * @return almacenLineasPedidos
     */
    public static  Map<Integer, LineaPedido> getAlmacenLineasPedidos() {
        return almacenLineasPedidos;
    }
    
}
