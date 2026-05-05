/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Vendedor;
import modelos.Fabricante;
import servicios.Conexiones;

/**
 *
 * @author Natalia
 */
public class Menu {

    public static Scanner teclado = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Menu.menuPrincipal();

    }

    public static void menuPrincipal() {
        int opcion = 0;
        boolean salir = false;
        while (!salir) {
            System.out.println("---BIENVENIDO AL MENU DE NUESTRO COMERCIO LOCAL---");
            System.out.println("Primero, eliga la tabla que desea modificar");
            System.out.println("\t 1) FABRICANTE ");
            System.out.println("\t 2) PRODUCTO   ");
            System.out.println("\t 3) CLIENTE ");
            System.out.println("\t 4) VENDEDOR ");
            System.out.println("\t 5) PEDIDO ");
            System.out.println("\t 6) LINEA DE PEDIDO");
            System.out.println("Si no quiere modificar ninguna tabla pulse");
            System.out.println("\t 7) SALIR ");

            
            try{
                System.out.println("Inserta una opcion");
                opcion = teclado.nextInt();
            }catch (InputMismatchException e){
                System.err.println("Error. Debes insertar numeros");
                System.err.println(e);
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
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }

        }
    }

    public static void subMenuFabricante() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN FABRICANTE...");
            Menu.historialSubMenus();

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    servicios.Conexiones.conexionEstablecida();
                    System.out.println("INSERTA EL CODIGO DEL FABRICANTE");
                    int codigoFabri = teclado.nextInt();
                    System.out.println("INSERTA EL NOMBRE DEL FABRICANTE");
                    String nombreFabri = teclado.nextLine();
                    System.out.println("INSERTA EL AÑO DE FUNDACION DEL FABRICANTE");
                    int anyoFundacionFabri = teclado.nextInt();
                    System.out.println("INSERTA EL LUGAR SEDE DEL FABRICANTE ");
                    String lugarSeedeFabri = teclado.nextLine();
                    System.out.println("INSERTA EL EL NUMERO DE EMPLEADOS DEL FABRICANTE");
                    int empleadosFabri = teclado.nextInt();
                    System.out.println("INSERTA EL SITIO WEB DEL FABRICANTE");
                    String sitioWebFabri = teclado.next();
                    
                    Fabricante f1 = new Fabricante(codigoFabri, nombreFabri, anyoFundacionFabri, lugarSeedeFabri, empleadosFabri, sitioWebFabri);
                    
                   Conexiones.insertarDatos(f1);
                   
                    
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }
    }

    public static void subMenuCliente() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN CLIENTE...");
            Menu.historialSubMenus();

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    servicios.Conexiones.conexionEstablecida();
                    System.out.println("INSERTA EL CODIGO DEL CLIENTE");
                    int codigoCli = teclado.nextInt();
                    System.out.println("INSERTA EL NOMBRE DEL CLIENTE");
                    String nombreCli = teclado.nextLine();
                    System.out.println("INSERTA LA FECHA DE NACIMIENTO DEL CLIENTE");
                    String fechaNacimiento = teclado.nextLine();
                    System.out.println("INSERTA LA DIRECCION DE ENVIO DEL CLIENTE");
                    String direccionEnvio = teclado.nextLine();
                    System.out.println("INSERTA EL TELEFONO DEL CLIENTE");
                    String telCliente = teclado.next();
                    System.out.println("INSERTA EL CORREO DEL CLIENTE");
                    String correoCli = teclado.next();
                    
                    Cliente c1 = new Cliente(codigoCli, nombreCli, fechaNacimiento, direccionEnvio, telCliente, correoCli);
                    
               
                    
                    break;


                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }

    }

    public static void subMenuVendedor() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN VENDEDOR...");
            Menu.historialSubMenus();
            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    servicios.Conexiones.conexionEstablecida();
                    System.out.println("INSERTA EL CODIGO DEL VENDEDOR");
                    int codigoVen = teclado.nextInt();
                    System.out.println("INSERTA EL NOMBRE DEL VENDEDOR");
                    String nombreVen = teclado.nextLine();
                    System.out.println("INSERTA LA FECHA DE ALTA DEL VENDEDOR");
                    String fechaAltaVen = teclado.nextLine();
                    System.out.println("INSERTA EL DOMICILIO DEL VENDEDOR");
                    String domicilioVen = teclado.nextLine();
                    System.out.println("INSERTA EL SALARIO DEL VENDEDOR");
                    double salarioVen = teclado.nextDouble();
                    System.out.println("INSERTA EL PORCENTAJE DEL VENDEDOR");
                    double porcentajeVen = teclado.nextDouble();
                    
                    Vendedor ven = new Vendedor(codigoVen, nombreVen, fechaAltaVen, domicilioVen);
                    
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }

    }

    public static void subMenuProducto() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN PRODUCTO...");
            Menu.historialSubMenus();

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }

    }

    public static void subMenuPedido() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN PEDIDO...");
            Menu.historialSubMenus();

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }

    }

    public static void subMenuLineaPedido() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN LINEA DE PEDIDO...");
            Menu.historialSubMenus();

            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 13:

                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    throw new AssertionError("Opcion incorrecta");
            }
        }

    }
    
    public static void historialSubMenus(){
        System.out.println("Que operacion de gestion desea realizar ");
            System.out.println("\t 1) INSERTAR UNA FILA EN LA TABLA ");
            System.out.println("\t 2) ACTUALIZAR UNA FILA POR CODIGO IDENTIFICATIVO   ");
            System.out.println("\t 3) ELIMINAR UNA FILA POR CODIGO IDENTIFICATIVO ");
            System.out.println("\t 4) CONSULTAR UNA FILA POR CODIGO IDENTIFICATIVO ");
            System.out.println("\t 5) CONSULTAR TODAS LAS FILAS DE LA TABLA, ORDENADAS DE FORMA ASCENDENTE ");
            System.out.println("Si no quiere hacer ninguna modificacion eliga de que manera quiere exportar la tabla ");
            System.out.println("\t 6) EXPORTAR TABLA A FICHERO DE TEXTO");
            System.out.println("\t 7) EXPORTAR TABLA A FICHERO DE CSV");
            System.out.println("\t 8) EXPORTAR TABLA A FICHERO BINARIO");
            System.out.println("\t 9) EXPORTAR TABLA A FICHERO JSON");
            System.out.println("Si desea importarla eliga la forma ");
            System.out.println("\t 10) IMPORTAR TABLA A FICHERO DE TEXTO");
            System.out.println("\t 11) IMPORTAR TABLA A FICHERO DE CSV");
            System.out.println("\t 12) IMPORTAR TABLA A FICHERO BINARIO");
            System.out.println("\t 13) IMPORTAR TABLA A FICHERO JSON");
            System.out.println("Si no quiere realizar ninguna operacion de gestion pulse ");
            System.out.println("\t 14) SALIR ");
    }
}
