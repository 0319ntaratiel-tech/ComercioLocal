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

            for (Pedido p : ContenedorPedido.getAlmacenPedidos()) {

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
            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                int codigoVendedor = Integer.parseInt(partes[0]);
                int codigoCliente = Integer.parseInt(partes[1]);
                String fechaRealizacion = partes[2];
                String fechaEntrega = partes[3];
                String estado = partes[4];
                double importe = Double.parseDouble(partes[5]);

                Pedido p = new Pedido(codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);

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
            ArrayList<Pedido> pedidos = om.readValue(new File(ficheroJson), new TypeReference<ArrayList<Pedido>>() {
            });

            for (Pedido p : pedidos) {
                Pedido p1 = new Pedido(p.getCodigoVendedor(), p.getCodigoCliente(), p.getFechaRealizacion(), p.getFechaEntrega(), p.getEstado(), p.getImporte());
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

            for (Pedido p : ContenedorPedido.getAlmacenPedidos()) {

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
            BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(":");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                int codigoVendedor = Integer.parseInt(partes[0]);
                int codigoCliente = Integer.parseInt(partes[1]);
                String fechaRealizacion = partes[2];
                String fechaEntrega = partes[3];
                String estado = partes[4];
                double importe = Double.parseDouble(partes[5]);

                Pedido p = new Pedido(codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);

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
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPed, true));
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

            for (Pedido p : pedidos) {
                Pedido p1 = new Pedido(p.getCodigoVendedor(), p.getCodigoCliente(), p.getFechaRealizacion(), p.getFechaEntrega(), p.getEstado(), p.getImporte());

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
