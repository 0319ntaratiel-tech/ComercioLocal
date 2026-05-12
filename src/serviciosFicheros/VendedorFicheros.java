/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import contenedores.ContenedorVendedor;
import excepciones.YaImportadoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modelos.Vendedor;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class VendedorFicheros {
    
    public static void exportarFicheroDeTextoVen(){
     try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoVen));

            for (Vendedor v : ContenedorVendedor.getAlmacenVendedor()) {

                bw.write(v.mostrarDatosConPuntoComa());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }    
    }
    
    
    public static void importarFicheroDeTextoVen() throws YaImportadoException{
    if (!ContenedorVendedor.getAlmacenVendedor().isEmpty()) {
            throw new YaImportadoException("Los fabricantes ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoVen));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaAlta=partes[2];
                String domicilio=partes[3];
                double salario=Double.parseDouble(partes[4]);
                double porcentaje=Double.parseDouble(partes[5]);

               // Vendedor v = new Vendedor(codigo, nombre, linea, nombre, empleado, empleado)
                //Fabricante f = new Fabricante(codigo, nombre, anyoFundacion, lugarSede, empleado, sitioWeb);

               // contFabri.agregarFabricante(f);
            }

            br.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        } 
    }
    
    
    public static void exportarFicheroJSONVen(){
        
    }
    
    
    public static void importarFicheroJSONVeni(){
        
    }
    
    public static void exportarFicheroCSVVen(){
        
    }
    
    
    public static void importarFicheroCSVVen(){
        
    }
    
    
    public static void exportarFicheroBinarioVen(){
        
    }
    
    
    public static void importarFicheroBinarioVen(){
        
    }
}
