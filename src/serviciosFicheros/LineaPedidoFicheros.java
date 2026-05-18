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

    public static void importarFicheroDeTextoLP(String ficheroTXT) throws YaImportadoException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));

            String linea;
            Map<Integer, Integer> codigoRepetido = new HashMap<>();
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 4) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);

                if (Conexiones.verificarExistenciaLineaPedido(codigo, codigoPro)) {
                    codigoRepetido.put(codigo, codigoPro);
                } else {
                    LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);
                    Conexiones.insertarDatos(LP);
                    System.out.println("LineaPedido importada correctamente");
                }

            }
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido;
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

    public static void importarFicheroJSONLP(String ficheroJSON) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<LineaPedido> lineasPedidos = om.readValue(new File(ficheroJSON), new TypeReference<ArrayList<LineaPedido>>() {
            });
            Map<Integer, Integer> codigoRepetido = new HashMap<>();
            while (!lineasPedidos.isEmpty()) {
                for (LineaPedido l : lineasPedidos) {
                    LineaPedido l1 = new LineaPedido(l.getCodigoPedido(), l.getCodigoProducto(), l.getUnidadesCompradas(), l.getSubTotal());
                    if (Conexiones.verificarExistenciaLineaPedido(l.getCodigoPedido(), l.getCodigoProducto())) {
                        codigoRepetido.put(l.getCodigoPedido(), l.getCodigoProducto());

                    } else {
                        Conexiones.insertarDatos(l1);

                    }

                }
            }
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido;
                }

                throw new YaImportadoException("La linea pedido con codigo de pedido y producto" + cadena + "ya fueron importado");

            }

            System.out.println("Importación finalizada con éxito");
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

    public static void importarFicheroCSVLP(String ficheroCSV) throws YaImportadoException {
        try {

            BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

            String linea;
            Map<Integer, Integer> codigoRepetido = new HashMap<>();

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(":");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 4) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                int codigo = Integer.parseInt(partes[0]);
                int codigoPro = Integer.parseInt(partes[1]);
                int unidadesCompradas = Integer.parseInt(partes[2]);
                double subTotal = Double.parseDouble(partes[3]);

                if (Conexiones.verificarExistenciaLineaPedido(codigo, codigoPro)) {
                codigoRepetido.put(codigo, codigoPro);
                 
                } else {
                    LineaPedido LP = new LineaPedido(codigo, codigoPro, unidadesCompradas, subTotal);
                    Conexiones.insertarDatos(LP);
                    System.out.println("LineaPedido importada correctamente");
                }
                
                

            }
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido;
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

    public static void exportarFicheroBinarioLP() {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioLP, true));
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

    public static void importarFicheroBinarioLP(String ficheroBinario) throws YaImportadoException {
        
        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
             Map<Integer, Integer> codigoRepetido = new HashMap<>();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<LineaPedido> lineasPedidos = (ArrayList<LineaPedido>) ob;
            //cerramos el fichero
            ois.close();
            //mete todos los datos al contenedor
            //ContenedorLineaPedido.getAlmacenLineasPedidos().values().addAll(LineasPedidos);
            while (!lineasPedidos.isEmpty()) {
                for (LineaPedido l : lineasPedidos) {
                    LineaPedido l1 = new LineaPedido(l.getCodigoPedido(), l.getCodigoProducto(), l.getUnidadesCompradas(), l.getSubTotal());
                    if (Conexiones.verificarExistenciaLineaPedido(l.getCodigoPedido(), l.getCodigoProducto())) {
                       codigoRepetido.put(l.getCodigoPedido(), l.getCodigoProducto());

                    } else {
                        Conexiones.insertarDatos(l1);

                    }

                }
            }
            if (!codigoRepetido.isEmpty()) {
                int codigoPedidoRepetido = 0;
                int codigoProductoRepetido = 0;
                String cadena = "";

                for (int i = 0; i < codigoRepetido.size(); i++) {

                    codigoPedidoRepetido = (int) codigoRepetido.keySet().toArray()[i];
                    codigoProductoRepetido = (int) codigoRepetido.values().toArray()[i];
                    cadena += codigoPedidoRepetido + "" + codigoProductoRepetido;
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
