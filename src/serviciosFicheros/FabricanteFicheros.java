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
 *
 * @author isard
 */
public class FabricanteFicheros {

    public static void exportarFicheroDeTextoFabri() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoFabri));

            for (Fabricante f : ContenedorFabricante.getFabricantes()) {

                bw.write(f.mostrarDatosConPuntoComa());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroDeTextoFabri(String ficheroTXT) throws YaImportadoException {

        try {

            BufferedReader br = new BufferedReader(new FileReader(ficheroTXT));
            String linea;
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int anyoFundacion = Integer.parseInt(partes[2]);
                String lugarSede = partes[3];
                int empleados = Integer.parseInt(partes[4]);
                String sitioWeb = partes[5];

                if (Conexiones.verificarExistenciaCodigo(1, codigo)) {
                    codigoRepetido.add(codigo);
                    //System.err.println("El fabricante con código " + codigo + " ya existe en la base de datos");

                } else {

                    Fabricante f = new Fabricante(codigo, nombre, anyoFundacion, lugarSede, empleados, sitioWeb);

                    //ContenedorFabricante.agregarFabricante(f);
                    Conexiones.insertarDatos(f);

                    System.out.println("Fabricante " + nombre + " importado correctamente");
                }
            }

            if (!codigoRepetido.isEmpty()) {
                for (Integer i : codigoRepetido) {

                    throw new YaImportadoException("El fabricante con codigo " + i + "ya fue importado");
                }

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

    public static void exportarFicheroJSONFabri() {

        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONFabri), ContenedorFabricante.getFabricantes());
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }

    }

    public static void importarFicheroJSONFabri(String nombreFicheroJson) throws YaImportadoException {
        //comprobamos que si el contenedor tiene fabricantes dentro y si ya hay datos lanzamos la excepcion para evitar importar el fichero varias veces
        if (!ContenedorFabricante.getFabricantes().isEmpty()) {
            throw new YaImportadoException("Los fabricantes ya fueron importados");
        }

        //creamos el lector de json
        ObjectMapper om = new ObjectMapper();

        try {
            //leememos el fichero, lo interpreta, lo convierte a objetod de Fabricante y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Fabricante> fabricantes = om.readValue(new File(nombreFicheroJson), new TypeReference<ArrayList<Fabricante>>() {
            });
            //Aqui toma los datos leidos del json y los añade al contenedor de Fabricantes
            //ContenedorFabricante.getFabricantes().addAll(fabricantes);
            while (!fabricantes.isEmpty()) {
                for (Fabricante f : fabricantes) {
                    Fabricante f1 = new Fabricante(f.getCodigo(), f.getNombre(), f.getAnyoFundacion(), f.getLugarSede(), f.getEmpleados(), f.getSitioWeb());
                    if (Conexiones.verificarExistenciaCodigo(1, f.getCodigo())) {

                        System.err.println("El fabricante con código " + f.getCodigo() + " ya existe en la base de datos");

                    } else {
                        Conexiones.insertarDatos(f1);

                    }

                }
            }
            System.out.println("Importación finalizada con éxito");
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void exportarFicheroCSVFabri() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVFabri));

            for (Fabricante f : ContenedorFabricante.getFabricantes()) {

                bw.write(f.mostrarDatosConDosPuntos());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error");
            System.err.println(ex);
        }
    }

    public static void importarFicheroCSVFabri(String ficheroCSV) throws YaImportadoException {
        

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

                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    int anyoFundacion = Integer.parseInt(partes[2]);
                    String lugarSede = partes[3];
                    int empleados = Integer.parseInt(partes[4]);
                    String sitioWeb = partes[5];

                    if (Conexiones.verificarExistenciaCodigo(1, codigo)) {

                        System.err.println("El fabricante con código " + codigo + " ya existe en la base de datos");

                    } else {

                        Fabricante f = new Fabricante(codigo, nombre, anyoFundacion, lugarSede, empleados, sitioWeb);

                        //ContenedorFabricante.agregarFabricante(f);
                        Conexiones.insertarDatos(f);

                        System.out.println("Fabricante " + nombre + " importado correctamente");
                    }
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

    public static void exportarFicheroBinarioFabri() {

        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioFabri, true));
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

    public static void importarFicheroBinarioFabri(String ficheroBinario) throws YaImportadoException {

        if (!ContenedorFabricante.getFabricantes().isEmpty()) {
            throw new YaImportadoException("Los fabricantes ya fueron importados");
        }

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
            //mete todos los datos al contenedor
            //ContenedorFabricante.getFabricantes().addAll(fabricantes);
            while (!fabricantes.isEmpty()) {
                for (Fabricante f : fabricantes) {
                    Fabricante f1 = new Fabricante(f.getCodigo(), f.getNombre(), f.getAnyoFundacion(), f.getLugarSede(), f.getEmpleados(), f.getSitioWeb());
                    if (Conexiones.verificarExistenciaCodigo(1, f.getCodigo())) {

                        System.err.println("El fabricante con código " + f.getCodigo() + " ya existe en la base de datos");

                    } else {
                        Conexiones.insertarDatos(f1);
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
