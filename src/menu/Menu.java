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
import java.sql.SQLException;
import java.time.LocalDate;
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

/**
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

        Menu.menuPrincipal();

    }

    /**
     * Menu principal donde se le pregunta al usuario la tbla que desea gestionar
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
            System.out.println("Si no quiere modificar ninguna tabla pulse");
            System.out.println("\t 7) SALIR ");

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
                    salir = true;
                    System.out.println("Saliendo del programa ...");
                    break;

                default:
                    System.out.println("OPCION INCORRECTA");
            }

        }
    }

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
                        int codigoFabri = teclado.nextInt();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabri)) {
                            System.out.println("EL CODIGO INGRESADO YA EXISTE");
                        } else {
                            teclado.nextLine();
                            System.out.println("INSERTA EL NOMBRE DEL FABRICANTE");
                            String nombreFabri = teclado.nextLine();
                            System.out.println("INSERTA EL AÑO DE FUNDACION DEL FABRICANTE");
                            int anyoFundacionFabri = teclado.nextInt();
                            teclado.nextLine();
                            System.out.println("INSERTA EL LUGAR SEDE DEL FABRICANTE ");
                            String lugarSedeFabri = teclado.nextLine();
                            System.out.println("INSERTA EL EL NUMERO DE EMPLEADOS DEL FABRICANTE");
                            int empleadosFabri = teclado.nextInt();
                            teclado.nextLine();
                            System.out.println("INSERTA EL SITIO WEB DEL FABRICANTE");
                            String sitioWebFabri = teclado.next();

                            Fabricante f1 = new Fabricante(codigoFabri, nombreFabri, anyoFundacionFabri, lugarSedeFabri, empleadosFabri, sitioWebFabri);

                            Conexiones.insertarDatos(f1);
                            ContenedorFabricante.agregarFabricanteNuevo(f1);
                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN FABRICANTE");
                        int codigoFabri1 = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabri1)) {
                            Conexiones.actualizarFila(1, codigoFabri1);
                            System.out.println("FABRICANTE ACTUALIZADO");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:

                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN FABRICANTE");
                        int codigoFabrEli = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabrEli)) {
                            Conexiones.eliminarFila(1, codigoFabrEli);
                            System.out.println("FABRICANTE ELIMINADO");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 4:

                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN FABRICANTE");
                        int codigoFabrB = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(1, codigoFabrB)) {
                            Conexiones.consultarFila(1, codigoFabrB);

                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 5:
                        System.out.println("DATOS DE LOS FABRICANTES");
                        Conexiones.consultarTodasFila(1);

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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                       ContenedorFabricante.mostrarDatosFabricante();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
                        break;

                    default:
                        System.out.println("OPCION INCORRECTA");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR ENTRADA INVALIDA,DEBE CONTENER SOLO NUMEROS");
                teclado.nextLine();

            }

        }
    }

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
                            System.out.println("INSERTA LA FECHA DE NACIMIENTO DEL CLIENTE");
                            String fechaNacimiento = teclado.nextLine();
                            System.out.println("INSERTA LA DIRECCION DE ENVIO DEL CLIENTE");
                            String direccionEnvio = teclado.nextLine();
                            System.out.println("INSERTA EL TELEFONO DEL CLIENTE");
                            String telCliente = teclado.nextLine();
                            System.out.println("INSERTA EL CORREO DEL CLIENTE");
                            String correoCli = teclado.nextLine();

                            Cliente c1 = new Cliente(codigoCli, nombreCli, fechaNacimiento, direccionEnvio, telCliente, correoCli);

                            Conexiones.insertarDatos(c1);
                            ContenedorCliente.agregarClienteNuevo(c1);
                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR CLIENTE");
                        int codigoCliFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(4, codigoCliFi)) {
                            Conexiones.actualizarFila(4, codigoCliFi);
                            System.out.println("CLIENTE ACTUALIZADO");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:

                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN CLIENTE");
                        int codigoCliEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(4, codigoCliEF)) {
                            Conexiones.actualizarFila(4, codigoCliEF);
                            System.out.println("CLIENTE ELIMINADO");
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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorCliente.mostrarDatosClientes();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
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
                            System.out.println("INSERTA LA FECHA DE ALTA DEL VENDEDOR");
                            String fechaAltaVen = teclado.nextLine();
                            System.out.println("INSERTA EL DOMICILIO DEL VENDEDOR");
                            String domicilioVen = teclado.nextLine();
                            System.out.println("INSERTA EL SALARIO DEL VENDEDOR");
                            double salarioVen = teclado.nextDouble();
                            teclado.nextLine();
                            System.out.println("INSERTA EL PORCENTAJE DEL VENDEDOR");
                            double porcentajeVen = teclado.nextDouble();
                            teclado.nextLine();

                            Vendedor ven = new Vendedor(codigoVen, nombreVen, fechaAltaVen, domicilioVen, salarioVen, porcentajeVen);

                            Conexiones.insertarDatos(ven);
                            ContenedorVendedor.agregarVendedorNuevo(ven);
                        }

                        break;

                    case 2:

                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN VENDEDOR");
                        int codigoVenFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(3, codigoVenFi)) {
                            Conexiones.actualizarFila(3, codigoVenFi);
                            System.out.println("VENDEDOR ACTUALIZADO");
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
                            System.out.println("VENDEDOR ELIMINADO");
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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorVendedor.mostrarDatosVendedor();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
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
                                System.out.println("INSERTA LA CATEGORIA DEL PRODUCTO");
                                String categoria = teclado.nextLine();
                                System.out.println("INSERTA LA DISPONIBILIDAD DEL PRODUCTO");
                                String disponibilidad = teclado.nextLine();
                                //Verificar
                                System.out.println("INSERTA EL PRECIO DE VENTA DEL PRODUCTO");
                                double precioVenta = teclado.nextDouble();
                                teclado.nextLine();

                                Producto p1 = new Producto(codigoPro, codidoFab, nombre, categoria, disponibilidad, precioVenta);

                                Conexiones.insertarDatos(p1);
                                ContenedorProducto.agregarProductoNuevo(p1);
                            } else {
                                System.out.println("EL CODIGO DEL FABRICANTE NO EXISTE");
                            }

                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR PRODUCTO");
                        int codigoProFi = teclado.nextInt();

                        if (Conexiones.verificarExistenciaCodigo(2, codigoProFi)) {
                            Conexiones.actualizarFila(2, codigoProFi);
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
                            System.out.println("PRODUCTO ELIMINADO");
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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorProducto.mostrarDatosProductos();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
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

    public static void subMenuPedido() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN PEDIDO...");
            Menu.historialSubMenus();
            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL PEDIDO");
                        int codigoPed = teclado.nextInt();
                        teclado.nextLine();
                        if (Conexiones.verificarExistenciaCodigo(5, codigoPed)) {
                            System.out.println("EL CODIGO INGRESADO YA EXISTE");
                        } else {
                            System.out.println("INSERTA EL CODIGO DEL VENDEDOR");
                            int codigoVen = teclado.nextInt();
                            teclado.nextLine();//Verificar
                            System.out.println("INSERTA EL CODIGO DEL CLIENTE");
                            int codigoCli = teclado.nextInt();
                            teclado.nextLine();//Verificar
                            System.out.println("INSERTA LA FECHA DE REALIZACION DEL PEDIDO");
                            String fechaRea = teclado.next();
                            teclado.nextLine();
                            System.out.println("INSERTA LA FECHA DE ENTREGA DEL PEDIDO");
                            String fechaEnt = teclado.next();
                            System.out.println("INSERTA EL ESTADO DEL PEDIDO");
                            String estado = teclado.next();

                            Pedido ped = new Pedido(codigoCli, codigoVen, codigoCli, fechaRea, fechaEnt, estado);

                            Conexiones.insertarDatos(ped);
                            ContenedorPedido.agregarPedidoNuevos(ped);
                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO PARA ACTUALIZAR UN PEDIDO");
                        int codigoPedFi = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedFi)) {
                            Conexiones.actualizarFila(5, codigoPedFi);
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }

                        break;

                    case 3:
                        System.out.println("INSERTAR CODIGO PARA ELIMINAR UN PEDIDO");
                        int codigoPedEF = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedEF)) {
                            Conexiones.actualizarFila(5, codigoPedEF);
                            System.out.println("PEDIDO ELIMINADO");
                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }
                        break;

                    case 4:
                        System.out.println("INSERTAR CODIGO PARA CONSULTAR UN PEDIDO");
                        int codigoPedCP = teclado.nextInt();
                        teclado.nextLine();

                        if (Conexiones.verificarExistenciaCodigo(5, codigoPedCP)) {
                            Conexiones.actualizarFila(5, codigoPedCP);

                        } else {
                            System.out.println("NO EXISTE EL CODIGO INGRESADO");
                        }
                        break;

                    case 5:
                        System.out.println("DATOS DE TODOS LOS PEDIDOS");
                        Conexiones.consultarTodasFila(5);
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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorPedido.mostrarDatosPedidos();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
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

    public static void subMenuLineaPedido() {
        boolean salir = false;
        while (!salir) {
            System.out.println("INICIANDO SESION EN LINEA DE PEDIDO...");
            Menu.historialSubMenus();

            try {
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:

                        System.out.println("INSERTA EL CODIGO DEL PEDIDO");
                        int codigoPed = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("INSERTA EL CODIGO DEL PRODUCTO");
                        int codigoPro = teclado.nextInt();
                        teclado.nextLine();
                        if (Conexiones.verificarExistenciaCodigo(5, codigoPed) && Conexiones.verificarExistenciaCodigo(2, codigoPro)) {
                            if (Conexiones.verificarExistenciaLineaPedido(codigoPed, codigoPro)) {
                                System.out.println("EL CODIGO INGRESADO YA EXISTE");
                            } else {
                                System.out.println("INSERTA LAS UNIDADES COMPRADAS");
                                int unidadesCompradas = teclado.nextInt();
                                teclado.nextLine();
                                
                                
                                

                                LineaPedido lp = new LineaPedido(codigoPed, codigoPro, unidadesCompradas );
                                
                                Conexiones.insertarDatos(lp);
                                ContenedorLineaPedido.agregarLineaPedidoNuevos(lp);
                            }

                        } else {
                            System.out.println("EL CODIGO INGRESADO NO EXISTE");
                        }

                        break;

                    case 2:
                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA ACTUALIZAR");
                        System.out.print("PEDIDO:");
                        int codigoPedFi = teclado.nextInt();
                        teclado.nextLine();

                        System.out.print("PRODUCTO:");
                        int codigoProFi = teclado.nextInt();
                        teclado.nextLine();

                        
                        if (Conexiones.verificarExistenciaLineaPedido(codigoPedFi, codigoPedFi)) {
                            Conexiones.actualizarLineaPedido(codigoPedFi, codigoProFi);
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }

                        break;

                    case 3:
                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA ELIMINAR LINEA DE PEDIDO");
                        System.out.print("PEDIDO:");
                        int codigoPedE = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("PRODUCTO:");
                        int codigoProE = teclado.nextInt();
                        teclado.nextLine();
                        boolean existeE = Conexiones.verificarExistenciaLineaPedido(codigoPedE, codigoProE);
                        if (existeE == true) {
                            Conexiones.eliminarLineaPedido(codigoPedE, codigoProE);
                            System.out.println("LINEA DE PEDIDO ELIMINADA");
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }

                        break;

                    case 4:
                        System.out.println("INSERTAR CODIGO DE PEDIDO Y CODIGO DE PRODUCTO PARA CONSULTAR UNA LINEA DE PEDIDO");
                        System.out.print("PEDIDO:");
                        int codigoPedCC = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("PRODUCTO:");
                        int codigoProECC = teclado.nextInt();
                        teclado.nextLine();
                        boolean existeECC = Conexiones.verificarExistenciaLineaPedido(codigoPedCC, codigoProECC);
                        if (existeECC == true) {
                            Conexiones.eliminarLineaPedido(codigoPedCC, codigoProECC);
                        } else {
                            System.out.println("NO EXISTE LA LINEA DE PEDIDO INGRESADA");
                        }
                        break;

                    case 5:
                        System.out.println("DATOS DE LINEAS DE PEDIDO");
                        Conexiones.consultarTodasFila(6);

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
                        System.out.println("Cargando datos insertados durante la ejecucion...");
                        ContenedorLineaPedido.mostrarLineaPedido();
                        break;
                        
                    case 15:

                        break;

                    case 16:
                        salir = true;
                        System.out.println("Saliendo del programa ...");
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

    public static void historialSubMenus() {
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
        System.out.println("Si desea ver los datos insertados durante la ejecucion pulse ");
        System.out.println("\t 14) VER DATOS INSERTADOS DURANTE LA EJECUCION");
        System.out.println("Si desea ver los informe multitabla pulse ");
        System.out.println("\t 15) VER INFORMES MULTITABLA");
        System.out.println("Si no quiere realizar ninguna operacion de gestion pulse ");
        System.out.println("\t 16) SALIR ");
    }
}
