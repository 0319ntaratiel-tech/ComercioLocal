/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
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
