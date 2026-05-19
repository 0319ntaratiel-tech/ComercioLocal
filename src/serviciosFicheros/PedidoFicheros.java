/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorPedido;
import java.io.*;
import java.util.ArrayList;
import modelos.Pedido;
import servicios.Conexiones;
import utils.Configuracion;

/**
 * Clase que contiene la gestion exportar e importar a diferentes tipos de ficheros
 * @author Gabriela
 */
public class PedidoFicheros {
    
    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de texto
     */
    public static void exportarFicheroDeTextoPed() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoFabri));
            //recorre la lista de pedidos del contenedor
            for (Pedido p : ContenedorPedido.getAlmacenPedidos()) {
                //almacena cada pedido en el fichero separando los datos con punto y coma
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
     * @param ficheroTXT se le pasa por parametro el fichero a importar
     */
    public static void importarFicheroDeTextoPed(String ficheroTXT) {

        try {
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));

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
                int codigoVendedor = Integer.parseInt(partes[0]);
                int codigoCliente = Integer.parseInt(partes[1]);
                String fechaRealizacion = partes[2];
                String fechaEntrega = partes[3];
                String estado = partes[4];
                double importe = Double.parseDouble(partes[5]);

                //creamos el objeto de tipo pedido con los datos extraidos del fichero
                Pedido p = new Pedido(codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);
                //y lo insertamos a la base de datos
                Conexiones.insertarDatos(p);

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
    public static void exportarFicheroJSONPed() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        //le pasamos el fichero al queremos pasarle los datos, y el contenedor pedidos donde estan los datos almacenados
        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONPed), ContenedorPedido.getAlmacenPedidos());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    /**
     * metodo que importa lo datos de un fichero json a la base de datos
     * @param ficheroJson se le pasa por parametro el fichero a importar
     */
    public static void importarFicheroJSONPed(String ficheroJson) {
        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objeto de Pedido y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Pedido 
            ArrayList<Pedido> pedidos = om.readValue(new File(ficheroJson), new TypeReference<ArrayList<Pedido>>() {
            });

            //recorremos el array y extraemos los datos del pedido para crear los objetos
            for (Pedido p : pedidos) {
                Pedido p1 = new Pedido(p.getCodigoVendedor(), p.getCodigoCliente(), p.getFechaRealizacion(), p.getFechaEntrega(), p.getEstado(), p.getImporte());
                //insertamos los objetos a la base de datos
                Conexiones.insertarDatos(p1);

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
    public static void exportarFicheroCSVPed() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVPed));

            //recorre la lista de pedidos del contenedor
            for (Pedido p : ContenedorPedido.getAlmacenPedidos()) {
                //almacena cada pedido en el fichero separando los datos con dos puntos
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
     * @param ficheroCSV se le pasa por parametro el fichero a importar
     */
    public static void importarFicheroCSVPed(String ficheroCSV) {

        try {
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

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
                int codigoVendedor = Integer.parseInt(partes[0]);
                int codigoCliente = Integer.parseInt(partes[1]);
                String fechaRealizacion = partes[2];
                String fechaEntrega = partes[3];
                String estado = partes[4];
                double importe = Double.parseDouble(partes[5]);

                //creamos el objeto pedido con los datos extraidos del fichero
                Pedido p = new Pedido(codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);

                //y lo insertamos en la base de datos
                Conexiones.insertarDatos(p);

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
    public static void exportarFicheroBinarioPed() {
        try {
            //le pasamos el fichero que queremos que escriba
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPed, true));
            //escribe los datos de los pedidos almacenados en el contenedor
            oss.writeObject(ContenedorPedido.getAlmacenPedidos());

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
     * @param ficheroBinario se le pasa por parametro el fichero a importar
     */
    public static void importarFicheroBinarioPed(String ficheroBinario) {

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Pedido> pedidos = (ArrayList<Pedido>) ob;
            //cerramos el fichero
            ois.close();

            //recorremos la lista y se estrae los datos de los pedidos
            for (Pedido p : pedidos) {
                //creamos los objetos con esos datos
                Pedido p1 = new Pedido(p.getCodigoVendedor(), p.getCodigoCliente(), p.getFechaRealizacion(), p.getFechaEntrega(), p.getEstado(), p.getImporte());
                // y los insertamos a la base de datos
                Conexiones.insertarDatos(p1);

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
