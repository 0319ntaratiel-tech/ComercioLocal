/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 * Clase que contiene metodos abstractos que seran implementados en las clases
 * @author Natalia
 */
public interface Lectora {
    //metodos
    /**
     * metodo que muestra la información separada por punto y coma (;)
     * @return los datos 
     */
    public abstract String mostrarDatosConPuntoComa();
    
    /**
     * metodo que muestra la información separada por dos puntos (:)
     * @return los datos
     */
    public abstract String mostrarDatosConDosPuntos();
}
