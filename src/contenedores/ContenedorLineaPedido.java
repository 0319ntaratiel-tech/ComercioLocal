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
    private Map<Integer , LineaPedido> almacenLineasPedidos;
    private int contador;
    
    //constructor
    public ContenedorLineaPedido() {
        this.almacenLineasPedidos = new HashMap<>();
        contador = 1;
    }
    
    //metodos
    
    /**
     * metodo que agrega una linea de pedido a la lista
     * @param lp 
     */
    public void agregarLineaPedido(LineaPedido lp){
        
        almacenLineasPedidos.put(contador, lp);
        contador++;
    }
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public void mostrarLineaPedido(){
        for (LineaPedido lp : almacenLineasPedidos.values()) {
            System.out.println(lp);
        }
    }
    //getter

    /**
     * metodo que devuelve la lista de lineas pedidos
     * @return almacenLineasPedidos
     */
    public Map<Integer, LineaPedido> getAlmacenLineasPedidos() {
        return almacenLineasPedidos;
    }
    
}
