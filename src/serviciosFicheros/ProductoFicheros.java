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
            //recorre la lista de productos del contenedor
            for (Producto p : ContenedorProducto.getAlmacenProductos()) {
                //almacena cada producto en el fichero separando los datos con punto y coma
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
            //creamos un arrayList de tipo integer para almacenar los productos  que ya fueron insertados
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(nombreTXT));
            String linea;

            while ((linea = br.readLine()) != null) {
                //almacenamos en un vector las lineas sin el ;
                String[] partes = linea.split(";");
                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                //extraemos los datos por posicion
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(2, codigo)) {
                    //si esta lo almacena al array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos el objeto de producto con los datos extraidos del fichero
                    Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);
                    //y lo insertamos a la base de datos
                    Conexiones.insertarDatos(p);

                    System.out.println("Producto " + nombre + " importado correctamente");
                }

            }
            //si el producto ya existe en la base de datos, salta la excepcion YaImportadoException 
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
        //le pasamos el fichero al que queremos pasarle los datos, y el contenedor producto donde estan los datos almacenados
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
                //recorremos el array y extraemos los datos del producto para crear un objeto
                for (Producto p : productos) {
                    Producto p1 = new Producto(p.getCodigo(), p.getCodigoFabricante(), p.getNombre(), p.getCategoria(), p.getDisponibilidad(), p.getPrecioVenta());
                    //comprobamos que el producto no exista ya en la base de datos
                    if (Conexiones.verificarExistenciaCodigo(2, p.getCodigo())) {

                        codigoRepetido.add(p.getCodigo());

                    } else {
                        //si los productos son nuevos se insertan en la base de datos
                        Conexiones.insertarDatos(p);
                    }
                }

            //si los productos ya existen , salta la excepcion YaImportadoException
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

            //recorre la lista de productos del contenedor
            for (Producto p : ContenedorProducto.getAlmacenProductos()) {
                //almacena cada producto en el fichero separando los datos con dos puntos
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
            //creamos un arrayList de tipo integer para almacenar los codigos que ya fueron insertados
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(nombreCSV));
            String linea;

            while ((linea = br.readLine()) != null) {
                //almacenamos en un vector las lineas sin el :
                String[] partes = linea.split(":");
                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                //extraemos los datos por posicion
                int codigo = Integer.parseInt(partes[0]);
                int codigoFabricante = Integer.parseInt(partes[1]);
                String nombre = partes[2];
                String categoria = partes[3];
                String disponibilidad = partes[4];
                double precioVenta = Double.parseDouble(partes[5]);

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(2, codigo)) {
                    //si esta lo almacena al array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos el objeto producto con los datos extraidos del fichero
                    Producto p = new Producto(codigo, codigoFabricante, nombre, categoria, disponibilidad, precioVenta);
                    //y lo insertamos en la base de datos
                    Conexiones.insertarDatos(p);

                    System.out.println("Producto " + nombre + " importado correctamente");
                }

            }
            //si el producto ya existe salta la excepcion YaImportadoException
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
            //le pasamos el fichero que queremos que escriba
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPro, true));
            
            //escribe los datos de los productos almacenados en el contenedor
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
                //recorremos la lista y se extrae los datos de los productos 
                for (Producto p : productos) {
                    //creamos los objetos con esos datos
                    Producto p1 = new Producto(p.getCodigo(), p.getCodigoFabricante(), p.getNombre(), p.getCategoria(), p.getDisponibilidad(), p.getPrecioVenta());
                    //verificamos que los productos no esten ya en la base de datos
                    if (Conexiones.verificarExistenciaCodigo(2, p.getCodigo())) {
                        //almacenamos los  productos que esten repetidos 
                        codigoRepetido.add(p.getCodigo());

                    } else {
                        //si son nuevos los insertamos en la base de datos
                        Conexiones.insertarDatos(p);
                    }
                }

            //si hay productos repetidos salta la excepcion YaImportadoException
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
