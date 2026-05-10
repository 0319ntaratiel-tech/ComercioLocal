/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.LinkedList;
import modelos.Vendedor;

/**
 *
 * @author isard
 */
public class ContenedorVendedor {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private LinkedList<Vendedor> almacenVendedor;
    
    //constructor

    public ContenedorVendedor() {
        this.almacenVendedor = new LinkedList<>();
    }
    
    //metodos
    
    /**
     * metodo que agrega un vendedor a la lista
     * @param v 
     */
    public void agregarVendedor(Vendedor v){
        almacenVendedor.add(v);
    }
    
    /**
     * metodo que muestra los datos del almacen
     */
    public void mostrarDatosVendedor(){
        for (Vendedor v : almacenVendedor) {
            System.out.println(v);
        }
    }
    
    //getter

    /**
     * metodo que devuleve la lista de vendedor
     * @return almacenVendedor
     */
    public LinkedList<Vendedor> getAlmacenVendedor() {
        return almacenVendedor;
    }
    
    
    
}
