/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;

import contenedores.ContenedorCliente;
import contenedores.ContenedorFabricante;
import contenedores.ContenedorLineaPedido;
import contenedores.ContenedorPedido;
import contenedores.ContenedorProducto;
import contenedores.ContenedorVendedor;
import excepciones.YaImportadoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Vendedor;
import modelos.Fabricante;
import modelos.LineaPedido;
import modelos.Pedido;
import modelos.Producto;
import servicios.Conexiones;
import serviciosFicheros.ClienteFicheros;
import serviciosFicheros.FabricanteFicheros;
import serviciosFicheros.LineaPedidoFicheros;
import serviciosFicheros.PedidoFicheros;
import serviciosFicheros.ProductoFicheros;
import serviciosFicheros.VendedorFicheros;
import utils.Comprobaciones;
import utils.Configuracion;

/**
 * Clase que contiene el programa principal con su menu y sus submenus
 *
 * @author Natalia y Gabriela
 */
public class Menu {

    public static Scanner teclado = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //establecemos la conexion con la base de datos
        Conexiones.conexionEstablecida();
        //lanzamos el menu
        Menu.menuPrincipal();
        //cerramos la conexion con la base de datos
        Conexiones.cierreDeConexion();

    }

    /**
     * Menu principal donde se le pregunta al usuario la tabla que desea
     * gestionar o si desea genererar los informes multitabla
     */
    public static void menuPrincipal() {
        int opcion = 0;
        boolean salir = false;
        while (!salir) {

            System.out.println("---BIENVENIDO AL MENU DE NUESTRO COMERCIO LOCAL---");
            System.out.println("Primero, eliga la tabla que desea gestionar");
            System.out.println("\t 1) FABRICANTE ");
            System.out.println("\t 2) PRODUCTO   ");
            System.out.println("\t 3) CLIENTE ");
            System.out.println("\t 4) VENDEDOR ");
            System.out.println("\t 5) PEDIDO ");
            System.out.println("\t 6) LINEA DE PEDIDO");
            System.out.println("\t 7) GENERAR INFORMES MULTITABLA");
            System.out.println("Si no quiere modificar ninguna tabla pulse");
            System.out.println("\t 8) SALIR ");

            try {
                System.out.println("Inserta una opcion");
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("ERROR ENTRADA INVALIDA, DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();
            }

            switch (opcion) {

                case 1:
                    Menu.subMenuFabricante();
                    break;

                case 2:
                    Menu.subMenuProducto();
                    break;

                case 3:
                    Menu.subMenuCliente();
                    break;

                case 4:
                    Menu.subMenuVendedor();
                    break;

                case 5:
                    Menu.subMenuPedido();
                    break;

                case 6:
                    Menu.subMenuLineaPedido();
                    break;

                case 7:
                    Menu.menuInformesMultitabla();
                    break;

                case 8:

                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    System.out.println("OPCION INCORRECTA");
            }

        }
    }

    /**
     * subMenu Fabricante con diferentes operaciones de gestion
     */
    public static void subMenuFabricante() {

        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN FABRICANTE...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();
                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL FABRICANTE");
                        int codigoFabri = teclado.nextInt(); //Verificar codigo

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabri)) {
                            System.err.println("EL CODIGO INGRESADO YA EXISTE");

                        } else {
                            teclado.nextLine();
                            System.out.println("INSERTA EL NOMBRE DEL FABRICANTE");
                            String nombreFabri = teclado.nextLine();
                            if (!Comprobaciones.comprobarStringValido(nombreFabri)) {
                                continue;
                            }
                            System.out.println("INSERTA EL AÑO DE FUNDACION DEL FABRICANTE");
                            int anyoFundacionFabri = teclado.nextInt();
                            teclado.nextLine();
                            if (!Comprobaciones.comprobarAnyo(anyoFundacionFabri)) {
                                continue;
                            }
                            System.out.println("INSERTA EL LUGAR SEDE DEL FABRICANTE ");
                            String lugarSedeFabri = teclado.nextLine();
                            if (!Comprobaciones.comprobarStringValido(lugarSedeFabri)) {
                                continue;
                            }
                            System.out.println("INSERTA EL EL NUMERO DE EMPLEADOS DEL FABRICANTE");
                            int empleadosFabri = teclado.nextInt();
                            teclado.nextLine();
                            System.out.println("INSERTA EL SITIO WEB DEL FABRICANTE");
                            String sitioWebFabri = teclado.next();

                            if (!Comprobaciones.comprobarStringValido(sitioWebFabri)) {
                                continue;
                            }
                            Fabricante f1 = new Fabricante(codigoFabri, nombreFabri, anyoFundacionFabri, lugarSedeFabri, empleadosFabri, sitioWebFabri);

                            if (Conexiones.insertarDatos(f1)) {
                                System.out.println("FABRICANTE INGRESADO CORRECTAMENTE");
                                ContenedorFabricante.agregarFabricanteNuevo(f1);

                            }

                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN FABRICANTE");
                        int codigoFabri1 = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabri1)) {
                            if (Conexiones.actualizarFila(1, codigoFabri1)) {
                                System.out.println("FABRICANTE ACTUALIZADO CORRECTAMENTE");
                            }

                        } else {
                            System.err.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:

                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN FABRICANTE");
                        int codigoFabrEli = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabrEli)) {
                            Conexiones.eliminarFila(1, codigoFabrEli);
                            System.out.println("FABRICANTE ELIMINADO CORRECTAMENTE");
                        } else {
                            System.err.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 4:

                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN FABRICANTE");
                        int codigoFabrB = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabrB)) {
                            Conexiones.consultarFila(1, codigoFabrB);

                        } else {
                            System.err.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 5:
                        System.out.println("DATOS DE LOS FABRICANTES");
                        Conexiones.consultarTodasFila(1);

                        break;

                    case 6:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor Fabricante
                        Conexiones.insersarDatosContenedorFabricante();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    FabricanteFicheros.exportarFicheroDeTextoFabri();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    FabricanteFicheros.exportarFicheroCSVFabri();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    FabricanteFicheros.exportarFicheroBinarioFabri();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    FabricanteFicheros.exportarFicheroJSONFabri();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 7:
                        System.out.println("Inserte el fichero TXT que desea importar");
                        String ficheroTXT = teclado.next();

                         {
                            try {
                                FabricanteFicheros.importarFicheroDeTextoFabri(ficheroTXT);
                            } catch (YaImportadoException ex) {
                                System.err.println(ex);
                            }
                        }
                        break;

                    case 8:
                        System.out.println("Inserte el fichero CVS que desea importar");
                        String ficheroCSV = teclado.next();
                         {
                            try {
                                FabricanteFicheros.importarFicheroCSVFabri(ficheroCSV);
                            } catch (YaImportadoException ex) {
                                System.err.println(ex);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Inserte el fichero Binario que desea importar");
                        String ficheroBinario = teclado.next();

                         {
                            try {
                                FabricanteFicheros.importarFicheroBinarioFabri(ficheroBinario);
                            } catch (YaImportadoException ex) {
                                System.err.println(ex);
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Inserte el fichero JSON que desea importar");
                        String ficheroJSON = teclado.next();

                         {
                            try {
                                FabricanteFicheros.importarFicheroJSONFabri(ficheroJSON);
                            } catch (YaImportadoException ex) {
                                System.err.println(ex);
                            }
                        }
                        break;

                    case 11:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorFabricante.mostrarDatosFabricante();
                        break;

                    case 12:

                        salir = true;

                        break;

                    default:
                        System.out.println("OPCION INCORRECTA");
                }
            } catch (InputMismatchException e) {
                System.err.println("ERROR ENTRADA INVALIDA");
                teclado.nextLine();

            }

        }
    }

    /**
     * subMenu Cliente que contiene diferentes operaciones de gestion
     */
    public static void subMenuCliente() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN CLIENTE...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL CLIENTE");
                        int codigoCli = teclado.nextInt();
                        if (Conexiones.verificarExistenciaCodigo(4, codigoCli)) {
                            System.out.println("EL CODIGO INGRESADO YA EXISTE");
                        } else {
                            teclado.nextLine();
                            System.out.println("INSERTA EL NOMBRE DEL CLIENTE");
                            String nombreCli = teclado.nextLine();
                            //Valida caracteres
                            if (!Comprobaciones.comprobarStringValido(nombreCli)) {
                                continue;
                            }
                            System.out.println("INSERTA LA FECHA DE NACIMIENTO DEL CLIENTE YYYY-MM-DD");
                            String fechaNacimiento = teclado.nextLine();
                            //Valida fecha
                            if (Comprobaciones.comprobarFecha(fechaNacimiento)) {
                                continue;
                            }
                            System.out.println("INSERTA LA DIRECCION DE ENVIO DEL CLIENTE");
                            String direccionEnvio = teclado.nextLine();
                            //Valida caracteres
                            if (!Comprobaciones.comprobarCarcateresDireccion(direccionEnvio)) {
                                continue;
                            }
                            System.out.println("INSERTA EL TELEFONO DEL CLIENTE");
                            String telCliente = teclado.nextLine();
                            if(!Comprobaciones.comprobarTelefono(telCliente)){
                                continue;
                            }
                            System.out.println("INSERTA EL CORREO DEL CLIENTE");
                            String correoCli = teclado.nextLine();
                            //Valida caracteres
                            if (!Comprobaciones.comprobarStringValido(correoCli)) {
                                continue;
                            }
                            Cliente c1 = new Cliente(codigoCli, nombreCli, fechaNacimiento, direccionEnvio, telCliente, correoCli);

                            if (Conexiones.insertarDatos(c1)) {
                                System.out.println("CLIENTE INGRESADO CORRECTAMENTE");
                                ContenedorCliente.agregarClienteNuevo(c1);

                            }

                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR CLIENTE");
                        int codigoCliFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(4, codigoCliFi)) {
                            Conexiones.actualizarFila(4, codigoCliFi);
                            System.out.println("CLIENTE ACTUALIZADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:

                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN CLIENTE");
                        int codigoCliEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(4, codigoCliEF)) {
                            Conexiones.eliminarFila(4, codigoCliEF);
                            System.out.println("CLIENTE ELIMINADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 4:

                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN CLIENTE");
                        int codigoCliCC = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(4, codigoCliCC)) {
                            Conexiones.consultarFila(4, codigoCliCC);

                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 5:
                        System.out.println("DATOS DE LOS CLIENTE");
                        Conexiones.consultarTodasFila(4);
                        break;

                    case 6:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor clientes
                        Conexiones.insertarDatosContenedorClientes();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    ClienteFicheros.exportarFicheroDeTextoCli();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    ClienteFicheros.exportarFicheroCSVCli();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    ClienteFicheros.exportarFicheroBinarioCli();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    ClienteFicheros.exportarFicheroJSONCli();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 7:
                        System.out.println("Inserte el fichero TXT que desea importar");
                        String ficheroTXT = teclado.next();
                         {
                            try {
                                ClienteFicheros.importarFicheroDeTextoCli(ficheroTXT);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;

                    case 8:
                        System.out.println("Inserte el fichero CVS que desea importar");
                        String ficheroCSV = teclado.next();
                         {
                            try {
                                ClienteFicheros.importarFicheroCSVCli(ficheroCSV);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Inserte el fichero Binario que desea importar");
                        String ficheroBinario = teclado.next();

                         {
                            try {
                                ClienteFicheros.importarFicheroBinarioCli(ficheroBinario);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Inserte el fichero JSON que desea importar");
                        String ficheroJSON = teclado.next();

                         {
                            try {
                                ClienteFicheros.importarFicheroJSONCli(ficheroJSON);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 11:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorCliente.mostrarDatosClientes();
                        break;

                    case 12:
                        salir = true;

                        break;

                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }

    /**
     * subMenu Vendedor que realiza diferentes operaciones de gestion
     */
    public static void subMenuVendedor() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN VENDEDOR...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL VENDEDOR");
                        int codigoVen = teclado.nextInt();
                        if (Conexiones.verificarExistenciaCodigo(3, codigoVen)) {
                            System.out.println("EL CODIGO INGRESADO YA EXISTE");
                        } else {
                            teclado.nextLine();
                            System.out.println("INSERTA EL NOMBRE DEL VENDEDOR");
                            String nombreVen = teclado.nextLine();
                            if (!Comprobaciones.comprobarStringValido(nombreVen)) {
                                continue;
                            }
                            System.out.println("INSERTA LA FECHA DE ALTA DEL VENDEDOR YYYY-MM-DD");
                            String fechaAltaVen = teclado.nextLine();
                            //Valida formato de fecha
                            if (Comprobaciones.comprobarFecha(fechaAltaVen)) {
                                continue;
                            }
                            System.out.println("INSERTA EL DOMICILIO DEL VENDEDOR");
                            String domicilioVen = teclado.nextLine();
                            //Valida caracteres
                            if (!Comprobaciones.comprobarCarcateresDireccion(domicilioVen)) {
                                continue;
                            }
                            System.out.println("INSERTA EL SALARIO DEL VENDEDOR");
                            double salarioVen = teclado.nextDouble();
                            teclado.nextLine();
                            System.out.println("INSERTA EL PORCENTAJE DEL VENDEDOR");
                            double porcentajeVen = teclado.nextDouble();
                            teclado.nextLine();
                            if (!Comprobaciones.validarNumero(salarioVen)) {
                                continue;
                            }
                            Vendedor ven = new Vendedor(codigoVen, nombreVen, fechaAltaVen, domicilioVen, salarioVen, porcentajeVen);

                            if (Conexiones.insertarDatos(ven)) {
                                System.out.println("VENDEDOR INGRESADO CORRECTAMENTE");
                                ContenedorVendedor.agregarVendedorNuevo(ven);
                            }

                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN VENDEDOR");
                        int codigoVenFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(3, codigoVenFi)) {
                            Conexiones.actualizarFila(3, codigoVenFi);
                            System.out.println("VENDEDOR ACTUALIZADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:

                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN VENDEDOR");
                        int codigoVenEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(3, codigoVenEF)) {
                            Conexiones.eliminarFila(3, codigoVenEF);
                            System.out.println("VENDEDOR ELIMINADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 4:

                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN VENDEDOR");
                        int codigoVenCF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(3, codigoVenCF)) {
                            Conexiones.consultarFila(3, codigoVenCF);
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 5:
                        System.out.println("DATOS DE LOS VENDEDORES:");
                        Conexiones.consultarTodasFila(3);

                        break;

                    case 6:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor vendedor
                        Conexiones.insertarDatosContenedorVendedor();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    VendedorFicheros.exportarFicheroDeTextoVen();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    VendedorFicheros.exportarFicheroCSVVen();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    VendedorFicheros.exportarFicheroBinarioVen();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    VendedorFicheros.exportarFicheroJSONVen();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 7:
                        System.out.println("Inserte el fichero TXT que desea importar");
                        String ficheroTXT = teclado.next();

                         {
                            try {
                                VendedorFicheros.importarFicheroDeTextoVen(ficheroTXT);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 8:
                        System.out.println("Inserte el fichero CVS que desea importar");
                        String ficheroCSV = teclado.next();

                         {
                            try {
                                VendedorFicheros.importarFicheroCSVVen(ficheroCSV);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Inserte el fichero Binario que desea importar");
                        String ficheroBinario = teclado.next();

                         {
                            try {
                                VendedorFicheros.importarFicheroBinarioVen(ficheroBinario);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;

                    case 10:
                        System.out.println("Inserte el fichero JSON que desea importar");
                        String ficheroJSON = teclado.next();
                         {
                            try {
                                VendedorFicheros.importarFicheroJSONVeni(ficheroJSON);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;

                    case 11:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorVendedor.mostrarDatosVendedor();
                        break;

                    case 12:
                        salir = true;

                        break;

                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }

    /**
     * subMenu Producto que realiza diferente operaciones de gestion
     */
    public static void subMenuProducto() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN PRODUCTO...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL PRODUCTO");
                        int codigoPro = teclado.nextInt();
                        teclado.nextLine();
                        if (Conexiones.verificarExistenciaCodigo(2, codigoPro)) {
                            System.out.println("EL CODIGO INGRESADO YA EXISTE");
                        } else {
                            System.out.println("INSERTA EL CODIGO DEL FABRICANTE");
                            int codidoFab = teclado.nextInt();
                            teclado.nextLine();
                            if (Conexiones.verificarExistenciaCodigo(1, codidoFab)) {
                                System.out.println("INSERTA EL NOMBRE DEL PRODUCTO");
                                String nombre = teclado.nextLine();
                                //Valida caracteres 
                                if (!Comprobaciones.comprobarStringValido(nombre)) {
                                    continue;
                                }
                                System.out.println("INSERTA LA CATEGORIA DEL PRODUCTO");
                                String categoria = teclado.nextLine();
                                //Valida caracteres 
                                if (!Comprobaciones.comprobarStringValido(categoria)) {
                                    continue;
                                }
                                System.out.println("INSERTA LA DISPONIBILIDAD DEL PRODUCTO: DISPONIBLE/POCAS UNIDADES/NO DISPONIBLE");
                                String disponibilidad = teclado.nextLine();
                                //Valida disponibilidad
                                if (!Comprobaciones.verificarDisponibilidad(disponibilidad)) {
                                    continue;
                                }
                                System.out.println("INSERTA EL PRECIO DE VENTA DEL PRODUCTO");
                                double precioVenta = teclado.nextDouble();
                                teclado.nextLine();
                                //Valida precio
                                if (!Comprobaciones.validarNumero(precioVenta)) {
                                    continue;
                                }

                                Producto p1 = new Producto(codigoPro, codidoFab, nombre, categoria, disponibilidad, precioVenta);

                                if (Conexiones.insertarDatos(p1)) {
                                    System.out.println("PRODUCTO INGRESADO CORRECTAMENTE");
                                    ContenedorProducto.agregarProductoNuevo(p1);
                                }

                            } else {
                                System.out.println("EL CODIGO DEL FABRICANTE NO EXISTE");
                            }

                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR PRODUCTO");
                        int codigoProFi = teclado.nextInt();

                        if (Conexiones.verificarExistenciaCodigo(2, codigoProFi)) {
                            if (Conexiones.actualizarFila(2, codigoProFi)) {
                                System.out.println("PRODUCTO ACTUALIZADO CORRECTAMENTE");
                            }

                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:
                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN PRODUCTO");
                        int codigoProEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(2, codigoProEF)) {
                            Conexiones.eliminarFila(2, codigoProEF);
                            System.out.println("PRODUCTO ELIMINADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 4:
                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN PRODUCTO");
                        int codigoProCF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(2, codigoProCF)) {
                            Conexiones.consultarFila(2, codigoProCF);
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 5:
                        System.out.println("DATOS DE LOS PRODUCTOS:");
                        Conexiones.consultarTodasFila(2);

                        break;

                    case 6:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor productos
                        Conexiones.insertarDatosContenedoresProductos();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    ProductoFicheros.exportarFicheroDeTextoPro();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    ProductoFicheros.exportarFicheroCSVPro();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    ProductoFicheros.exportarFicheroBinarioPro();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    ProductoFicheros.exportarFicheroJSONPro();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 7:
                        System.out.println("Inserte el fichero TXT que desea importar");
                        String ficheroTXT = teclado.next();
                         {
                            try {
                                ProductoFicheros.importarFicheroDeTextoPro(ficheroTXT);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;

                    case 8:
                        System.out.println("Inserte el fichero CVS que desea importar");
                        String ficheroCSV = teclado.next();

                         {
                            try {
                                ProductoFicheros.importarFicheroCSVPro(ficheroCSV);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Inserte el fichero Binario que desea importar");
                        String ficheroBinario = teclado.next();

                         {
                            try {
                                ProductoFicheros.importarFicheroBinarioPro(ficheroBinario);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Inserte el fichero JSON que desea importar");
                        String ficheroJSON = teclado.next();

                         {
                            try {
                                ProductoFicheros.importarFicheroJSONPro(ficheroJSON);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 11:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorProducto.mostrarDatosProductos();
                        break;

                    case 12:
                        salir = true;

                        break;
                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }

    /**
     * subMenu Pedido que realiza diferentes operaciones de gestion
     */
    public static void subMenuPedido() {
        boolean salir = false;

        while (!salir) {
            System.out.println("INICIANDO SESION EN PEDIDO...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        ArrayList<Integer> productos = new ArrayList<>();
                        ArrayList<Integer> cantidades = new ArrayList<>();
                        ArrayList<Double> subtotaltesli = new ArrayList<>();
                        double importeFinal = 0;
                        int codigopedidogeneral = Conexiones.codigoPedido();

                        System.out.println("MOSTRANDO LISTA DE PRODUCTOS DISPONIBLES");
                        Conexiones.consultarTodasFila(2);
                        boolean pedir = false;
                        while (!pedir) {
                            System.out.println("DIME EL CODIGO DEL PRODUCTO QUE DESEA PEDIR");
                            int codigoPro = teclado.nextInt();
                            if (!Conexiones.verificarExistenciaCodigo(2, codigoPro)) {
                                System.out.println("EL CODIGO DEL PRODUCTO INGRESADO NO EXISTE");
                                continue;
                            }
                            System.out.println("DIME LAS UNIDADES");
                            int cantidad = teclado.nextInt();
                            if (cantidad <= 0) {
                                System.out.println("CANTIDAD INVALIDA");
                                continue;
                            }

                            double subTotal = (Conexiones.precioProducto(codigoPro) * cantidad);
                            importeFinal += subTotal;
                            productos.add(codigoPro);
                            cantidades.add(cantidad);
                            subtotaltesli.add(subTotal);
                            System.out.println("DESEA PEDIR MAS PRODUCTOS SI/NO");
                            String opcion1 = teclado.next();
                            if (opcion1.equalsIgnoreCase("Si")) {
                                pedir = false;

                            } else {
                                pedir = true;
                            }

                        }
                        System.out.println("INSERTA EL CODIGO DEL VENDEDOR");
                        int codigoVen = teclado.nextInt();
                        teclado.nextLine();
                        if (!Conexiones.verificarExistenciaCodigo(3, codigoVen)) {
                            System.out.println("EL CODIGO INGRESADO DE VENDEDOR NO EXISTE");
                            continue;
                        }
                        System.out.println("INSERTA EL CODIGO DEL CLIENTE");
                        int codigoCli = teclado.nextInt();
                        teclado.nextLine();
                        if (!Conexiones.verificarExistenciaCodigo(4, codigoCli)) {
                            System.out.println("EL CODIGO INGRESADO DE CLIENTE NO EXISTE");
                            continue;
                        }
                        Pedido ped = new Pedido(codigoVen, codigoCli, LocalDate.now().toString(), LocalDate.now().plusDays(7).toString(), "realizado", importeFinal);

                        if (Conexiones.insertarDatos(ped)) {
                            System.out.println("PEDIDO CON CODIGO "+codigopedidogeneral+"INGRESADO CORRECTAMENTE");
                            ContenedorPedido.agregarPedidoNuevos(ped);
                        }

                        for (int i = 0; i < productos.size(); i++) {
                            int fb = productos.get(i);
                            int ca = cantidades.get(i);
                            double sb = subtotaltesli.get(i);

                            LineaPedido lp = new LineaPedido(codigopedidogeneral, fb, ca, sb);
                            if (Conexiones.insertarDatos(lp)) {
                                ContenedorLineaPedido.agregarLineaPedidoNuevos(lp);
                            }

                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN PEDIDO");
                        int codigoPedFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedFi)) {
                            if (Conexiones.actualizarFila(5, codigoPedFi)) {
                                System.out.println("PEDIDO ACTUALIZADO CORRECTAMENTE");

                            }
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:
                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN PEDIDO");
                        int codigoPedEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedEF)) {
                            Conexiones.eliminarFila(5, codigoPedEF);
                            System.out.println("PEDIDO ELIMINADO CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }
                        break;

                    case 4:
                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN PEDIDO");
                        int codigoPedCP = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedCP)) {
                            Conexiones.consultarFila(5, codigoPedCP);

                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }
                        break;

                    case 5:
                        System.out.println("DATOS DE TODOS LOS PEDIDOS");
                        Conexiones.consultarTodasFila(5);
                        break;

                    case 6:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor pedidos
                        Conexiones.insertarDatosContenedoresPedidos();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    PedidoFicheros.exportarFicheroDeTextoPed();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    PedidoFicheros.exportarFicheroCSVPed();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    PedidoFicheros.exportarFicheroBinarioPed();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    PedidoFicheros.exportarFicheroJSONPed();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 7:
                        System.out.println("Inserte el fichero de texto que desea importar");
                        String ficheroTXT = teclado.next();

                        PedidoFicheros.importarFicheroDeTextoPed(ficheroTXT);

                        break;

                    case 8:
                        System.out.println("Inserte el fichero CVS que desea importar");
                        String ficheroCSV = teclado.next();

                        PedidoFicheros.importarFicheroCSVPed(ficheroCSV);

                        break;

                    case 9:
                        System.out.println("Inserte el fichero Binario que desea importar");
                        String ficheroBinario = teclado.next();
                        PedidoFicheros.importarFicheroBinarioPed(ficheroBinario);

                        break;

                    case 10:
                        System.out.println("Inserte el fichero JSON que desea importar");
                        String ficheroJSON = teclado.next();

                        PedidoFicheros.importarFicheroJSONPed(ficheroJSON);

                        break;

                    case 11:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorPedido.mostrarDatosPedidos();
                        break;

                    case 12:
                        salir = true;

                        break;

                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }

    /**
     * subMenu lineaPedido que realiza diferentes operaciones de gestion
     */
    public static void subMenuLineaPedido() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN LINEA DE PEDIDO...");
            System.out.println("Que operacion de gestion desea realizar ");
            System.out.println("\t 1) ACTUALIZAR UNA FILA POR CODIGO IDENTIFICATIVO   ");
            System.out.println("\t 2) ELIMINAR UNA FILA POR CODIGO IDENTIFICATIVO ");
            System.out.println("\t 3) CONSULTAR UNA FILA POR CODIGO IDENTIFICATIVO ");
            System.out.println("\t 4) CONSULTAR TODAS LAS FILAS DE LA TABLA, ORDENADAS DE FORMA ASCENDENTE ");
            System.out.println("Si no quiere hacer ninguna modificacion y desea exportar la tabla pulse");
            System.out.println("\t 5) EXPORTAR TABLAS");
            System.out.println("Si desea importarla eliga la forma ");
            System.out.println("\t 6) IMPORTAR TABLA A FICHERO DE TEXTO");
            System.out.println("\t 7) IMPORTAR TABLA A FICHERO DE CSV");
            System.out.println("\t 8) IMPORTAR TABLA A FICHERO BINARIO");
            System.out.println("\t 9) IMPORTAR TABLA A FICHERO JSON");
            System.out.println("Si desea ver los datos insertados durante la ejecucion pulse ");
            System.out.println("\t 10) VER DATOS INSERTADOS DURANTE LA EJECUCION");
            System.out.println("Si no quiere realizar ninguna operacion de gestion pulse ");
            System.out.println("\t 11) SALIR ");

            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA ACTUALIZAR");
                        System.out.print("PEDIDO:");
                        int codigoPedFi = teclado.nextInt();
                        teclado.nextLine();

                        System.out.print("PRODUCTO:");
                        int codigoProFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaLineaPedido(codigoPedFi, codigoProFi)) {
                            if (Conexiones.actualizarLineaPedido(codigoPedFi, codigoProFi)) {
                                System.out.println("LINEA DE PEDIDO ACTUALIZADA CORRECTAMENTE");
                                
                            }
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA ELIMINAR LINEA DE PEDIDO");
                        System.out.print("PEDIDO:");
                        int codigoPedE = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("PRODUCTO:");
                        int codigoProE = teclado.nextInt();
                        teclado.nextLine();
                        
                        if (Conexiones.verificarExistenciaLineaPedido(codigoPedE, codigoProE)) {
                            Conexiones.eliminarLineaPedido(codigoPedE, codigoProE);
                            System.out.println("LINEA DE PEDIDO ELIMINADA CORRECTAMENTE");
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }

                        break;

                    case 3:
                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA CONSULTAR UNA LINEA DE PEDIDO");
                        System.out.print("PEDIDO:");
                        int codigoPedCC = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("PRODUCTO:");
                        int codigoProECC = teclado.nextInt();
                        teclado.nextLine();
                        
                        if (Conexiones.verificarExistenciaLineaPedido(codigoPedCC, codigoProECC)) {
                            Conexiones.consultarLineaPedido(codigoPedCC, codigoProECC);
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }
                        break;

                    case 4:
                        System.out.println("DATOS DE LINEAS DE PEDIDO");
                        Conexiones.consultarTodasFila(6);

                        break;

                    case 5:
                        boolean salir1 = false;
                        //insertamos los datos de la base de datos al contenedor lineaPedido
                        Conexiones.insertarDatosContenedoresLP();
                        while (!salir1) {
                            Menu.menuExportar();
                            int opcion1 = teclado.nextInt();
                            switch (opcion1) {

                                case 1:
                                    System.out.println("Exportando a fichero TXT...");
                                    LineaPedidoFicheros.exportarFicheroDeTextoLP();
                                    break;

                                case 2:
                                    System.out.println("Exportando a fichero CSV...");
                                    LineaPedidoFicheros.exportarFicheroCSVLP();
                                    break;

                                case 3:
                                    System.out.println("Exportando a fichero Binario...");
                                    LineaPedidoFicheros.exportarFicheroBinarioLP();
                                    break;

                                case 4:
                                    System.out.println("Exportando a fichero JSON...");
                                    LineaPedidoFicheros.exportarFicheroJSONLP();
                                    break;

                                case 5:
                                    salir1 = true;
                                    break;

                                default:
                                    System.out.println("OPCION INCORRECTA");
                            }
                        }

                        break;

                    case 6:
                        System.out.println("Inserte el fichero de texto que desea importar");
                        String ficheroTXT = teclado.next();
                         {
                            try {
                                LineaPedidoFicheros.importarFicheroDeTextoLP(ficheroTXT);
                            } catch (YaImportadoException ex) {
                                System.err.println(ex);
                            }
                        }
                        break;

                    case 7:
                        System.out.println("Inserte el fichero CSV que desea importar");
                        String ficheroCSV = teclado.next();
                         {
                            try {
                                LineaPedidoFicheros.importarFicheroCSVLP(ficheroCSV);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 8:
                        System.out.println("Inserte el fichero binario que desea importar");
                        String ficheroBinario = teclado.next();

                         {
                            try {
                                LineaPedidoFicheros.importarFicheroBinarioLP(ficheroBinario);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Inserte el fichero json que desea importar");
                        String ficheroJSON = teclado.next();

                         {
                            try {
                                LineaPedidoFicheros.importarFicheroJSONLP(ficheroJSON);
                            } catch (YaImportadoException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorLineaPedido.mostrarLineaPedido();
                        break;

                    case 11:
                        salir = true;

                        break;

                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }

    /**
     * Historial de subMenus
     */
    public static void historialSubMenus() {
        System.out.println("Que operacion de gestion desea realizar ");
        System.out.println("\t 1) INSERTAR UNA FILA EN LA TABLA ");
        System.out.println("\t 2) ACTUALIZAR UNA FILA POR CODIGO IDENTIFICATIVO   ");
        System.out.println("\t 3) ELIMINAR UNA FILA POR CODIGO IDENTIFICATIVO ");
        System.out.println("\t 4) CONSULTAR UNA FILA POR CODIGO IDENTIFICATIVO ");
        System.out.println("\t 5) CONSULTAR TODAS LAS FILAS DE LA TABLA, ORDENADAS DE FORMA ASCENDENTE ");
        System.out.println("Si no quiere hacer ninguna modificacion y desea exportar la tabla pulse");
        System.out.println("\t 6) EXPORTAR TABLAS");
        System.out.println("Si desea importarla eliga la forma ");
        System.out.println("\t 7) IMPORTAR TABLA A FICHERO DE TEXTO");
        System.out.println("\t 8) IMPORTAR TABLA A FICHERO DE CSV");
        System.out.println("\t 9) IMPORTAR TABLA A FICHERO BINARIO");
        System.out.println("\t 10) IMPORTAR TABLA A FICHERO JSON");
        System.out.println("Si desea ver los datos insertados durante la ejecucion pulse ");
        System.out.println("\t 11) VER DATOS INSERTADOS DURANTE LA EJECUCION");
        System.out.println("Si no quiere realizar ninguna operacion de gestion pulse ");
        System.out.println("\t 12) SALIR ");
    }

    /**
     * menu Exportar
     */
    public static void menuExportar() {
        System.out.println("ELIGA LA MANERA EN LA QUE DESEA EXPORTAR LA TABLA");
        System.out.println("\t 1) EXPORTAR TABLA A FICHERO DE TEXTO");
        System.out.println("\t 2) EXPORTAR TABLA A FICHERO DE CSV");
        System.out.println("\t 3) EXPORTAR TABLA A FICHERO BINARIO");
        System.out.println("\t 4) EXPORTAR TABLA A FICHERO JSON");
        System.out.println("\t 5) SALIR");
    }

    /**
     * menu informes multitabla
     */
    public static void menuInformesMultitabla() {
        boolean salir2 = false;
        while (!salir2) {
            System.out.println("QUE INFORME DESEA GENERAR");
            System.out.println("\t 1) CONSULTAR TODOS LOS FABRICANTES,JUNTO A SUS PRODUCTOS Y DISPONIBILIDADES");
            System.out.println("\t 2) CONSULTAR PEDIDOS REALIZADOS POR UN CLIENTE");
            System.out.println("\t 3) CONSULTAR PRODUCTOS Y CLIENTES QUE LOS HAN COMPRADO");
            System.out.println("\t 4) CONSULTAR PRODUCTOS COMPRADOS POR CADA CLIENTE");
            System.out.println("\t 5) CONSULTAR FABRICANTES Y CLIENTES COMPRADOS");
            System.out.println("\t 6) CONSULTAR PEDIDOS GESTIONADOS POR VENDEDORES Y SUS COMISIONES");
            System.out.println("\t 7) CONSULTAR PRODUCTOS MAS VENDIDOS");
            System.out.println("\t 8) CONSULTAR FABRICANTES CON PRODUCTOS SIN VENTAS");
            System.out.println("\t 9) CONSULTAR ESTADÍSTICAS DE VENTAS DE VENDEDORES");
            System.out.println("\t 10) SALIR");

            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:
                        Conexiones.informesMultitabla(1);

                        break;

                    case 2:
                        System.out.println("INGRESA EL CODIGO DEL CLIENTE QUE DESEA CONSULTAR");
                        int codigoCli = teclado.nextInt();
                        if (!Conexiones.verificarExistenciaCodigo(4, codigoCli)) {
                            System.out.println("EL CODIGO INGRESADO NO EXISTE EN LA BASE DE DATOS");
                        } else {
                            Conexiones.consulta2(codigoCli);
                        }

                        break;

                    case 3:
                        Conexiones.informesMultitabla(2);
                        break;

                    case 4:
                        Conexiones.informesMultitabla(3);
                        break;

                    case 5:
                        Conexiones.informesMultitabla(4);
                        break;

                    case 6:
                        Conexiones.informesMultitabla(5);
                        break;

                    case 7:
                        Conexiones.informesMultitabla(6);
                        break;

                    case 8:
                        Conexiones.informesMultitabla(7);
                        break;

                    case 9:
                        Conexiones.informesMultitabla(8);
                        break;

                    case 10:
                        salir2 = true;

                        break;

                    default:
                        System.out.println("OPCION INVALIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }

    }
}
