/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author isard
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<Integer, Integer> codigoRepetido = new HashMap<>();
        codigoRepetido.put(1, 10);
        codigoRepetido.put(2, 20);
        codigoRepetido.put(3, 30);
        for (int i = 0; i < codigoRepetido.size(); i++) {
            System.out.println("");
            System.out.print("Clave: ");
            System.out.print(codigoRepetido.keySet().toArray()[i]);
            System.out.print("Con valor: ");
            System.out.print(codigoRepetido.values().toArray()[i]);
            
        }
        /*
        for (Integer cod : codigoRepetido.keySet()) {
            System.out.println(cod);
        }*/
    }

}
