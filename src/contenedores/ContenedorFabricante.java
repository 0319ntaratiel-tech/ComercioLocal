/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedores;

import java.util.ArrayList;
import modelos.Fabricante;

/**
 *
 * @author isard
 */
public class ContenedorFabricante {
    //creamos una lista para almecenar los datos insertados durante la ejecución
    private ArrayList<Fabricante> almacenFabricantes;
    
    //constructor

    public ContenedorFabricante() {
        this.almacenFabricantes = new ArrayList<>();
    }
    
    //metodos
    
    /**
     * metodo que agrega un fabricante a la lista
     * @param f 
     */
    public void agregarFabricante(Fabricante f){
        almacenFabricantes.add(f);
    }
    
    /**
     * metodo que muestra los datos del almacen 
     */
    public void mostrarDatosFabricante(){
        for (Fabricante f : almacenFabricantes) {
            System.out.println(f);
        }
    }
    //getter

    /**
     * metodo que devuelve la lista de fabricantes
     * @return almacenFabricantes
     */
    public ArrayList<Fabricante> getFabricantes() {
        return almacenFabricantes;
    }
    
}
