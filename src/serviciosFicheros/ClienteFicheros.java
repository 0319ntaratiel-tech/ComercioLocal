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
 * Clase que contiene la gestion exportar e importar a diferentes tipos de ficheros
 * @author  Natalia
 */
public class ClienteFicheros {

    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de texto
     */
    public static void exportarFicheroDeTextoCli() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoCli));

             //recorre la lista de clientes del contenedor
            for (Cliente c : ContenedorCliente.getAlmacenClientes()) {
                //almacena cada cliente en el fichero separando los datos con punto y coma
                bw.write(c.mostrarDatosConPuntoComa());
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
     * @throws YaImportadoException si ya estan importados lanza la excepcion
     */
    public static void importarFicheroDeTextoCli(String ficheroTXT) throws YaImportadoException {

        try {
            //creamos un arraylist de tipo integer para almacenar los codigos que ya fueron insertados
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            //leemos el fichero pasado por parametro linea a linea
            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));
            String linea;

            while ((linea = br.readLine()) != null) {
                //almacenamos en vector las lineas sin el ;
                String[] partes = linea.split(";");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }
                //extraemos los datos por posicion
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaNacimiento = partes[2];
                String direccionEnvio = partes[3];
                String telefono = partes[4];
                String correo = partes[5];

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(4, codigo)) {
                    //si esta lo almacena en el array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos los objetos cliente con los datos extraidos del fichero
                    Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);
                    // y lo insertamos a la base de datos
                    Conexiones.insertarDatos(c);
                    System.out.println("Cliente " + nombre + " importado correctamente");

                }

            }
            //si el cliente ya existe en la base de datos, salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los clientes con codigo " + cadena + "ya fueron importado");
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
    public static void exportarFicheroJSONCli() {

        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        //le pasamos el fichero al que queremos pasarle los datos , y el contenedor cliente donde estan almacenados los datos
        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONCli), ContenedorCliente.getAlmacenClientes());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    /**
     * metodo que importa lo datos de un fichero json a la base de datos
     * @param ficheroJSON se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados lanza la excepcion 
     */
    public static void importarFicheroJSONCli(String ficheroJSON) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Cliente> clientes = om.readValue(new File(ficheroJSON), new TypeReference<ArrayList<Cliente>>() {
            });

            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();

            //recorremos el array y extraemos los datos del cliente para crear un objeto
            for (Cliente c : clientes) {
                Cliente c1 = new Cliente(c.getCodigo(), c.getNombre(), c.getFechaNacimiento(), c.getDireccionEnvio(), c.getTelefono(), c.getCorreo());
                //comprobamos que el cliente no exista en la base de datos
                if (Conexiones.verificarExistenciaCodigo(4, c.getCodigo())) {

                    codigoRepetido.add(c.getCodigo());

                } else {
                    //si los clientes son nuevos se insertan en la base de datos
                    Conexiones.insertarDatos(c1);

                }

            }
            //si los fabricantes ya existen, salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los clientes con codigo " + cadena + "ya fueron importado");
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
    public static void exportarFicheroCSVCli() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVCli));

            //recorre la lista de clientes del contenedor
            for (Cliente c : ContenedorCliente.getAlmacenClientes()) {
                //almacena cada cliente en el fichero separando los datos con dos puntos
                bw.write(c.mostrarDatosConDosPuntos());
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
     * @throws YaImportadoException si ya estan los datos importados salta la excepcion
     */
    public static void importarFicheroCSVCli(String ficheroCSV) throws YaImportadoException {

        try {
            //creamos un arrayList de tipo integer para almacenar los codigos que ya fueron insertados
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
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
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaNacimiento = partes[2];
                String direccionEnvio = partes[3];
                String telefono = partes[4];
                String correo = partes[5];

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(4, codigo)) {
                    //si esta lo almacena el array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos los objetos cliente con los datos extraidos del fichero
                    Cliente c = new Cliente(codigo, nombre, fechaNacimiento, direccionEnvio, telefono, correo);
                    //y lo insertamos en la base de datos
                    Conexiones.insertarDatos(c);
                    System.out.println("Cliente " + nombre + " importado correctamente");

                }

            }

            //si el cliente ya existe salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los clientes con codigo " + cadena + "ya fueron importado");
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
    public static void exportarFicheroBinarioCli() {

        try {
            //le pasamos el fichero que queremos que escriba
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioCli, true));
            //escribe los datos de los clientes almacenados en el contenedor
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

    /**
     * metodo que importa los datos de un fichero binario a la base de datos
     * @param ficheroBinario se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya esta importado lanza la excepcion
     */
    public static void importarFicheroBinarioCli(String ficheroBinario) throws YaImportadoException {

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
            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();

            //recorremos la lista y se extrae los datos de los clientes
            for (Cliente c : clientes) {
                //creamos los objetos con esos datos
                Cliente c1 = new Cliente(c.getCodigo(), c.getNombre(), c.getFechaNacimiento(), c.getDireccionEnvio(), c.getTelefono(), c.getCorreo());
                //verificamos que los clientes no esten ya en la base de datos
                if (Conexiones.verificarExistenciaCodigo(4, c.getCodigo())) {
                    //almacenamos los clientes que esten repetidos
                    codigoRepetido.add(c.getCodigo());

                } else {
                    //si son nuevos los insertamos en la base de datos
                    Conexiones.insertarDatos(c1);

                }

            }

            //si hay clientes repetidos salta las excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los clientes con codigo " + cadena + "ya fueron importado");
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
