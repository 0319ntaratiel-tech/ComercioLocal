/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import utils.Configuracion;

/**
 * Clase que contiene el metodo guardarLinea 
 * @author isard Natalia
 */
public class InformesMultitablaFicheros {

    /**
     * metodo que guarda los datos dados de los informes multitabla en un fichero de texto
     * @param linea 
     */
    public static void guardarLinea(String linea) {
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroMultitabla, true));

            bw.write(linea);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

        
    }
}
