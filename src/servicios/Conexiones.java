/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import contenedores.ContenedorCliente;
import contenedores.ContenedorFabricante;
import contenedores.ContenedorLineaPedido;
import contenedores.ContenedorPedido;
import contenedores.ContenedorProducto;
import contenedores.ContenedorVendedor;
import excepciones.YaImportadoException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static menu.Menu.teclado;
import modelos.Cliente;
import modelos.Fabricante;
import modelos.LineaPedido;
import modelos.Pedido;
import modelos.Producto;
import modelos.Vendedor;
import serviciosFicheros.InformesMultitablaFicheros;
import utils.Comprobaciones;
import utils.Configuracion;

/**
 * Clase que contiene metodos de conexion con la base de datos, asi como
 * diferentes metodos de gestion
 *
 * @author Natalia y Gabriela
 */
public class Conexiones {

    private static Connection con;

    /**
     * Metodo para establecer la conexion con la base de datos
     */
    public static void conexionEstablecida() {
        try {
            con = DriverManager.getConnection(Configuracion.url, Configuracion.user, Configuracion.password);
            System.out.println("Conexion exitosa");

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para cerrar la conexion con la base de datos
     */
    public static void cierreDeConexion() {
        try {
            con.close();
            System.out.println("Conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos en la base de datos dependiendo del tipo de
     * objeto que se recibe por parametro se insertan los datos en cada tabla
     * correspondiente
     *
     * @param o objeto que se inserta en la BD
     * @return devuelve true se ingresaron correctamente los datos, false si no
     */
    public static boolean insertarDatos(Object o) {
        try {

            //Establece la conexion con la base de datos
            /*Se verifica que el tipo de objeto que recibe  y dependiendo del  objeto se realiaza una 
             sentencia diferente para la BD*/
            if (o.getClass() == Fabricante.class) {

                //Si el objeto es de tipo fabricante, se inserta en la tabla fabricante
                PreparedStatement pst = con.prepareStatement("insert into fabricante values (?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Fabricante) o).getCodigo());
                pst.setString(2, ((Fabricante) o).getNombre());
                pst.setInt(3, ((Fabricante) o).getAnyoFundacion());
                pst.setString(4, ((Fabricante) o).getLugarSede());
                pst.setInt(5, ((Fabricante) o).getEmpleados());
                pst.setString(6, ((Fabricante) o).getSitioWeb());
                //Se ejecuta la insercion en la BD 
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (o.getClass() == Producto.class) {
                //Si el objeto es de tipo producto, se inserta en la tabla producto
                PreparedStatement pst = con.prepareStatement("insert into producto values (?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Producto) o).getCodigo());
                pst.setInt(2, ((Producto) o).getCodigoFabricante());
                pst.setString(3, ((Producto) o).getNombre());
                pst.setString(4, ((Producto) o).getCategoria());
                pst.setString(5, ((Producto) o).getDisponibilidad());
                pst.setDouble(6, ((Producto) o).getPrecioVenta());
                //Se ejecuta la insercion en la BD 
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (o.getClass() == Vendedor.class) {
                //Si el objeto es de tipo vendedor, se inserta en la tabla vendedor
                PreparedStatement pst = con.prepareStatement("insert into vendedor values (?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Vendedor) o).getCodigo());
                pst.setString(2, ((Vendedor) o).getNombre());
                pst.setString(3, ((Vendedor) o).getFechaAlta());
                pst.setString(4, ((Vendedor) o).getDomicilio());
                pst.setDouble(5, ((Vendedor) o).getSalario());
                pst.setDouble(6, ((Vendedor) o).getPorcentaje());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();
                return true;
            } else if (o.getClass() == Cliente.class) {
                //Si el objeto es de tipo cliente, se inserta en la tabla cliente
                PreparedStatement pst = con.prepareStatement("insert into cliente values (?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Cliente) o).getCodigo());
                pst.setString(2, ((Cliente) o).getNombre());
                pst.setString(3, ((Cliente) o).getFechaNacimiento());
                pst.setString(4, ((Cliente) o).getDireccionEnvio());
                pst.setString(5, ((Cliente) o).getTelefono());
                pst.setString(6, ((Cliente) o).getCorreo());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();
                return true;
            } else if (o.getClass() == Pedido.class) {
                //Si el objeto es de tipo pedido, se inserta en la tabla pedido
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Pedido) o).getCodigo());
                pst.setInt(2, ((Pedido) o).getCodigoVendedor());
                pst.setInt(3, ((Pedido) o).getCodigoCliente());
                pst.setString(4, ((Pedido) o).getFechaRealizacion());
                pst.setString(5, ((Pedido) o).getFechaEntrega());
                pst.setString(6, ((Pedido) o).getEstado());
                pst.setDouble(7, ((Pedido) o).getImporte());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();
                return true;
            } else if (o.getClass() == LineaPedido.class) {
                //Si el objeto es de tipo LineaPedido, se inserta en la tabla LineaPedido
                PreparedStatement pst = con.prepareStatement("insert into lineaPedido values (?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((LineaPedido) o).getCodigoPedido());
                pst.setInt(2, ((LineaPedido) o).getCodigoProducto());
                pst.setInt(3, ((LineaPedido) o).getUnidadesCompradas());
                pst.setDouble(4, ((LineaPedido) o).getSubTotal());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();
                return true;
            } else {
                System.out.println("Esa clase no existe");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Metodo para verifica si un código existe en la base de datos según la
     * clase indicada
     *
     * @param clase tipo de clase que le indico
     * @param codigo codigo por el cual se va buscar en la BD
     * @return true si el codigo existe, false si no existe
     */
    public static boolean verificarExistenciaCodigo(int clase, int codigo) {
        boolean existe = false;

        try {

            if (clase == 1) { //Si la clase es 1, se trata de un fabricante

                //Consulta para comparar el codigo recibido por parametro, se encuentra en la BD 
                PreparedStatement pst = con.prepareStatement("select codigo from fabricante where codigo=? ");
                //Asignamos valores a la consulta
                pst.setInt(1, codigo);
                //Se ejecuta la consulta y el resultado se guardar en rs
                ResultSet rs = pst.executeQuery();
                //Si devuelve 1 o mas de 1 resultado es porque el codigo existe en la BD 
                existe = rs.next();
                rs.close();
                pst.close();

            } else if (clase == 2) {//Si la clase es 2, se trata de un producto
                //Consulta para comparar el codigo recibido por parametro, se encuentra en la BD 
                PreparedStatement pst = con.prepareStatement("select codigo from producto where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                rs.close();
                pst.close();
            } else if (clase == 3) {//Si la clase es 3, se trata de un vendedor
                //Consulta para comparar el codigo recibido por parametro, se encuentra en la BD 
                PreparedStatement pst = con.prepareStatement("select codigo from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                rs.close();
                pst.close();

            } else if (clase == 4) {//Si la clase es 4, se trata de un cliente
                //Consulta para comparar el codigo recibido por parametro, se encuentra en la BD 
                PreparedStatement pst = con.prepareStatement("select codigo from cliente where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                rs.close();
                pst.close();
            } else if (clase == 5) {//Si la clase es 5, se trata de un pedido
                PreparedStatement pst = con.prepareStatement("select codigo from pedido where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                rs.close();
                pst.close();
            } else {
                System.out.println("Esa clase no existe");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existe;
    }

    /**
     * Metodo para verifica si existe una linea de pedido en la base de datos en
     * funcion del codigo del pedido y el codigo de productos
     *
     * @param codPed codigo del pedido
     * @param codPro codigo del producto
     * @return true si existe la linea de pedido existe, false si no existe
     */
    public static boolean verificarExistenciaLineaPedido(int codPed, int codPro) {
        boolean existe = false;
        try {

            //Consulta para comparar el codigo recibido por parametro, se encuentra en la BD 
            PreparedStatement pst = con.prepareStatement("select codigoPedido, codigoProducto from lineaPedido where codigoPedido=? and codigoProducto=? ");
            pst.setInt(1, codPed);
            pst.setInt(2, codPro);
            //Se ejecuta la consulta y el resultado se guardar en la BD

            ResultSet rs = pst.executeQuery();
            //Si devuelve 1 o mas de 1 resultado es porque el codigo existe en la BD 
            existe = rs.next();
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;

    }

    /**
     * Metodo que actualiza una fila de la base de datos segun el tipo
     *
     * @param clase indica la tabla a actualizar
     * @param codigo codigo del registro a acatualizar
     * @return si se actualizo correctamente devuleve un true, de lo contrario
     * false
     */
    public static boolean actualizarFila(int clase, int codigo) {

        try {
            //Segun el valor ingresado, se actaliza una tabla distinta
            if (clase == 1) {
                //Actualiza fabricante
                System.out.println("INSERTA NUEVO NOMBRE DEL FABRICANTE");
                String nombreFabri = teclado.nextLine();
                //Valida caracteres 
                if (!Comprobaciones.comprobarStringValido(nombreFabri)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO AÑO DE FUNDACION DEL FABRICANTE");
                int anyoFundacionFabri = teclado.nextInt();
                teclado.nextLine();
                //Valida año
                if (!Comprobaciones.comprobarAnyo(anyoFundacionFabri)) {
                    return false;
                }

                System.out.println("INSERTA NUEVO LUGAR SEDE DEL FABRICANTE ");
                String lugarSedeFabri = teclado.nextLine();
                //Valida caracteres 
                if (!Comprobaciones.comprobarCarcateresDireccion(lugarSedeFabri)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO NUMERO DE EMPLEADOS DEL FABRICANTE");
                int empleadosFabri = teclado.nextInt();
                teclado.nextLine();

                System.out.println("INSERTA NUEVO SITIO WEB DEL FABRICANTE");
                String sitioWebFabri = teclado.next();
                //Valida caracteres permitidos
                if (!Comprobaciones.comprobarStringValido(sitioWebFabri)) {
                    return false;
                }
                PreparedStatement pst = con.prepareStatement("update fabricante set nombre= ?,"
                        + "anyoFundacion=?,lugarSede=?,empleados=?,sitioWeb=? where codigo=?");
                pst.setString(1, nombreFabri);
                pst.setInt(2, anyoFundacionFabri);
                pst.setString(3, lugarSedeFabri);
                pst.setInt(4, empleadosFabri);
                pst.setString(5, sitioWebFabri);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (clase == 2) {

                //Actualiza producto
                System.out.println("INSERTA NUEVO CODIGO DEL FABRICANTE");
                int codidoFab = teclado.nextInt();
                //Valida el codigo
                if (!verificarExistenciaCodigo(1, codidoFab)) {
                    System.out.println("EL CODIGO INGRESADO NO EXISTE EN LA BASE DE DATOS");
                    return false;
                }
                teclado.nextLine();
                System.out.println("INSERTA NUEVO NOMBRE DEL PRODUCTO");
                String nombre = teclado.nextLine();
                //Valida caracteres 
                if (!Comprobaciones.comprobarStringValido(nombre)) {
                    return false;
                }
                System.out.println("INSERTA NUEVA CATEGORIA DEL PRODUCTO");
                String categoria = teclado.nextLine();
                //Valida caracteres 
                if (!Comprobaciones.comprobarStringValido(categoria)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO DISPONIBILIDAD DEL PRODUCTO: DISPONIBLE/POCAS UNIDADES/NO DISPONIBLE");
                String disponibilidad = teclado.nextLine();
                //Valida disponibilidad
                if (!Comprobaciones.verificarDisponibilidad(disponibilidad)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO PRECIO DE VENTA DEL PRODUCTO");
                double precioVenta = teclado.nextDouble();
                teclado.nextLine();
                //Valida precio
                if (!Comprobaciones.validarNumero(precioVenta)) {
                    return false;
                }

                PreparedStatement pst = con.prepareStatement("update producto set codigoFabricante=?,nombre=?,categoria=?,"
                        + "disponibilidad=?,precioVenta=? where codigo=? ");

                pst.setInt(1, codidoFab); //Verificar el codigo exista
                pst.setString(2, nombre);
                pst.setString(3, categoria);
                pst.setString(4, disponibilidad);//Verificar en vector
                pst.setDouble(5, precioVenta);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (clase == 3) {

                //Actualiza vendedor
                System.out.println("INSERTA NUEVO NOMBRE DEL VENDEDOR");
                String nombreVen = teclado.nextLine();
                //Valida caracteres
                if (!Comprobaciones.comprobarStringValido(nombreVen)) {
                    return false;
                }
                System.out.println("INSERTA NUEVA FECHA DE ALTA DEL VENDEDOR YYYY-MM-DD");
                String fechaAltaVen = teclado.nextLine();
                //Valida formato de fecha
                if (Comprobaciones.comprobarFecha(fechaAltaVen)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO DOMICILIO DEL VENDEDOR");
                String domicilioVen = teclado.nextLine();
                //Valida caracteres
                if (!Comprobaciones.comprobarCarcateresDireccion(domicilioVen)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO SALARIO DEL VENDEDOR");
                double salarioVen = teclado.nextDouble();
                teclado.nextLine();
                if (!Comprobaciones.validarNumero(salarioVen)) {
                    return false;
                }

                System.out.println("INSERTA NUEVO PORCENTAJE DEL VENDEDOR");
                double porcentajeVen = teclado.nextDouble();

                teclado.nextLine();

                PreparedStatement pst = con.prepareStatement("update vendedor set nombre=?,fechaAlta=?,domicilio=?,"
                        + "salario=?,porcentaje=? where codigo=? ");

                pst.setString(1, nombreVen);
                pst.setString(2, fechaAltaVen);
                pst.setString(3, domicilioVen);
                pst.setDouble(4, salarioVen);
                pst.setDouble(5, porcentajeVen);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (clase == 4) {
                //Actualiza cliente
                System.out.println("INSERTA NUEVO  NOMBRE DEL CLIENTE");
                String nombreCli = teclado.nextLine();
                //Valida caracteres
                if (!Comprobaciones.comprobarStringValido(nombreCli)) {
                    return false;
                }
                System.out.println("INSERTA NUEVA FECHA DE NACIMIENTO DEL CLIENTE YYYY-MM-DD");
                String fechaNacimientoCli = teclado.nextLine();
                //Valida fecha
                if (Comprobaciones.comprobarFecha(fechaNacimientoCli)) {
                    return false;
                }
                System.out.println("INSERTA NUEVA DIRECCION DE ENVIO DEL CLIENTE");
                String direccionEnvio = teclado.nextLine();
                //Valida caracteres
                if (!Comprobaciones.comprobarCarcateresDireccion(direccionEnvio)) {
                    return false;
                }
                System.out.println("INSERTA NUEVO TELEFONO DEL CLIENTE");
                String telCliente = teclado.nextLine();

                System.out.println("INSERTA NUEVO CORREO DEL CLIENTE");
                String correoCli = teclado.nextLine();
                //Valida caracteres
                if (!Comprobaciones.comprobarStringValido(correoCli)) {
                    return false;
                }
                PreparedStatement pst = con.prepareStatement("update cliente set nombre=?,fechaNacimiento=?,"
                        + "direccionEnvio=?,telefono=?,correo=? where codigo=? ");

                pst.setString(1, nombreCli);
                pst.setString(2, fechaNacimientoCli);
                pst.setString(3, direccionEnvio);
                pst.setString(4, telCliente);
                pst.setString(5, correoCli);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
                return true;
            } else if (clase == 5) {
                //Actualiza un pedido
                System.out.println("INSERTA NUEVO CODIGO DEL VENDEDOR");
                int codigoVen = teclado.nextInt();
                if (!verificarExistenciaCodigo(3, codigoVen)) {
                    System.out.println("EL CODIGO NO EXISTE EN LA BASE DE DATOS");
                    return false;
                }
                teclado.nextLine();

                System.out.println("INSERTA NUEVO CODIGO DEL CLIENTE");
                int codigoCli = teclado.nextInt();
                if (!verificarExistenciaCodigo(4, codigoCli)) {
                    System.out.println("EL CODIGO NO EXISTE EN LA BASE DE DATOS");
                    return false;
                }
                teclado.nextLine();

                System.out.println("INSERTA NUEVA FECHA DE ENTREGA DEL PEDIDO YYYY-MM-DD");
                String fechaEnt = teclado.nextLine();
                if (Comprobaciones.comprobarFecha(fechaEnt)) {
                    return false;
                }
                System.out.println("INSERTA EL NUEVO ESTADO DEL PEDIDO: REALIZADO/CANCELADO/ENVIADO/ENTREGADO/DEVUELTO");
                String estado = teclado.nextLine();
                if (!Comprobaciones.verificarEstado(estado)) {
                    return false;
                }

                PreparedStatement pst = con.prepareStatement("update pedido set codigoVendedor=?,codigoCliente=?,fechaEntrega =?,"
                        + "estado=? where codigo=? ");

                pst.setInt(1, codigoVen);//Verificar codigo vendedor
                pst.setInt(2, codigoCli);//Verificar codigo cliente
                pst.setString(3, fechaEnt);//Verificar fecha
                pst.setString(4, estado);//Verificar estado
                pst.setInt(5, codigo);
                pst.executeUpdate();
                pst.close();
                return true;
            } else {
                System.out.println("Clase no encontrada");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Metodo que actualiza una linea de pedido
     *
     * @param codigoped codigo del pedido
     * @param codpro codigo del producto
     */
    public static boolean actualizarLineaPedido(int codigoped, int codpro) {
        try {

            System.out.println("INSERTA LAS UNIDADES COMPRADAS");
            int unidadesCompradas = teclado.nextInt();
            teclado.nextLine();
            if (unidadesCompradas <= 0) {
                return false;
            }
            double precio = Conexiones.precioProducto(codpro);
            double subTotal = unidadesCompradas * precio;

            PreparedStatement pst = con.prepareStatement(
                    "update lineaPedido set unidadesCompradas=?, subTotal=? where codigoPedido=? and codigoProducto=?"
            );

            pst.setInt(1, unidadesCompradas);
            pst.setDouble(2, subTotal);
            pst.setInt(3, codigoped);
            pst.setInt(4, codpro);

            pst.executeUpdate();
            pst.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Metodo que elimina una fila en la base de datos dependiendo del tipo
     *
     * @param clase tipo de clase que le indico
     * @param codigo codigo por el cual se va buscar en la BD
     */
    public static void eliminarFila(int clase, int codigo) {

        try {

            if (clase == 1) {
                //Elimina un fabricante
                PreparedStatement pst = con.prepareStatement("delete from fabricante where codigo=?");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 2) {
                //Elimina un producto
                PreparedStatement pst = con.prepareStatement("delete from producto  where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 3) {
                //Elimina un vendedor
                PreparedStatement pst = con.prepareStatement("delete from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 4) {
                //Elimina un cliente
                PreparedStatement pst = con.prepareStatement("delete from cliente where codigo=? ");

                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 5) {
                //Elimina un pedido
                PreparedStatement pst = con.prepareStatement("delete from pedido where codigo=? ");

                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else {
                System.out.println("Clase no encontrada");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que elimina una linea pedido en la base de datos
     *
     * @param codPed codigo de pedido
     * @param codPro codigo del producto
     */
    public static void eliminarLineaPedido(int codPed, int codPro) {
        try {

            PreparedStatement pst = con.prepareStatement("delete from lineaPedido  where codigoPedido=? AND codigoProducto=? ");

            pst.setInt(1, codPed);
            pst.setInt(2, codPro);
            pst.executeUpdate();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que consulta una fila de la base de datos dependiento del tipo
     *
     * @param clase tipo de clase que le indico
     * @param codigo codigo por el cual va a buscar en la base de datos
     */
    public static void consultarFila(int clase, int codigo) {

        try {

            if (clase == 1) {
                PreparedStatement pst = con.prepareStatement("select * from fabricante where codigo=?");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Anyo fundacion: " + rs.getInt("anyoFundacion")
                            + " | Lugar sede: " + rs.getString("lugarSede")
                            + " | Empleados: " + rs.getInt("empleados")
                            + " | Siti web: " + rs.getString("sitioWeb")
                    );
                }
                rs.close();
                pst.close();
            } else if (clase == 2) {
                PreparedStatement pst = con.prepareStatement("select * from producto  where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Codigo fabricante: " + rs.getInt("codigoFabricante")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Categoria: " + rs.getString("categoria")
                            + " | Disponibilidad: " + rs.getString("disponibilidad")
                            + " | Precio venta: " + rs.getDouble("precioVenta")
                    );
                }
                rs.close();
                pst.close();
            } else if (clase == 3) {
                PreparedStatement pst = con.prepareStatement("select * from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Fecha alta: " + rs.getString("fechaAlta")
                            + " | Domicilio: " + rs.getString("domicilio")
                            + " | Salario: " + rs.getDouble("salario")
                            + " | Porcentaje: " + rs.getDouble("porcentaje")
                    );
                }
                rs.close();
                pst.close();
            } else if (clase == 4) {
                PreparedStatement pst = con.prepareStatement("select * from cliente where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Fecha nacimiento: " + rs.getString("fechaNacimiento")
                            + " | Direccion de envio: " + rs.getString("direccionEnvio")
                            + " | Telefono: " + rs.getString("telefono")
                            + " | Correo: " + rs.getString("correo")
                    );
                }
                rs.close();
                pst.close();
            } else if (clase == 5) {
                PreparedStatement pst = con.prepareStatement("select * from pedido where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Codigo vendedor: " + rs.getInt("codigoVendedor")
                            + " | Codigo cliente: " + rs.getInt("codigoCliente")
                            + " | Fecha de realizacion: " + rs.getString("fechaRealizacion")
                            + " | Fecha de entrega: " + rs.getString("fechaEntrega")
                            + " | Estado: " + rs.getString("estado")
                            + " | Importe: " + rs.getDouble("importe")
                    );

                }
                rs.close();
                pst.close();
            } else {
                System.out.println("Clase no encontrada");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para consultar una linea de pedido de la base de datos
     *
     * @param codPed codigo del pedido
     * @param codPro codigo de producto
     */
    public static void consultarLineaPedido(int codPed, int codPro) {
        try {

            PreparedStatement pst = con.prepareStatement("select * from lineaPedido  where codigoPedido=? and codigoProducto=? ");

            pst.setInt(1, codPed);
            pst.setInt(2, codPro);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "Codigo de pedido: " + rs.getInt("codigoPedido")
                        + " | Codigo de producto: " + rs.getInt("codigoProducto")
                        + " | Unidades compradas: " + rs.getInt("unidadesCompradas")
                        + " | Subtotal: " + rs.getDouble("subTotal")
                );
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que consulta todos los registro de una tabla en la base de datos
     * dependiendo del tipo de clase indicada
     *
     * @param clase tipo de clase que le indiquemos
     */
    public static void consultarTodasFila(int clase) {

        try {

            if (clase == 1) {
                //Consulta todas las filas de fabricantes
                PreparedStatement pst = con.prepareStatement("SELECT * FROM fabricante ORDER BY codigo ASC");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Anyo fundacion: " + rs.getInt("anyoFundacion")
                            + " | Lugar sede: " + rs.getString("lugarSede")
                            + " | Empleados: " + rs.getInt("empleados")
                            + " | Sitio web: " + rs.getString("sitioWeb")
                    );

                }
                rs.close();
                pst.close();

            } else if (clase == 2) {
                //Consulta todas las filas de productos
                PreparedStatement pst = con.prepareStatement("SELECT * FROM producto ORDER BY codigo ASC");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Codigo fabricante: " + rs.getInt("codigoFabricante")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Categoria: " + rs.getString("categoria")
                            + " | Disponibilidad: " + rs.getString("disponibilidad")
                            + " | Precio venta: " + rs.getDouble("precioVenta")
                    );

                }
                rs.close();
                pst.close();
            } else if (clase == 3) {
                //Consulta todas las filas de vendedores
                PreparedStatement pst = con.prepareStatement("SELECT * FROM vendedor ORDER BY codigo ASC ");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Fecha alta: " + rs.getString("fechaAlta")
                            + " | Domicilio: " + rs.getString("domicilio")
                            + " | Salario: " + rs.getDouble("salario")
                            + " | Porcentaje: " + rs.getDouble("porcentaje")
                    );

                }
                rs.close();
                pst.close();
            } else if (clase == 4) {
                //Consulta todas las filas de clientes
                PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente ORDER BY codigo ASC ");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                            + " | Fecha nacimiento: " + rs.getString("fechaNacimiento")
                            + " | Direccion de envio: " + rs.getString("direccionEnvio")
                            + " | Telefono: " + rs.getString("telefono")
                            + " | Correo: " + rs.getString("correo")
                    );

                }
                rs.close();
                pst.close();
            } else if (clase == 5) {
                //Consulta todas las filas de pedidos
                PreparedStatement pst = con.prepareStatement("SELECT * FROM pedido ORDER BY codigo ASC");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Codigo vendedor: " + rs.getInt("codigoVendedor")
                            + " | Codigo cliente: " + rs.getInt("codigoCliente")
                            + " | Fecha de realizacion: " + rs.getString("fechaRealizacion")
                            + " | Fecha de entrega: " + rs.getString("fechaEntrega")
                            + " | Estado: " + rs.getString("estado")
                            + " | Importe: " + rs.getDouble("importe")
                    );

                }
                rs.close();
                pst.close();
            } else if (clase == 6) {
                //Consulta todas las filas de linea de pedido
                PreparedStatement pst = con.prepareStatement("SELECT * FROM lineaPedido ORDER BY codigoPedido ASC");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo de pedido: " + rs.getInt("codigoPedido")
                            + " | Codigo de producto: " + rs.getInt("codigoProducto")
                            + " | Unidades compradas: " + rs.getInt("unidadesCompradas")
                            + " | Subtotal: " + rs.getDouble("subTotal")
                    );

                }
                rs.close();
                pst.close();
            } else {
                System.out.println("Clase no encontrada");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insersarDatosContenedorFabricante() {

        try {
            //Realiza la consulta
            PreparedStatement pst = con.prepareStatement("SELECT * FROM fabricante");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //Crea el objeto
                Fabricante f1 = new Fabricante(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("anyoFundacion"), rs.getString("lugarSede"), rs.getInt("empleados"),
                        rs.getString("sitioWeb"));
                //Inserta en el contenedor
                ContenedorFabricante.agregarFabricante(f1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insertarDatosContenedorVendedor() {
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * FROM vendedor");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vendedor v1 = new Vendedor(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("fechaAlta"), rs.getString("domicilio"), rs.getDouble("salario"), rs.getDouble("porcentaje"));

                ContenedorVendedor.agregarVendedor(v1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insertarDatosContenedorClientes() {
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente c1 = new Cliente(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("fechaNacimiento"), rs.getString("direccionEnvio"), rs.getString("telefono"), rs.getString("correo"));

                ContenedorCliente.agregarCliente(c1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insertarDatosContenedoresProductos() {
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * FROM producto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Producto pro1 = new Producto(rs.getInt("codigo"), rs.getInt("codigoFabricante"), rs.getString("nombre"), rs.getString("categoria"), rs.getString("disponibilidad"), rs.getDouble("precioVenta"));

                ContenedorProducto.agregarProducto(pro1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insertarDatosContenedoresPedidos() {
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * FROM pedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Pedido p1 = new Pedido(rs.getInt("codigoVendedor"), rs.getInt("codigoCliente"), rs.getString("fechaRealizacion"), rs.getString("fechaEntrega"), rs.getString("estado"), rs.getDouble("importe"));

                ContenedorPedido.agregarPedido(p1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para insertar datos desde la base de datos a un contenedor
     */
    public static void insertarDatosContenedoresLP() {
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * FROM lineaPedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                LineaPedido LP = new LineaPedido(rs.getInt("codigoPedido"), rs.getInt("codigoProducto"), rs.getInt("unidadesCompradas"), rs.getDouble("subTotal"));

                ContenedorLineaPedido.agregarLineaPedido(LP);

            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para obtener el precio de un producto a partir de su codigo
     *
     * @param codigo codigo del producto a consultar
     * @return precio de venta del producto
     */
    public static double precioProducto(int codigo) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT precioVenta FROM producto WHERE codigo = ?");

            pst.setInt(1, codigo);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double precio = rs.getDouble("precioVenta");
                rs.close();
                pst.close();
                return precio;
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    /**
     * Metodo para calcular el importe final de un pedido sumnado todas sus
     * lineas de pedido
     *
     * @param codigoPed codigo del pedido que se quiere sabe el importe
     * @return precio del importe final
     */
    public static double importeFinal(int codigoPed) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT sum(subTotal) AS total FROM lineaPedido where codigoPedido=?");

            pst.setInt(1, codigoPed);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double importeFinal = rs.getDouble("total");
                rs.close();
                pst.close();
                return importeFinal;
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    /**
     * Metodo para obtener el siguiente codigo de pedido de forma incremental
     *
     * @return el siguiente codigo disponible
     */
    public static int codigoPedido() {

        try {

            PreparedStatement pst = con.prepareStatement("SELECT  (max(codigo) + 1) as codigoPedido from pedido");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int codigoPedido = rs.getInt("codigoPedido");
                rs.close();
                pst.close();
                return codigoPedido;
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    /**
     * Consultas realizada a la base de datos que devuelte un reuslta y se
     * almacena en un fichero dependiendo del tipo de consulta a realizar
     *
     * @param opcion consulta a realizar
     */
    public static void informesMultitabla(int opcion) {
        try {

            switch (opcion) {
                case 1:

                    String consulta1 = "SELECT f.nombre AS fabricante, f.lugarSede, f.sitioWeb,p.nombre AS producto, p.categoria, p.disponibilidad "
                            + " FROM fabricante f LEFT JOIN producto p ON f.codigo = p.codigoFabricante ORDER BY f.nombre ASC";
                    PreparedStatement pst = con.prepareStatement(consulta1);
                    ResultSet rs = pst.executeQuery();

                    String consultasF1 = "Consulta 1";
                    InformesMultitablaFicheros.guardarLinea(consultasF1);

                    while (rs.next()) {

                        String linea = "fabricante: " + rs.getString("fabricante")
                                + " | lugarSede: " + rs.getString("lugarSede")
                                + " | sitioWeb: " + rs.getString("sitioWeb")
                                + " | producto: " + rs.getString("producto")
                                + " | categoria: " + rs.getString("categoria")
                                + " | disponibilidad: " + rs.getString("disponibilidad");

                        System.out.println(linea);

                        String linea1 = rs.getString("fabricante") + ";"
                                + rs.getString("lugarSede") + ";"
                                + rs.getString("sitioWeb") + ";"
                                + rs.getString("producto") + ";"
                                + rs.getString("categoria") + ";"
                                + rs.getString("disponibilidad");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs.close();
                    pst.close();
                    break;
                case 2:
                    String consulta3 = "SELECT p.nombre AS producto, p.categoria AS categoria, c.nombre AS cliente,SUM(lp.unidadesCompradas) AS unidadesTotales "
                            + " FROM producto p JOIN lineaPedido lp ON p.codigo = lp.codigoProducto JOIN pedido pe ON lp.codigoPedido = pe.codigo "
                            + " JOIN cliente c ON pe.codigoCliente = c.codigo GROUP BY p.nombre, p.categoria, c.nombre ORDER BY p.nombre ASC";
                    PreparedStatement pst1 = con.prepareStatement(consulta3);
                    ResultSet rs1 = pst1.executeQuery();
                    String consultasF3 = "Consulta 3";
                    InformesMultitablaFicheros.guardarLinea(consultasF3);

                    while (rs1.next()) {
                        String linea = "Producto: " + rs1.getString("producto")
                                + " | categoria: " + rs1.getString("categoria")
                                + " | cliente: " + rs1.getString("cliente")
                                + " | unidadesTotales: " + rs1.getInt("unidadesTotales");

                        System.out.println(linea);
                        String linea1 = rs1.getString("producto") + ";"
                                + rs1.getString("categoria") + ";"
                                + rs1.getString("cliente") + ";"
                                + rs1.getInt("unidadesTotales");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs1.close();
                    pst1.close();
                    break;
                case 3:
                    String consulta4 = "SELECT c.nombre AS cliente, c.direccionEnvio AS direccion, p.nombre AS producto,SUM(lp.unidadesCompradas) AS unidadesTotales "
                            + " FROM cliente c JOIN pedido pe ON c.codigo = pe.codigoCliente JOIN lineaPedido lp ON pe.codigo = lp.codigoPedido "
                            + " JOIN producto p ON lp.codigoProducto = p.codigo GROUP BY c.nombre, c.direccionEnvio, p.nombre ORDER BY c.nombre ASC";
                    PreparedStatement pst2 = con.prepareStatement(consulta4);
                    ResultSet rs2 = pst2.executeQuery();
                    String consultasF4 = "Consulta 4";
                    InformesMultitablaFicheros.guardarLinea(consultasF4);
                    while (rs2.next()) {
                        String linea = "cliente: " + rs2.getString("cliente")
                                + " | direccionEnvio: " + rs2.getString("direccion")
                                + " | producto: " + rs2.getString("producto")
                                + " | unidadesTotales: " + rs2.getInt("unidadesTotales");

                        System.out.println(linea);
                        String linea1 = rs2.getString("cliente") + ";"
                                + rs2.getString("direccion") + ";"
                                + rs2.getString("producto") + ";"
                                + rs2.getInt("unidadesTotales");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs2.close();
                    pst2.close();
                    break;
                case 4:
                    String consulta5 = "SELECT f.nombre AS fabricante, f.lugarSede AS lugar, f.sitioWeb AS web,c.nombre AS cliente, c.fechaNacimiento AS nacimiento,"
                            + " COUNT(pe.codigo) AS productosComprados FROM fabricante f JOIN producto p ON f.codigo = p.codigoFabricante"
                            + " JOIN lineaPedido lp ON p.codigo = lp.codigoProducto JOIN pedido pe ON lp.codigoPedido = pe.codigo"
                            + " JOIN cliente c ON pe.codigoCliente = c.codigo GROUP BY f.nombre, f.lugarSede, f.sitioWeb, c.nombre, c.fechaNacimiento"
                            + " ORDER BY f.nombre ASC";
                    PreparedStatement pst3 = con.prepareStatement(consulta5);
                    ResultSet rs3 = pst3.executeQuery();
                    String consultasF5 = "Consulta 5";
                    InformesMultitablaFicheros.guardarLinea(consultasF5);
                    while (rs3.next()) {
                        String linea = "fabricante: " + rs3.getString("fabricante")
                                + " | lugarSede: " + rs3.getString("lugar")
                                + " | sitioWeb: " + rs3.getString("web")
                                + " | cliente: " + rs3.getString("cliente")
                                + " | fechaNacimiento: " + rs3.getString("nacimiento")
                                + " | productosComprados: " + rs3.getInt("productosComprados");

                        System.out.println(linea);
                        String linea1 = rs3.getString("fabricante") + ";"
                                + rs3.getString("lugar") + ";"
                                + rs3.getString("web") + ";"
                                + rs3.getString("cliente") + ";"
                                + rs3.getString("nacimiento") + ";"
                                + rs3.getInt("productosComprados");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs3.close();
                    pst3.close();

                    break;
                case 5:
                    String consulta6 = "SELECT v.nombre AS vendedor, v.fechaAlta AS fechaAlta, p.fechaRealizacion AS fechaRea, p.estado AS estado,"
                            + " p.importe,(p.importe * v.porcentaje) AS comisionPedido FROM vendedor v INNER JOIN pedido p ON v.codigo = p.codigoVendedor "
                            + " ORDER BY v.nombre ASC";
                    PreparedStatement pst4 = con.prepareStatement(consulta6);
                    ResultSet rs4 = pst4.executeQuery();
                    String consultasF6 = "Consulta 6";
                    InformesMultitablaFicheros.guardarLinea(consultasF6);

                    while (rs4.next()) {
                        String linea = "vendedor: " + rs4.getString("vendedor")
                                + " | fechaAlta: " + rs4.getString("fechaAlta")
                                + " | fechaRealizacion: " + rs4.getString("fechaRea")
                                + " | estado: " + rs4.getString("estado")
                                + " | importe: " + rs4.getDouble("importe")
                                + " | comisionPedido: " + rs4.getDouble("comisionPedido");

                        System.out.println(linea);
                        String linea1 = rs4.getString("vendedor")
                                + ";" + rs4.getString("fechaAlta")
                                + ";" + rs4.getString("fechaRea")
                                + ";" + rs4.getString("estado")
                                + ";" + rs4.getDouble("importe")
                                + ";" + rs4.getDouble("comisionPedido");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs4.close();
                    pst4.close();

                    break;
                case 6:
                    String consulta7 = "SELECT p.nombre AS producto, p.categoria AS categoria,SUM(lp.unidadesCompradas) AS totalUnidades,"
                            + " SUM(lp.subTotal) AS importeTotal,COUNT(lp.codigoPedido) AS numeroPedidos FROM producto p "
                            + " JOIN lineaPedido lp ON p.codigo = lp.codigoProducto GROUP BY p.codigo, p.nombre, p.categoria "
                            + " HAVING COUNT(lp.codigoPedido) > 5 ORDER BY totalUnidades DESC";
                    PreparedStatement pst5 = con.prepareStatement(consulta7);
                    ResultSet rs5 = pst5.executeQuery();
                    String consultasF7 = "Consulta 7";
                    InformesMultitablaFicheros.guardarLinea(consultasF7);
                    while (rs5.next()) {
                        String linea = "producto: " + rs5.getString("producto")
                                + " | categoria: " + rs5.getString("categoria")
                                + " | totalUnidades: " + rs5.getInt("totalUnidades")
                                + " | importeTotal: " + rs5.getDouble("importeTotal")
                                + " | numeroPedidos: " + rs5.getInt("numeroPedidos");

                        System.out.println(linea);
                        String linea1 = rs5.getString("producto")
                                + ";" + rs5.getString("categoria")
                                + ";" + rs5.getInt("totalUnidades")
                                + ";" + rs5.getDouble("importeTotal")
                                + ";" + rs5.getInt("numeroPedidos");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs5.close();
                    pst5.close();

                    break;
                case 7:
                    String consulta8 = "SELECT DISTINCT f.nombre AS nombre, f.lugarSede AS sede, f.sitioWeb AS web FROM fabricante f "
                            + " JOIN producto p ON f.codigo = p.codigoFabricante LEFT JOIN lineaPedido lp ON p.codigo = lp.codigoProducto "
                            + " WHERE lp.codigoProducto IS NULL ORDER BY f.nombre ASC";
                    PreparedStatement pst6 = con.prepareStatement(consulta8);
                    ResultSet rs6 = pst6.executeQuery();
                    String consultasF8 = "Consulta 8";
                    InformesMultitablaFicheros.guardarLinea(consultasF8);
                    while (rs6.next()) {

                        String linea = "Fabricante: " + rs6.getString("nombre")
                                + " | sede: " + rs6.getString("sede")
                                + " | web: " + rs6.getString("web");

                        System.out.println(linea);
                        String linea1 = rs6.getString("nombre")
                                + ";" + rs6.getString("sede")
                                + ";" + rs6.getString("web");
                        InformesMultitablaFicheros.guardarLinea(linea1);

                    }
                    rs6.close();
                    pst6.close();

                    break;
                case 8:
                    String consulta9 = "SELECT v.nombre AS vendedor,COUNT(p.codigo) AS totalPedidos,SUM(p.importe) AS importeTotal, "
                            + " ROUND(AVG(p.importe),2) AS promedioImporte FROM vendedor v JOIN pedido p ON v.codigo = p.codigoVendedor "
                            + " WHERE p.estado = 'entregado' GROUP BY v.nombre ORDER BY importeTotal DESC";
                    PreparedStatement pst7 = con.prepareStatement(consulta9);
                    ResultSet rs7 = pst7.executeQuery();
                    String consultasF9 = "Consulta 9";
                    InformesMultitablaFicheros.guardarLinea(consultasF9);
                    while (rs7.next()) {
                        String linea = "vendedor: " + rs7.getString("vendedor")
                                + " | totalPedidos: " + rs7.getInt("totalPedidos")
                                + " | importeTotal: " + rs7.getDouble("importeTotal")
                                + " | promedioImporte: " + rs7.getDouble("promedioImporte");

                        System.out.println(linea);
                        String linea1 = rs7.getString("vendedor")
                                + ";" + rs7.getInt("totalPedidos")
                                + ";" + rs7.getDouble("importeTotal")
                                + ";" + rs7.getDouble("promedioImporte");
                        InformesMultitablaFicheros.guardarLinea(linea1);
                    }
                    rs7.close();
                    pst7.close();

                    break;

                default:
                    throw new AssertionError();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Consulta información de pedidos de un cliente específico y la exporta a
     * fichero
     *
     * @param codigoCliente codigo de cliente que se desea consultar
     */
    public static void consulta2(int codigoCliente) {
        try {

            String consulta1 = "  SELECT p.fechaRealizacion AS fechaRea, p.fechaEntrega AS fechaEntr,p.estado AS estado, p.importe AS importe,"
                    + " pr.nombre AS producto, lp.unidadesCompradas, lp.subTotal FROM pedido p JOIN lineaPedido lp ON p.codigo = lp.codigoPedido"
                    + " JOIN producto pr ON lp.codigoProducto = pr.codigo WHERE p.codigoCliente = ? ORDER BY fechaRea ASC";
            PreparedStatement pst = con.prepareStatement(consulta1);
            pst.setInt(1, codigoCliente);
            ResultSet rs = pst.executeQuery();
            String consultasF2 = "Consulta 2";
            InformesMultitablaFicheros.guardarLinea(consultasF2);
            while (rs.next()) {

                String linea = rs.getDate("fechaRea") + ";"
                        + rs.getDate("fechaEntr") + ";"
                        + rs.getString("estado") + ";"
                        + rs.getDouble("importe") + ";"
                        + rs.getString("producto") + ";"
                        + rs.getInt("unidadesCompradas") + ";"
                        + rs.getDouble("subTotal");

                System.out.println(linea);
                String linea1 = rs.getDate("fechaRea") + ";"
                        + rs.getDate("fechaEntr") + ";"
                        + rs.getString("estado") + ";"
                        + rs.getDouble("importe") + ";"
                        + rs.getString("producto") + ";"
                        + rs.getInt("unidadesCompradas") + ";"
                        + rs.getDouble("subTotal");

                InformesMultitablaFicheros.guardarLinea(linea1);
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
