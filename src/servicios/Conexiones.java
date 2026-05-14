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
import utils.Configuracion;

/**
 *
 * @author isard
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
            System.out.println("Conexión cerrada");
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
     */
    public static void insertarDatos(Object o) {
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

            } else if (o.getClass() == Pedido.class) {
                //Si el objeto es de tipo pedido, se inserta en la tabla pedido
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((Pedido) o).getCodigo());
                pst.setInt(2, ((Pedido) o).getCodigoVendedor());
                pst.setInt(3, ((Pedido) o).getCodigoCliente());
                pst.setString(4, ((Pedido) o).getFechaRealizacion());
                pst.setString(5, ((Pedido) o).getFechaEntrega());
                pst.setString(6, ((Pedido) o).getEstado());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();

            } else if (o.getClass() == LineaPedido.class) {
                //Si el objeto es de tipo LineaPedido, se inserta en la tabla LineaPedido
                PreparedStatement pst = con.prepareStatement("insert into lineaPedido values (?,?,?,?)");
                //Se asignan los valores a la sentencia
                pst.setInt(1, ((LineaPedido) o).getCodigoPedido());
                pst.setInt(2, ((LineaPedido) o).getCodigoProducto());
                pst.setInt(3, ((LineaPedido) o).getUnidadesCompradas());
                //pst.setDouble(4, ((LineaPedido) o).calcularSubTotal());

                pst.executeUpdate();//Se ejecuta la insercion en la BD 
                pst.close();
            } else {
                System.out.println("Esa clase no existe");
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para verifica si un código existe en la base de datos según la
     * clase indicada, estableciendo la conexión y consultando la tabla
     * correspondiente
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

    public static boolean verificarExistenciaLineaPedido(int codPed, int codPro) {
        boolean existe = false;
        try {
           
            PreparedStatement pst = con.prepareStatement("select codigoPedido, codigoProducto from lineaPedido where codigoPedido=? and codigoProducto=? ");
            pst.setInt(1, codPed);
            pst.setInt(2, codPro);
            ResultSet rs = pst.executeQuery();
            existe = rs.next();
            rs.close();
            pst.close();

           

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;

    }

    public static void actualizarFila(int clase, int codigo) {

        try {
           
            if (clase == 1) {
                System.out.println("INSERTA NUEVO NOMBRE DEL FABRICANTE");
                String nombreFabri = teclado.nextLine();
                System.out.println("INSERTA NUEVO AÑO DE FUNDACION DEL FABRICANTE");
                int anyoFundacionFabri = teclado.nextInt();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO LUGAR SEDE DEL FABRICANTE ");
                String lugarSedeFabri = teclado.nextLine();
                System.out.println("INSERTA NUEVO NUMERO DE EMPLEADOS DEL FABRICANTE");
                int empleadosFabri = teclado.nextInt();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO SITIO WEB DEL FABRICANTE");
                String sitioWebFabri = teclado.next();

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
            } else if (clase == 2) {

                System.out.println("INSERTA NUEVO CODIGO DEL FABRICANTE");
                int codidoFab = teclado.nextInt();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO NOMBRE DEL PRODUCTO");
                String nombre = teclado.nextLine();
                System.out.println("INSERTA NUEVA CATEGORIA DEL PRODUCTO");
                String categoria = teclado.nextLine();
                System.out.println("INSERTA NUEVO DISPONIBILIDAD DEL PRODUCTO");
                String disponibilidad = teclado.nextLine();
                System.out.println("INSERTA NUEVO PRECIO DE VENTA DEL PRODUCTO");
                double precioVenta = teclado.nextDouble();
                teclado.nextLine();

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
            } else if (clase == 3) {

                System.out.println("INSERTA NUEVO NOMBRE DEL VENDEDOR");
                String nombreVen = teclado.nextLine();
                System.out.println("INSERTA NUEVA FECHA DE ALTA DEL VENDEDOR");
                String fechaAltaVen = teclado.nextLine();
                System.out.println("INSERTA NUEVO DOMICILIO DEL VENDEDOR");
                String domicilioVen = teclado.nextLine();
                System.out.println("INSERTA NUEVO SALARIO DEL VENDEDOR");
                double salarioVen = teclado.nextDouble();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO PORCENTAJE DEL VENDEDOR");
                double porcentajeVen = teclado.nextDouble();
                teclado.nextLine();

                PreparedStatement pst = con.prepareStatement("update vendedor set nombre=?,fechaAlta=?,domicilio=?,"
                        + "salario=?,porcentaje=? where codigo=? ");

                pst.setString(1, nombreVen);
                pst.setString(2, fechaAltaVen);//Verificar fecha
                pst.setString(3, domicilioVen);
                pst.setDouble(4, salarioVen);
                pst.setDouble(5, porcentajeVen);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 4) {

                System.out.println("INSERTA NUEVO  NOMBRE DEL CLIENTE");
                String nombreCli = teclado.nextLine();
                System.out.println("INSERTA NUEVA FECHA DE NACIMIENTO DEL CLIENTE");
                String fechaNacimientoCli = teclado.nextLine();
                System.out.println("INSERTA NUEVA DIRECCION DE ENVIO DEL CLIENTE");
                String direccionEnvio = teclado.nextLine();
                System.out.println("INSERTA NUEVO TELEFONO DEL CLIENTE");
                String telCliente = teclado.nextLine();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO CORREO DEL CLIENTE");
                String correoCli = teclado.nextLine();

                PreparedStatement pst = con.prepareStatement("update cliente set nombre=?,fechaNacimiento=?,"
                        + "direccionEnvio=?,telefono=?,correo=? where codigo=? ");

                pst.setString(1, nombreCli);
                pst.setString(2, fechaNacimientoCli);//Verificar fecha
                pst.setString(3, direccionEnvio);
                pst.setString(4, telCliente);
                pst.setString(5, correoCli);
                pst.setInt(6, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 5) {

                System.out.println("INSERTA NUEVO CODIGO DEL VENDEDOR");
                int codigoVen = teclado.nextInt();
                teclado.nextLine();
                System.out.println("INSERTA NUEVO CODIGO DEL CLIENTE");
                int codigoCli = teclado.nextInt();
                teclado.nextLine();
                System.out.println("INSERTA NUEVA FECHA DE REALIZACION DEL PEDIDO");
                String fechaRea = teclado.nextLine();
                System.out.println("INSERTA NUEVA FECHA DE ENTREGA DEL PEDIDO");
                String fechaEnt = teclado.nextLine();
                System.out.println("INSERTA NUEVO ESTADO DEL PEDIDO");
                String estado = teclado.nextLine();
                System.out.println("INSERTA NUEVO IMPORTE DEL PEDIDO");
                double importe = teclado.nextDouble();
                teclado.nextLine();

                PreparedStatement pst = con.prepareStatement("update pedido set codigoVendedor=?,codigoCliente=?,"
                        + "fechaRealizacion=?,fechaEntrega=?,estado=?,importe=? where codigo=? ");

                pst.setInt(1, codigoVen);//Verificar codigo vendedor
                pst.setInt(2, codigoCli);//Verificar codigo cliente
                pst.setString(3, fechaRea);//Verificar fecha
                pst.setString(4, fechaEnt);//Verificar fecha
                pst.setString(5, estado);//Verificar estado
                pst.setDouble(6, importe);
                pst.setInt(7, codigo);
                pst.executeUpdate();
                pst.close();
            } else {
                System.out.println("Clase no encontrada");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void actualizarLineaPedido(int codigoped, int codpro) {
        try {
           
            System.out.println("INSERTA LAS UNIDADES COMPRADAS");
            int unidadesCompradas = teclado.nextInt();
            teclado.nextLine();
            System.out.println("INSERTA EL SUBTOTAL");
            teclado.nextLine();
            double subTotal = teclado.nextDouble();
            PreparedStatement pst = con.prepareStatement("update lineaPedido set unidadesCompradas=?, subTotal=? "
                    + "where codigoPedido=? and codigoProducto=?");

            pst.setInt(1, unidadesCompradas);
            pst.setDouble(2, subTotal);
            pst.setInt(3, codigoped);
            pst.setInt(4, codpro);
            pst.executeUpdate();
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarFila(int clase, int codigo) {

        try {
            
            if (clase == 1) {
                PreparedStatement pst = con.prepareStatement("delete from fabricante where codigo=?");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 2) {
                PreparedStatement pst = con.prepareStatement("delete from producto  where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 3) {
                PreparedStatement pst = con.prepareStatement("delete from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 4) {
                PreparedStatement pst = con.prepareStatement("delete from cliente where codigo=? ");

                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 5) {
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

    public static void eliminarLineaPedido(int codPed, int codPro) {
        try {
            
            PreparedStatement pst = con.prepareStatement("delete from lineaPedido  where codigoPedido=? and codigoProducto ");

            pst.setInt(1, codPed);
            pst.setInt(1, codPro);
            pst.executeUpdate();
            pst.close();
          

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public static void consultarLineaPedido(int codPed, int codPro) {
        try {
            
            PreparedStatement pst = con.prepareStatement("select * from lineaPedido  where codigoPedido=? and codigoProducto=? ");

            pst.setInt(1, codPed);
            pst.setInt(1, codPro);
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

    public static void consultarTodasFila(int clase) {

        try {
            
            if (clase == 1) {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM fabricante ORDER BY codigo ASC");
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

    public static void insersarDatosContenedorFabricante() {

        try {
          
            PreparedStatement pst = con.prepareStatement("SELECT * FROM fabricante");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Fabricante f1 = new Fabricante(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("anyoFundacion"), rs.getString("lugarSede"), rs.getInt("empleados"),
                        rs.getString("sitioWeb"));

                ContenedorFabricante.agregarFabricante(f1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public static void insertarDatosContenedoresPedidos() {
        try {
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM pedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Pedido p1 = new Pedido( rs.getInt("codigoVendedor"), rs.getInt("codigoCliente"), rs.getString("fechaRealizacion"), rs.getString("fechaEntrega"), rs.getString("estado"), rs.getDouble("importe"));

                ContenedorPedido.agregarPedido(p1);
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertarDatosContenedoresLP() throws YaImportadoException {
        try {
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM lineaPedido");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                LineaPedido LP = new LineaPedido(rs.getInt("codigoPedido"), rs.getInt("codigoProducto"), rs.getInt("unidadesCompradas"), rs.getDouble("subTotal"));

                if (!ContenedorLineaPedido.getAlmacenLineasPedidos().isEmpty()) {
                    throw new YaImportadoException("Los datos ya han sido insertados al contenedor");
                } else {
                    ContenedorLineaPedido.agregarLineaPedido(LP);
                }

            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static double precioProducto(int codigo) {
        try {
            
            PreparedStatement pst = con.prepareStatement("SELECT precioVenta FROM producto where codigo = ?");

            ResultSet rs = pst.executeQuery();
            pst.setInt(1, codigo);
            while (rs.next()) {
                return rs.getDouble("precioVenta");

            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static double importeFinal(int codigoPed) {
        try {
           
            PreparedStatement pst = con.prepareStatement("SELECT sum(subTotal) AS total FROM lineaPedido where codigoPedido=?");

            ResultSet rs = pst.executeQuery();
            pst.setInt(1, codigoPed);

            while (rs.next()) {
                return rs.getDouble("total");

            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void consultas(int clase) {
        try {
           

            switch (clase) {
                case 1:
                    PreparedStatement pst = con.prepareStatement("SELECT sum(subTotal) FROM lineaPedido where codigoPedido=?");
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        // return rs.getString("");
                        System.out.println("hola");
                    }
                    rs.close();
                    pst.close();
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
                default:
                    throw new AssertionError();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
