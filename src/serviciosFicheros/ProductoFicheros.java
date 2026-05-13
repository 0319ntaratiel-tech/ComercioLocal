/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorProducto;
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
import modelos.Producto;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class ProductoFicheros {

    public static void exportarFicheroDeTextoPro() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoPro));

            for (Producto p : ContenedorProducto.getAlmacenProductos()) {

                bw.write(p.mostrarDatosConPuntoComa());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void importarFicheroDeTextoPro() throws YaImportadoException {
        if (!ContenedorProducto.getAlmacenProductos().isEmpty()) {
            throw new YaImportadoException("Los productos ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoPro));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);

                ContenedorProducto.agregarProducto(p);
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

    public static void exportarFicheroJSONPro() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONPro), ContenedorProducto.getAlmacenProductos());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroJSONPro() throws YaImportadoException {
    //comprobamos que si el contenedor tiene productos dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
        if (!ContenedorProducto.getAlmacenProductos().isEmpty()) {
            throw new YaImportadoException("Los productos ya fueron importados");
        }

        //creamos el lector de json
        ObjectMapper om = new ObjectMapper();

        try {
            //leememos el fichero, lo interpreta, lo convierte a objetod de productos y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Producto> productos = om.readValue(new File(Configuracion.nombreFicheroJSONPro), new TypeReference<ArrayList<Producto>>() {
            });
            //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
            ContenedorProducto.getAlmacenProductos().addAll(productos);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroCSVPro() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVPro));

            for (Producto p : ContenedorProducto.getAlmacenProductos()) {

                bw.write(p.mostrarDatosConDosPuntos());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroCSVPro() throws YaImportadoException {
    if (!ContenedorProducto.getAlmacenProductos().isEmpty()) {
            throw new YaImportadoException("Los productos ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroCSVPro));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(":");
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);

                ContenedorProducto.agregarProducto(p);
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

    public static void exportarFicheroBinarioPro() {
    try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPro,true));
            oss.writeObject(ContenedorProducto.getAlmacenProductos());

            oss.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void importarFicheroBinarioPro() throws YaImportadoException {
    if (!ContenedorProducto.getAlmacenProductos().isEmpty()) {
            throw new YaImportadoException("Los productos ya fueron importados");
        }

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracion.nombreFicheroBinarioPro));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Producto> productos = (ArrayList<Producto>) ob;
            //cerramos el fichero
            ois.close();
            //mete todos los datos al contenedor
            ContenedorProducto.getAlmacenProductos().addAll(productos);

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
