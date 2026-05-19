/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Clase que extiende de Exception y que se utiliza para verificar si un fichero ya fue importado 
 * @author Natalia
 */
public class YaImportadoException extends Exception{

    //constructor
    public YaImportadoException(String message) {
        super(message);
    }
    
    
}
