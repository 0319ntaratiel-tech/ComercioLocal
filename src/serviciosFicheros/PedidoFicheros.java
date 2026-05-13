/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorPedido;
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
import modelos.Pedido;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class PedidoFicheros {

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
    
    public static void importarFicheroDeTextoPed() throws YaImportadoException {
        if (!ContenedorPedido.getAlmacenPedidos().isEmpty()) {
            throw new YaImportadoException("Los pedidos ya fueron importados");
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoPed));
            String linea = br.readLine();
            
            while (linea != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                int codigoVendedor = Integer.parseInt(partes[1]);
                int codigoCliente = Integer.parseInt(partes[2]);
                String fechaRealizacion = partes[3];
                String fechaEntrega = partes[4];
                String estado = partes[5];
                double importe = Double.parseDouble(partes[6]);
                Pedido p = new Pedido(codigo, codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);
                
                ContenedorPedido.agregarPedido(p);
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
    
    public static void importarFicheroJSONPed() throws YaImportadoException {
        //comprobamos que si el contenedor tiene pedidos dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
        if (!ContenedorPedido.getAlmacenPedidos().isEmpty()) {
            throw new YaImportadoException("Los pedidos ya fueron importados");
        }

        //creamos el lector de json
        ObjectMapper om = new ObjectMapper();
        
        try {
            //leememos el fichero, lo interpreta, lo convierte a objetod de pedidos y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Pedido> pedidos = om.readValue(new File(Configuracion.nombreFicheroJSONPed), new TypeReference<ArrayList<Pedido>>() {
            });
            //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
            ContenedorPedido.getAlmacenPedidos().addAll(pedidos);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }        
    }
    
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
    
    public static void importarFicheroCSVPed() throws YaImportadoException {
        if (!ContenedorPedido.getAlmacenPedidos().isEmpty()) {
            throw new YaImportadoException("Los pedidos ya fueron importados");
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroCSVPed));
            String linea = br.readLine();
            
            while (linea != null) {
                String[] partes = linea.split(":");
                int codigo = Integer.parseInt(partes[0]);
                int codigoVendedor = Integer.parseInt(partes[1]);
                int codigoCliente = Integer.parseInt(partes[2]);
                String fechaRealizacion = partes[3];
                String fechaEntrega = partes[4];
                String estado = partes[5];
                double importe = Double.parseDouble(partes[6]);
                Pedido p = new Pedido(codigo, codigoVendedor, codigoCliente, fechaRealizacion, fechaEntrega, estado, importe);
                //preguntar a nat
                ContenedorPedido.agregarPedido(p);
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
    
    public static void exportarFicheroBinarioPed() {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioPed,true));
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
    
    public static void importarFicheroBinarioPed() throws YaImportadoException {
        if (!ContenedorPedido.getAlmacenPedidos().isEmpty()) {
            throw new YaImportadoException("Los pedidos ya fueron importados");
        }
        
        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracion.nombreFicheroBinarioPed));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Pedido> pedidos = (ArrayList<Pedido>) ob;
            //cerramos el fichero
            ois.close();
            //mete todos los datos al contenedor
            ContenedorPedido.getAlmacenPedidos().addAll(pedidos);
            
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
