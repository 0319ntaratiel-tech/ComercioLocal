/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorCliente;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Fabricante;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class ClienteFicheros {

    public class FabricanteFicheros {

        public static void exportarFicheroDeTextoCli() {

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoCli));

                for (Cliente c : ContenedorCliente.getAlmacenClientes()) {

                    bw.write(c.mostrarDatosConPuntoComa());
                    bw.newLine();
                }

                bw.close();
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }

        }

        public static void importarFicheroDeTextoCli() throws YaImportadoException {
            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException("Los fabricantes ya fueron importados");
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroTextoCli));
                String linea = br.readLine();

                while (linea != null) {
                    String[] partes = linea.split(";");
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String fechaNacimiento = partes[2];
                    String direccionEnvio = partes[3];
                    String telefono = partes[4];
                    String correo = partes[5];
                    
                    Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);


                    ContenedorCliente.agregarCliente(c);
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

        public static void exportarFicheroJSONFabri() {

            ObjectMapper mp = new ObjectMapper();

            ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

            try {
                wr.writeValue(new File(Configuracion.nombreFicheroJSONCli), ContenedorCliente.getAlmacenClientes());
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }

        }

        public static void importarFicheroJSONFabri() throws YaImportadoException {
            //comprobamos que si el contenedor tiene productos dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException("Los fabricantes ya fueron importados");
            }

            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();

            try {
                //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
                //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
                ArrayList<Cliente> clientes = om.readValue(new File(Configuracion.nombreFicheroJSONCli), new TypeReference<ArrayList<Cliente>>() {
                });
                //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
                ContenedorCliente.getAlmacenClientes().addAll(clientes);
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }
        }

        public static void exportarFicheroCSVFabri() {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVCli));

                for (Cliente c : ContenedorCliente.getAlmacenClientes()) {

                    bw.write(c.mostrarDatosConDosPuntos());
                    bw.newLine();
                }

                bw.close();
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error");
                System.err.println(ex);
            }
        }

        public static void importarFicheroCSVFabri() throws YaImportadoException {
            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException("Los fabricantes ya fueron importados");
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(Configuracion.nombreFicheroCSVCli));
                String linea = br.readLine();

                while (linea != null) {
                    String[] partes = linea.split(":");
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String fechaNacimiento = partes[2];
                    String direccionEnvio = partes[3];
                    String telefono = partes[4];
                    String correo = partes[5];
                    
                    Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);

                    ContenedorCliente.agregarCliente(c);

                    
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

        public static void exportarFicheroBinarioFabri( ) {

            try {
                ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioCli, true));
                oss.writeObject(ContenedorCliente.getAlmacenClientes());

                oss.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FabricanteFicheros.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FabricanteFicheros.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public static void importarFicheroBinarioFabri() throws YaImportadoException {

            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException("Los fabricantes ya fueron importados");
            }

            try {
                //java abre le fichero binario y empieza a leer bytes de 0 y 1
                //interpreta esos bytes como objetos  java
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuracion.nombreFicheroBinarioCli));

                //lee todo el contenido del fichero y lo devuelve como un object generico
                Object ob = ois.readObject();
                //aqui convertimos el objeto en al tipo correcto (se llama casting)
                ArrayList<Cliente> clientes = (ArrayList<Cliente>) ob;
                //cerramos el fichero
                ois.close();
                //mete todos los datos al contenedor
                ContenedorCliente.getAlmacenClientes().addAll(clientes);

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
    
}