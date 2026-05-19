/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorFabricante;
import excepciones.YaImportadoException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Fabricante;
import servicios.Conexiones;
import utils.Configuracion;

/**
 * Clase que contiene la gestion exportar e importar a diferentes tipos de ficheros
 * @author Natalia
 */
public class FabricanteFicheros {

    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de texto
     */
    public static void exportarFicheroDeTextoFabri() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoFabri));

            //recorre la lista de fabricantes del contenedor
            for (Fabricante f : ContenedorFabricante.getFabricantes()) {
                //almacena cada fabricante en el fichero separando los datos con punto y coma
                bw.write(f.mostrarDatosConPuntoComa());
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
    public static void importarFicheroDeTextoFabri(String ficheroTXT) throws YaImportadoException {

        try {
            //creamos un arrayList de tipo integer para almacenar los fabricantes que ya fueron insertados
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
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
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int anyoFundacion = Integer.parseInt(partes[2]);
                String lugarSede = partes[3];
                int empleados = Integer.parseInt(partes[4]);
                String sitioWeb = partes[5];

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(1, codigo)) {
                    //si esta lo almacena al array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos el objeto de fabricante con los datos extraidos del fichero
                    Fabricante f = new Fabricante(codigo, nombre, anyoFundacion, lugarSede, empleados, sitioWeb);
                    //y lo insertamos a la base de datos
                    Conexiones.insertarDatos(f);

                    System.out.println("Fabricante " + nombre + " importado correctamente");
                }
            }

            //si el fabricante ya existe en la base de datos, salta la excepcion YaImportadoException 
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los fabricantes con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {

            System.err.println("Error: fichero no encontrado");
            System.err.println(ex);

        } catch (IOException ex) {

            System.err.println("Ha ocurrido un error");
            System.err.println(ex);

        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero json
     */
    public static void exportarFicheroJSONFabri() {

        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        //le pasamos el fichero al que queremos pasarle los datos, y el contenedor fabricante donde estan los datos almacenados
        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONFabri), ContenedorFabricante.getFabricantes());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    /**
     * metodo que importa lo datos de un fichero json a la base de datos
     * @param nombreFicheroJson se le pasa por parametro el fichero a importar
     * @throws YaImportadoException si ya estan los datos importados lanza la excepcion 
     */
    public static void importarFicheroJSONFabri(String nombreFicheroJson) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objeto de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Fabricante> fabricantes = om.readValue(new File(nombreFicheroJson), new TypeReference<ArrayList<Fabricante>>() {
            });

            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();

            //recorremos el array y extraemos los datos del fabricante para crear un objeto
            for (Fabricante f : fabricantes) {
                Fabricante f1 = new Fabricante(f.getCodigo(), f.getNombre(), f.getAnyoFundacion(), f.getLugarSede(), f.getEmpleados(), f.getSitioWeb());
                //comprobamos que el fabricante no exista ya en la base de datos
                if (Conexiones.verificarExistenciaCodigo(1, f.getCodigo())) {

                    codigoRepetido.add(f.getCodigo());

                } else {
                    //si los fabricantes son nuevos se insertan en la base de datos
                    Conexiones.insertarDatos(f1);
                    System.out.println("Fabricante " + f.getNombre() + " importado correctamente");

                }

            }

            //si los fabricantes ya existen , salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los fabricantes con codigo " + cadena + "ya fueron importados");
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
    public static void exportarFicheroCSVFabri() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVFabri));

            //recorre la lista de fabricantes del contenedor
            for (Fabricante f : ContenedorFabricante.getFabricantes()) {
                //almacena cada fabricante en el fichero separando los datos con dos puntos
                bw.write(f.mostrarDatosConDosPuntos());
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
    public static void importarFicheroCSVFabri(String ficheroCSV) throws YaImportadoException {

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
                int anyoFundacion = Integer.parseInt(partes[2]);
                String lugarSede = partes[3];
                int empleados = Integer.parseInt(partes[4]);
                String sitioWeb = partes[5];

                //verificamos que el codigo no este en la base de datos
                if (Conexiones.verificarExistenciaCodigo(1, codigo)) {
                    //si esta lo almacena al array creado anteriormente
                    codigoRepetido.add(codigo);

                } else {
                    //creamos el objeto fabricante con los datos extraidos del fichero
                    Fabricante f = new Fabricante(codigo, nombre, anyoFundacion, lugarSede, empleados, sitioWeb);
                    //y lo insertamos en la base de datos
                    Conexiones.insertarDatos(f);

                    System.out.println("Fabricante " + nombre + " importado correctamente");
                }
            }

            //si el fabricante ya existe salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los fabricantes con codigo " + cadena + "ya fueron importado");
            }
            System.out.println("Importación finalizada con éxito");
            br.close();

        } catch (FileNotFoundException ex) {

            System.err.println("Error: fichero no encontrado");
            System.err.println(ex);

        } catch (IOException ex) {

            System.err.println("Ha ocurrido un error");
            System.err.println(ex);

        }
    }

    /**
     * metodo que exporta los datos de la base de datos a un fichero binario
     */
    public static void exportarFicheroBinarioFabri() {

        try {
            //le pasamos el fichero que queremos que escriba
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioFabri, true));
            //escribe los datos de los fabricantes almacenados en el contenedor
            oss.writeObject(ContenedorFabricante.getFabricantes());

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
    public static void importarFicheroBinarioFabri(String ficheroBinario) throws YaImportadoException {

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto  al tipo correcto (se llama casting)
            ArrayList<Fabricante> fabricantes = (ArrayList<Fabricante>) ob;
            //cerramos el fichero
            ois.close();
            //creamos un arrayList para almacenar codigos repetidos
            ArrayList<Integer> codigoRepetido = new ArrayList<>();

            //recorremos la lista y se extrae los datos de los fabricantes 
            for (Fabricante f : fabricantes) {
                //creamos los objetos con esos datos
                Fabricante f1 = new Fabricante(f.getCodigo(), f.getNombre(), f.getAnyoFundacion(), f.getLugarSede(), f.getEmpleados(), f.getSitioWeb());
                //verificamos que los fabricantes no esten ya en la base de datos
                if (Conexiones.verificarExistenciaCodigo(1, f.getCodigo())) {
                    //almacenamos los  fabricantes que esten repetidos 
                    codigoRepetido.add(f.getCodigo());

                } else {
                    //si son nuevos los insertamos en la base de datos
                    Conexiones.insertarDatos(f1);
                    System.out.println("Fabricante " + f.getNombre() + " importado correctamente");
                }

            }

            //si hay fabricantes repetidos salta la excepcion YaImportadoException
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los fabricantes con codigo " + cadena + "ya fueron importado");
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
