/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import modelos.Cliente;

/**
 *
 * @author isard
 */
public class ContenedorCliente {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private static ArrayList<Cliente> almacenClientes = new ArrayList<>();
    private static ArrayList<Cliente> almacenClientesNuevos = new ArrayList<>();

    
    
    //metodos
    /**
     * metodo que agrega un cliente a la lista
     * @param c 
     */
    public static void agregarCliente(Cliente c){
        almacenClientes.add(c);
    }
    
    /**
     * metodo que agrega un cliente insertado a la lista
     * @param c 
     */
    public static void agregarClienteNuevo(Cliente c){
        almacenClientesNuevos.add(c);
    }
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public static void mostrarDatosClientes(){
        for (Cliente c : almacenClientesNuevos) {
            System.out.println(c);
        }
    }
    //getter

    /**
     * metodo que devuelve la lista de clientes
     * @return almacenClientes
     */
    public static ArrayList<Cliente> getAlmacenClientes() {
        return almacenClientes;
    }
    
    
}
