/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosFicheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import contenedores.ContenedorVendedor;
import excepciones.YaImportadoException;
import java.io.*;
import java.util.ArrayList;
import modelos.Vendedor;
import servicios.Conexiones;
import utils.Configuracion;

/**
 * Clase que contiene la gestion exportar e importar a diferentes tipos de ficheros
 * @author Gabriela
 */
public class VendedorFicheros {

    /**
     * metodo que exporta los datos que hay en la base de datos a un fichero de texto
     */
    public static void exportarFicheroDeTextoVen() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroTextoVen));

            for (Vendedor v : ContenedorVendedor.getAlmacenVendedor()) {

                bw.write(v.mostrarDatosConPuntoComa());
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
    public static void importarFicheroDeTextoVen(String ficheroTXT) throws YaImportadoException {
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
                String fechaAlta = partes[2];
                String domicilio = partes[3];
                double salario = Double.parseDouble(partes[4]);
                double porcentaje = Double.parseDouble(partes[5]);

                if (Conexiones.verificarExistenciaCodigo(3, codigo)) {
                    codigoRepetido.add(codigo);

                } else {
                    Vendedor v = new Vendedor(codigo, nombre, fechaAlta, domicilio, salario, porcentaje);
                    Conexiones.insertarDatos(v);
                    System.out.println("Vendedor " + nombre + " importado correctamente");

                }

            }
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los vendedores  con codigo " + cadena + "ya fueron importado");

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
    public static void exportarFicheroJSONVen() {
        ObjectMapper mp = new ObjectMapper();

        ObjectWriter wr = mp.writerWithDefaultPrettyPrinter();

        try {
            wr.writeValue(new File(Configuracion.nombreFicheroJSONVen), ContenedorVendedor.getAlmacenVendedor());
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
    public static void importarFicheroJSONVeni(String ficheroJSON) throws YaImportadoException {

        try {
            //creamos el lector de json
            ObjectMapper om = new ObjectMapper();
            //leememos el fichero, lo interpreta, lo convierte a objeto de vendedores y los mete en un ArrayList
            //el TypeReference sirve para mantener el tipo generico , sin esto java no sabe que es una lista de Fabricante 
            ArrayList<Vendedor> vendedores = om.readValue(new File(ficheroJSON), new TypeReference<ArrayList<Vendedor>>() {
            });
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            
                for (Vendedor v : vendedores) {
                    Vendedor v1 = new Vendedor(v.getCodigo(), v.getNombre(), v.getFechaAlta(), v.getDomicilio(), v.getSalario(), v.getPorcentaje());
                    if (Conexiones.verificarExistenciaCodigo(3, v1.getCodigo())) {
                        codigoRepetido.add(v.getCodigo());

                    } else {
                        Conexiones.insertarDatos(v1);
                    }

                }
            

            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los vendedores  con codigo " + cadena + "ya fueron importado");

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
    public static void exportarFicheroCSVVen() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Configuracion.nombreFicheroCSVVen));

            for (Vendedor v : ContenedorVendedor.getAlmacenVendedor()) {

                bw.write(v.mostrarDatosConDosPuntos());
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
    public static void importarFicheroCSVVen(String ficheroCSV) throws YaImportadoException {

        try {
            BufferedReader br = new BufferedReader(new FileReader(ficheroCSV));

            String linea;
            ArrayList<Integer> codigoRepetido = new ArrayList<>();

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(":");

                // Verificar que la línea tenga todos los datos
                if (partes.length < 6) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String fechaAlta = partes[2];
                String domicilio = partes[3];
                double salario = Double.parseDouble(partes[4]);
                double porcentaje = Double.parseDouble(partes[5]);

                if (Conexiones.verificarExistenciaCodigo(3, codigo)) {
                    codigoRepetido.add(codigo);

                } else {
                    Vendedor v = new Vendedor(codigo, nombre, fechaAlta, domicilio, salario, porcentaje);
                    Conexiones.insertarDatos(v);
                    System.out.println("Vendedor " + nombre + " importado correctamente");

                }

            }

            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los vendedores  con codigo " + cadena + "ya fueron importado");

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
    public static void exportarFicheroBinarioVen() {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(Configuracion.nombreFicheroBinarioVen, true));
            oss.writeObject(ContenedorVendedor.getAlmacenVendedor());

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
    public static void importarFicheroBinarioVen(String ficheroBinario) throws YaImportadoException {

        try {
            //java abre le fichero binario y empieza a leer bytes de 0 y 1
            //interpreta esos bytes como objetos  java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBinario));
            
            //lee todo el contenido del fichero y lo devuelve como un object generico
            Object ob = ois.readObject();
            //aqui convertimos el objeto en al tipo correcto (se llama casting)
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) ob;
            //cerramos el fichero
            ois.close();
            ArrayList<Integer> codigoRepetido = new ArrayList<>();
            
                for (Vendedor v : vendedores) {
                    Vendedor v1 = new Vendedor(v.getCodigo(), v.getNombre(), v.getFechaAlta(), v.getDomicilio(), v.getSalario(), v.getPorcentaje());
                    if (Conexiones.verificarExistenciaCodigo(3, v1.getCodigo())) {
                        codigoRepetido.add(v.getCodigo());

                    } else {
                        Conexiones.insertarDatos(v1);
                    }

                }
            
            if (!codigoRepetido.isEmpty()) {
                String cadena = "";
                for (Integer i : codigoRepetido) {
                    cadena += i + ", ";

                }
                throw new YaImportadoException("Los vendedores  con codigo " + cadena + "ya fueron importado");

            }
            System.out.println("Importacion finalizada");
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
