/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorVendedor;
import excepciones.YaImportadoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelos.Vendedor;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class VendedorFicheros {

    public static void exportarFicheroDeTextoVen() {
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

    public static void importarFicheroDeTextoVen() throws YaImportadoException {
        if (!ContenedorVendedor.getAlmacenVendedor().isEmpty()) {
            throw new YaImportadoException("Los vendedores ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoVen));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaAlta = partes[2];
                String domicilio = partes[3];
                double salario = Double.parseDouble(partes[4]);
                double porcentaje = Double.parseDouble(partes[5]);

                Vendedor v = new Vendedor(codigo, nombre, fechaAlta, domicilio, salario, porcentaje);

                ContenedorVendedor.agregarVendedor(v);
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

    public static void exportarFicheroJSONVen() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONVen), ContenedorVendedor.getAlmacenVendedor());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroJSONVeni() throws YaImportadoException {
//comprobamos que si el contenedor tiene vendedores dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
        if (!ContenedorVendedor.getAlmacenVendedor().isEmpty()) {
            throw new YaImportadoException("Los vendedores ya fueron importados");
        }

        //creamos el lector de json
        ObjectMapper om = new ObjectMapper();

        try {
            //leememos el fichero, lo interpreta, lo convierte a objeto de vendedores y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Vendedor> vendedores = om.readValue(new File(Configuracion.nombreFicheroJSONVen), new TypeReference<ArrayList<Vendedor>>() {
            });
            //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
            ContenedorVendedor.getAlmacenVendedor().addAll(vendedores);

        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroCSVVen() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVVen));

            for (Vendedor v : ContenedorVendedor.getAlmacenVendedor()) {

                bw.write(v.mostrarDatosConDosPuntos());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void importarFicheroCSVVen() throws YaImportadoException {
        if (!ContenedorVendedor.getAlmacenVendedor().isEmpty()) {
            throw new YaImportadoException("Los vendedores ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroCSVVen));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(":");
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaAlta = partes[2];
                String domicilio = partes[3];
                double salario = Double.parseDouble(partes[4]);
                double porcentaje = Double.parseDouble(partes[5]);

                Vendedor v = new Vendedor(codigo, nombre, fechaAlta, domicilio, salario, porcentaje);

                ContenedorVendedor.agregarVendedor(v);
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

    public static void exportarFicheroBinarioVen() {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioVen,true));
            oss.writeObject(ContenedorVendedor.getAlmacenVendedor());

            oss.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroBinarioVen()     throws YaImportadoException
 {
        if (!ContenedorVendedor.getAlmacenVendedor().isEmpty()) {
            throw new YaImportadoException("Los vendedores ya fueron importados");
        }

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracion.nombreFicheroBinarioVen));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) ob;
            //cerramos el fichero
            ois.close();
            //mete todos los datos al contenedor
            ContenedorVendedor.getAlmacenVendedor().addAll(vendedores);

        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error. Clase no encontrada");
            System.err.println(ex);
        }
    }
}
