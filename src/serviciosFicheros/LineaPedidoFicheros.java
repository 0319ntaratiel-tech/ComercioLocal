/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorLineaPedido;
import excepciones.YaImportadoException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.LineaPedido;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class LineaPedidoFicheros {

    public static void exportarFicheroDeTextoLP() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoLP));

            for (LineaPedido lp : ContenedorLineaPedido.getAlmacenLineasPedidos().values()) {

                bw.write(lp.mostrarDatosConPuntoComa());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroDeTextoLP() throws YaImportadoException {
        if (!ContenedorLineaPedido.getAlmacenLineasPedidos().isEmpty()) {
            throw new YaImportadoException("Las lineas de pedidos ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoLP));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);
                
                LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);

                ContenedorLineaPedido.agregarLineaPedido(LP);
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

    public static void exportarFicheroJSONLP() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONLP), ContenedorLineaPedido.getAlmacenLineasPedidos());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void importarFicheroJSONLP()throws YaImportadoException {
        //comprobamos que si el contenedor tiene productos dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
            if (!ContenedorLineaPedido.getAlmacenLineasPedidos().isEmpty()) {
                throw new YaImportadoException("Las lineas de pedidos ya fueron importados");
            }

            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();

            try {
                //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
                //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
                ArrayList<LineaPedido> LineasPedidos = om.readValue(new File(Configuracion.nombreFicheroJSONLP), new TypeReference<ArrayList<LineaPedido>>() {
                });
                //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
                ContenedorLineaPedido.getAlmacenLineasPedidos().values().addAll(LineasPedidos);
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }
    }

    public static void exportarFicheroCSVLP() {
        try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVLP));

                for (LineaPedido lp : ContenedorLineaPedido.getAlmacenLineasPedidos().values()) {

                    bw.write(lp.mostrarDatosConDosPuntos());
                    bw.newLine();
                }

                bw.close();
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }
    }

    public static void importarFicheroCSVLP() throws YaImportadoException{
        if (!ContenedorLineaPedido.getAlmacenLineasPedidos().isEmpty()) {
            throw new YaImportadoException("Los fabricantes ya fueron importados");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoLP));
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(":");
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);
                //double subTotal = Double.parseDouble(partes[3]);
                LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);

                ContenedorLineaPedido.agregarLineaPedido(LP);
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

    public static void exportarFicheroBinarioLP() {
        try {
                ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioLP,true));
                oss.writeObject(ContenedorLineaPedido.getAlmacenLineasPedidos().values());

                oss.close();
            } catch (FileNotFoundException ex) {
                System.err.println("Fichero no encontrado");
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }
    }

    public static void importarFicheroBinarioLP() throws YaImportadoException {
        if (!ContenedorLineaPedido.getAlmacenLineasPedidos().isEmpty()) {
                throw new YaImportadoException("Los fabricantes ya fueron importados");
            }

            try {
                //java abre le fichero binario y empieza a leer bytes de 0 y 1
                //interpreta esos bytes como objetos  java
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracion.nombreFicheroBinarioLP));

                //lee todo el contenido del fichero y lo devuelve como un object generico
                Object ob = ois.readObject();
                //aqui convertimos el objeto en al tipo correcto (se llama casting)
                ArrayList<LineaPedido> LineasPedidos = (ArrayList<LineaPedido>) ob;
                //cerramos el fichero
                ois.close();
                //mete todos los datos al contenedor
                ContenedorLineaPedido.getAlmacenLineasPedidos().values().addAll(LineasPedidos);

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
