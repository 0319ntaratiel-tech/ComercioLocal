/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import modelos.Producto;

/**
 *
 * @author isard
 */
public class ContenedorProducto {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private ArrayList<Producto> almacenProductos;

    //constructor
    public ContenedorProducto() {
        this.almacenProductos = new ArrayList<>();
    }
    
    //metodos
    
    /**
     * metodo que permite agregar un producto a la lista
     * @param pro 
     */
    public void agregarProducto(Producto pro){
        almacenProductos.add(pro);
    }
    
    /**
     * metodo que muestra la informacion del almacen
     */
    public void mostrarDatosProductos(){
        for (Producto pro : almacenProductos) {
            System.out.println(pro);
            
        }
    }
    
    /**
     * metodo que muestra el precio de venta del producto
     */
    public double mostrarPrecioVenta(){
        double precioVenta = 0;
        for (Producto pro : almacenProductos) {
           precioVenta =  pro.getPrecioVenta();
        }
        
        return precioVenta;
    }
    //getter

    /**
     * metodo que devuelve la lista de productos
     * @return almacenProductos
     */
    public ArrayList<Producto> getAlmacenProductos() {
        return almacenProductos;
    }
    
    
}
