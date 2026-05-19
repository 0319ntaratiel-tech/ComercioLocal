/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import modelos.Producto;

/**
 *  Clase contenedora donde se almacenaran los diferentes datos insertados , 
 * asi como la informacion de los productos que esten en la base de datos
 * @author Natalia
 */
public class ContenedorProducto {

    //creamos una lista para almacenar los datos de la base de datos
    private static Set<Producto> almacenProductos = new HashSet<>();
    //creamos una lista para almacenar los datos insertados durante la ejecución
    private static Set<Producto> almacenProductosNuevo = new HashSet<>();

   
    //metodos
    /**
     * metodo que permite agregar un producto a la lista
     * @param pro
     */
    public static void agregarProducto(Producto pro) {
        almacenProductos.add(pro);
    }
    
    /**
     * metodo que permite agregar un producto insertado a la lista
     * @param pro
     */
    public static void agregarProductoNuevo(Producto pro) {
        almacenProductosNuevo.add(pro);
    }

    /**
     * metodo que muestra la informacion del almacen
     */
    public static void mostrarDatosProductos() {
        for (Producto pro : almacenProductosNuevo) {
            System.out.println(pro);

        }
    }

    
    //getter

    /**
     * metodo que devuelve la lista de productos
     * @return almacenProductos
     */
    public static Set<Producto> getAlmacenProductos() {
        return almacenProductos;
    }

}
