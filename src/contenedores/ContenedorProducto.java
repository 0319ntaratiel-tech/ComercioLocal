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
 *
 * @author isard
 */
public class ContenedorProducto {

    //creamos una lista para almecenar los datos insertados durante la ejecución
    private static Set<Producto> almacenProductos = new HashSet<>();
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

    /**
     * metodo que muestra el precio de venta del producto
     */
    public static double mostrarPrecioVenta() {
        double precioVenta = 0;
        for (Producto pro : almacenProductos) {
            precioVenta = pro.getPrecioVenta();
        }

        return precioVenta;
    }
    //getter

    /**
     * metodo que devuelve la lista de productos
     *
     * @return almacenProductos
     */
    public static Set<Producto> getAlmacenProductos() {
        return almacenProductos;
    }

}
