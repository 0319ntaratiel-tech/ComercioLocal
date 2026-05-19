/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import modelos.Fabricante;

/**
 * Clase contenedora donde se almacenaran los diferentes datos insertados , 
 * asi como la informacion de los fabricantes que esten en la base de datos
 * @author Natalia
 */
public class ContenedorFabricante {
    //creamos una lista para almacenar los datos de la base de datos
    private static ArrayList<Fabricante> almacenFabricantes=new ArrayList<>();
    //creamos una lista para almacenar los datos insertados durante la ejecución
    private static ArrayList<Fabricante> almacenFabricantesNuevo= new ArrayList<>();
    
  
    //metodos
    
    /**
     * metodo que agrega un fabricante a la lista
     * @param f 
     */
    public static void agregarFabricante(Fabricante f){
        almacenFabricantes.add(f);
    }
    
    /**
     * metodo que agrega un fabricante insertado a la lista
     * @param f 
     */
    public static void agregarFabricanteNuevo(Fabricante f){
        almacenFabricantesNuevo.add(f);
    }
    
    /**
     * metodo que muestra los datos del almacen 
     */
    public static void mostrarDatosFabricante(){
        for (Fabricante f : almacenFabricantesNuevo) {
            System.out.println(f);
        }
    }
    //getter

    /**
     * metodo que devuelve la lista de fabricantes
     * @return almacenFabricantes
     */
    public static ArrayList<Fabricante> getFabricantes() {
        return almacenFabricantes;
    }
    
}
