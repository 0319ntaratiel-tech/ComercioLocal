/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.HashSet;
import java.util.Set;
import modelos.Pedido;

/**
 *
 * @author isard
 */
public class ContenedorPedido {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private Set<Pedido> almacenPedidos;
    
    //constructor

    public ContenedorPedido() {
        this.almacenPedidos = new HashSet<>();
    }
    
    //metodos
    /**
     * metodo que agrega pedidos a la lista
     * @param ped 
     */
    public void agregarPedido(Pedido ped){
        almacenPedidos.add(ped);
    }
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public void mostrarDatosPedidos(){
        for (Pedido p : almacenPedidos) {
            System.out.println(p);
        }
    }
    
    //getter

    /**
     * metodo que devuelve la lista de pedidos
     * @return almacenPedidos
     */
    public Set<Pedido> getAlmacenPedidos() {
        return almacenPedidos;
    }
    
    
}
