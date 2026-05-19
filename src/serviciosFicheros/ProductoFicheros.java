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
import java.io.*;
import java.util.ArrayList;
import modelos.Producto;
import servicios.Conexiones;
import utils.Configuracion;

/**
 * Clase que contiene la gestion exportar e importar a diferentes tipos de ficheros
 * @author Natalia
 */
public class ProductoFicheros {

    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de texto
     */
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

    /**
     * metodo que importa los datos de un fichero txt a la base de datos
     * @param nombreTXT se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan importados lanza la excepcion
     */
    public static void importarFicheroDeTextoPro(String nombreTXT) throws YaImportadoException {

        try {
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(nombreTXT));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                if (Conexiones.verificarExistenciaCodigo(2, codigo)) {

                    codigoRepetido.add(codigo);

                } else {
                    Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);

                    Conexiones.insertarDatos(p);

                    System.out.println("Producto " + nombre + " importado correctamente");
                }

            }
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los productos con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {

            System.err.println("Error: fichero no encontrado");
            System.err.println(ex);

        } catch (IOException ex) {

            System.err.println("Ha ocurrido un error de lectura");
            System.err.println(ex);

        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero json
     */
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

    /**
     * metodo que importa lo datos de un fichero json a la base de datos
     * @param ficheroJson se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados lanza la excepcion 
     */
    public static void importarFicheroJSONPro(String ficheroJson) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();

            //leememos el fichero, lo interpreta, lo convierte a objetod de productos y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Producto> productos = om.readValue(new File(ficheroJson), new TypeReference<ArrayList<Producto>>() {
            });
            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
           
                for (Producto p : productos) {
                    Producto p1 = new Producto(p.getCodigo(), p.getCodigoFabricante(), p.getNombre(), p.getCategoria(), p.getDisponibilidad(), p.getPrecioVenta());
                    if (Conexiones.verificarExistenciaCodigo(2, p.getCodigo())) {

                        codigoRepetido.add(p.getCodigo());

                    } else {
                        Conexiones.insertarDatos(p);
                    }
                }

            
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los productos con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero csv
     */
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

    /**
     * metodo que importa lo datos de un fichero csv a la base de datos
     * @param nombreCSV se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados salta la excepcion
     */
    public static void importarFicheroCSVPro(String nombreCSV) throws YaImportadoException {

        try {
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(nombreCSV));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                if (Conexiones.verificarExistenciaCodigo(2, codigo)) {

                    codigoRepetido.add(codigo);

                } else {
                    Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);

                    Conexiones.insertarDatos(p);

                    System.out.println("Producto " + nombre + " importado correctamente");
                }

            }
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los productos con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {

            System.err.println("Error: fichero no encontrado");
            System.err.println(ex);

        } catch (IOException ex) {

            System.err.println("Ha ocurrido un error de lectura");
            System.err.println(ex);

        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero binario
     */
    public static void exportarFicheroBinarioPro() {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPro, true));
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

    /**
     * metodo que importa los datos de un fichero binario a la base de datos
     * @param nombreBinario se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya esta importado lanza la excepcion
     */
    public static void importarFicheroBinarioPro(String nombreBinario) throws YaImportadoException {

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Producto> productos = (ArrayList<Producto>) ob;
            //cerramos el fichero
            ois.close();
            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            
                for (Producto p : productos) {
                    Producto p1 = new Producto(p.getCodigo(), p.getCodigoFabricante(), p.getNombre(), p.getCategoria(), p.getDisponibilidad(), p.getPrecioVenta());
                    if (Conexiones.verificarExistenciaCodigo(2, p.getCodigo())) {

                        codigoRepetido.add(p.getCodigo());

                    } else {
                        Conexiones.insertarDatos(p);
                    }
                }

            
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los productos con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
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
