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
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import servicios.Conexiones;
import utils.Configuracion;

/**
 *
 * @author isard
 */
public class ClienteFicheros {

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

    public static void importarFicheroDeTextoCli(String ficheroTXT) throws YaImportadoException {
        try {

            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException(
                        "Los clientes ya fueron importados");
            }

            try ( BufferedReader br = new BufferedReader(new FileReader(ficheroTXT))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    String[] partes = linea.split(";");

                    // Verificar que la línea tenga todos los datos
                    if (partes.length < 6) {
                        System.err.println("Línea inválida: " + linea);
                        continue;
                    }

                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String fechaNacimiento = partes[2];
                    String direccionEnvio = partes[3];
                    String telefono = partes[4];
                    String correo = partes[5];

                    if (Conexiones.verificarExistenciaCodigo(4, codigo)) {

                        System.err.println("El cliente con código " + codigo + " ya existe en la base de datos");

                    } else {
                        Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);
                        Conexiones.insertarDatos(c);
                        System.out.println("Cliente " + nombre + " importado correctamente");

                    }

                }

                System.out.println("Importación finalizada con éxito");
                br.close();

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroJSONCli() {

        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONCli), ContenedorCliente.getAlmacenClientes());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroJSONCli(String ficheroJSON) throws YaImportadoException {
        //comprobamos que si el contenedor tiene clientes dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
        if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
            throw new YaImportadoException("Los clientes ya fueron importados");
        }

        //creamos el lector de json
        ObjectMapper om = new ObjectMapper();

        try {
            //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Cliente> clientes = om.readValue(new File(ficheroJSON), new TypeReference<ArrayList<Cliente>>() {
            });
            //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
            //ContenedorCliente.getAlmacenClientes().addAll(clientes);

            while (!clientes.isEmpty()) {
                for (Cliente c : clientes) {
                    Cliente c1 = new Cliente(c.getCodigo(), c.getNombre(), c.getFechaNacimiento(), c.getDireccionEnvio(), c.getTelefono(), c.getCorreo());
                    if (Conexiones.verificarExistenciaCodigo(4, c.getCodigo())) {

                        System.err.println("El cliente con código " + c.getCodigo() + " ya existe en la base de datos");

                    } else {
                        Conexiones.insertarDatos(c1);

                    }

                }
            }
            System.out.println("Importación finalizada con éxito");
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroCSVCli() {
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

    public static void importarFicheroCSVCli(String ficheroCSV) throws YaImportadoException {
        try {

            if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
                throw new YaImportadoException(
                        "Los clientes ya fueron importados");
            }

            try ( BufferedReader br = new BufferedReader(new FileReader(ficheroCSV))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    String[] partes = linea.split(":");

                    // Verificar que la línea tenga todos los datos
                    if (partes.length < 6) {
                        System.err.println("Línea inválida: " + linea);
                        continue;
                    }

                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String fechaNacimiento = partes[2];
                    String direccionEnvio = partes[3];
                    String telefono = partes[4];
                    String correo = partes[5];

                    if (Conexiones.verificarExistenciaCodigo(4, codigo)) {

                        System.err.println("El cliente con código " + codigo + " ya existe en la base de datos");

                    } else {
                        Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);
                        Conexiones.insertarDatos(c);
                        System.out.println("Cliente " + nombre + " importado correctamente");

                    }

                }

                System.out.println("Importación finalizada con éxito");
                br.close();

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error. Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroBinarioCli() {

        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioCli, true));
            oss.writeObject(ContenedorCliente.getAlmacenClientes());

            oss.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroBinarioCli(String ficheroBinario) throws YaImportadoException {

        if (!ContenedorCliente.getAlmacenClientes().isEmpty()) {
            throw new YaImportadoException("Los clientes ya fueron importados");
        }

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Cliente> clientes = (ArrayList<Cliente>) ob;
            //cerramos el fichero
            ois.close();
            //mete todos los datos al contenedor
            //ContenedorCliente.getAlmacenClientes().addAll(clientes);
            while (!clientes.isEmpty()) {
                for (Cliente c : clientes) {
                    Cliente c1 = new Cliente(c.getCodigo(), c.getNombre(), c.getFechaNacimiento(), c.getDireccionEnvio(), c.getTelefono(), c.getCorreo());
                    if (Conexiones.verificarExistenciaCodigo(4, c.getCodigo())) {

                        System.err.println("El cliente con código " + c.getCodigo() + " ya existe en la base de datos");

                    } else {
                        Conexiones.insertarDatos(c1);

                    }

                }
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
