/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import java.util.LinkedList;
import modelos.Vendedor;

/**
 * Clase contenedora donde se almacenaran los diferentes datos insertados , 
 * asi como la informacion de los vendedores que esten en la base de datos
 * @author Natalia
 */
public class ContenedorVendedor {
    //creamos una lista para almacenar los datos de la base de datos
    private static ArrayList<Vendedor> almacenVendedor = new ArrayList<>();
    //creamos una lista para almacenar los datos insertados durante la ejecución
    private static ArrayList<Vendedor> almacenVendedorNuevos = new ArrayList<>();
    
   
   
    
    //metodos
    
    /**
     * metodo que agrega un vendedor a la lista
     * @param v 
     */
    public static void agregarVendedor(Vendedor v){
        almacenVendedor.add(v);
    }
    
    /**
     * metodo que agrega un vendedor insertado a lista
     * @param v 
     */
    public static void agregarVendedorNuevo(Vendedor v){
        almacenVendedorNuevos.add(v);
    }
    
    /**
     * metodo que muestra los datos del almacen
     */
    public static void mostrarDatosVendedor(){
        for (Vendedor v : almacenVendedorNuevos) {
            System.out.println(v);
        }
    }
    
    //getter

    /**
     * metodo que devuleve la lista de vendedor
     * @return almacenVendedor
     */
    public static ArrayList<Vendedor> getAlmacenVendedor() {
        return almacenVendedor;
    }
    
    
    
}
