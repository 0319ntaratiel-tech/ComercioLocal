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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.LineaPedido;
import servicios.Conexiones;
import utils.Configuracion;

/**
 * Clase que contiene la gestion exportar e importar a diferentes tipos de
 * ficheros
 *
 * @author Gabriela
 */
public class LineaPedidoFicheros {

    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de
     * texto
     */
    public static void exportarFicheroDeTextoLP() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoLP));

            //recorre la lista de lineasPedido del contenedor
            for (LineaPedido lp : ContenedorLineaPedido.getAlmacenLineasPedidos().values()) {
                //almacena cada lineaPedido en el fichero separando los datos con punto y coma
                bw.write(lp.mostrarDatosConPuntoComa());
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
     *
     * @param ficheroTXT se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan importados lanza la excepcion
     */
    public static void importarFicheroDeTextoLP(String ficheroTXT) throws YaImportadoException {
        try {
            //creamos una lista tipo map , para almacenar las lineasPedido que ya fueron insertadas
            Map<Integer, Integer> codigoRepetido = new HashMap<>();
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));
            String linea;

            while ((linea = br.readLine()) != null) {
                //almacenamos en un vector las lineas sin el ;
                String[] partes = linea.split(";");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 4) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                //extraemos los datos por posicion
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);

                //verificamos que los codigos no este en la base de datos
                if (Conexiones.verificarExistenciaLineaPedido(codigo, codigoPro)) {
                    //si estan los almacena al array creado anteriormente
                    codigoRepetido.put(codigo, codigoPro);
                } else {
                    //creamos el objeto de lineaPedido con los datos extraidos del fichero
                    LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);
                    //y lo insertamos a la base de datos
                    Conexiones.insertarDatos(LP);
                    System.out.println("LineaPedido importada correctamente");
                }

            }

            //si la lineaPedido ya existe en la base de datos, salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {
                    //recorremos la lista para mostrar los codigos
                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido + ", ";
                }

                throw new YaImportadoException("La linea pedido con codigo de pedido y producto" + cadena + "ya fueron importados");

            }

            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero json
     */
    public static void exportarFicheroJSONLP() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        //le pasamos el fichero al que queremos pasarle los datos, y el contenedor donde estan los datos almacenados
        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONLP), ContenedorLineaPedido.getAlmacenLineasPedidos());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    /**
     * metodo que importa lo datos de un fichero json a la base de datos
     *
     * @param ficheroJSON se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados lanza la
     * excepcion
     */
    public static void importarFicheroJSONLP(String ficheroJSON) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<LineaPedido> lineasPedidos = om.readValue(new File(ficheroJSON), new TypeReference<ArrayList<LineaPedido>>() {
            });

            //creamos una lista HashMap para almacenar los codigos repetidos
            Map<Integer, Integer> codigoRepetido = new HashMap<>();

            //recorremos el array y extraemos los datos de la linea pedido para crear un objeto
            for (LineaPedido l : lineasPedidos) {
                LineaPedido l1 = new LineaPedido(l.getCodigoPedido(), l.getCodigoProducto(), l.getUnidadesCompradas(), l.getSubTotal());
                //comprobamos que la lineaPedido no exista ya en la base de datos
                if (Conexiones.verificarExistenciaLineaPedido(l.getCodigoPedido(), l.getCodigoProducto())) {
                    codigoRepetido.put(l.getCodigoPedido(), l.getCodigoProducto());

                } else {
                    //si las lineas son nuevas se insertan en la base de datos
                    Conexiones.insertarDatos(l1);

                }

            }

            //si las lineas ya existen, salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido + ", ";
                }

                throw new YaImportadoException("La linea pedido con codigo de pedido y producto" + cadena + "ya fueron importado");

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
    public static void exportarFicheroCSVLP() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVLP));

            //recorre la lista de lineasPedido del contenedor
            for (LineaPedido lp : ContenedorLineaPedido.getAlmacenLineasPedidos().values()) {
                //almacena cada linea en el fichero separando los datos con dos puntos
                bw.write(lp.mostrarDatosConDosPuntos());
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
     *
     * @param ficheroCSV se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados salta la
     * excepcion
     */
    public static void importarFicheroCSVLP(String ficheroCSV) throws YaImportadoException {
        try {
            //creamos una lista HashMap para almacenar los codigos que ya fueron insertados
            Map<Integer, Integer> codigoRepetido = new HashMap<>();
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

            String linea;

            while ((linea = br.readLine()) != null) {
                //almacenamos en un vector las lineas sin el :
                String[] partes = linea.split(":");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 4) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                //extraemos los datos por posicion
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);

                //verificamos que los codigos no esten en la base de datos
                if (Conexiones.verificarExistenciaLineaPedido(codigo, codigoPro)) {
                    //si estan los almacena al array creado anteriormente
                    codigoRepetido.put(codigo, codigoPro);

                } else {
                    //creamos el objeto de lineaPedido con los datos extraidos del fichero
                    LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);
                    //y lo insertamos en la base de datos
                    Conexiones.insertarDatos(LP);
                    System.out.println("LineaPedido importada correctamente");
                }

            }
            
            //si la linea ya existe salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido + ", ";
                }

                throw new YaImportadoException("La linea pedido con codigo de pedido y producto" + cadena + "ya fueron importado");

            }

            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero binario
     */
    public static void exportarFicheroBinarioLP() {
        try {
            //le pasamos el fichero que queremos que escriba
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioLP, true));
            //escribe los datos de las lineas almacenads en el contenedor
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

    /**
     * metodo que importa los datos de un fichero binario a la base de datos
     *
     * @param ficheroBinario se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya esta importado lanza la excepcion
     */
    public static void importarFicheroBinarioLP(String ficheroBinario) throws YaImportadoException {

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<LineaPedido> lineasPedidos = (ArrayList<LineaPedido>) ob;
            //cerramos el fichero
            ois.close();
            //creamos una lista HashMap para almacenar los codigos repetidos
            Map<Integer, Integer> codigoRepetido = new HashMap<>();
            //recorremos la la lista y se extrae los datos de las lineas
            for (LineaPedido l : lineasPedidos) {
                //creamos los objetos con esos datos
                LineaPedido l1 = new LineaPedido(l.getCodigoPedido(), l.getCodigoProducto(), l.getUnidadesCompradas(), l.getSubTotal());
                //verificamos que las lineas no esten ya en la base de datos
                if (Conexiones.verificarExistenciaLineaPedido(l.getCodigoPedido(), l.getCodigoProducto())) {
                    codigoRepetido.put(l.getCodigoPedido(), l.getCodigoProducto());

                } else {
                    //si son nuevas las insertamos en la base de datos
                    Conexiones.insertarDatos(l1);

                }

            }
            //si hay lineas repetidas salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido + ", ";
                }

                throw new YaImportadoException("La linea pedido con codigo de pedido y producto" + cadena + "ya fueron importado");

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
