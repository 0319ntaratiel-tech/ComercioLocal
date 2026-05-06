/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

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

    public static void conexionEstablecida() {
        try {
            con = DriverManager.getConnection(Configuracion.url, Configuracion.user, Configuracion.password);
            System.out.println("Conexion exitosa");

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cierreDeConexion() {
        try {
            con.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertarDatos(Object o) {
        try {
            conexionEstablecida();
            if (o.getClass() == Fabricante.class) {

                PreparedStatement pst = con.prepareStatement("insert into fabricante values (?,?,?,?,?,?)");
                pst.setInt(1, ((Fabricante) o).getCodigo());
                pst.setString(2, ((Fabricante) o).getNombre());
                pst.setInt(3, ((Fabricante) o).getAnyoFundacion());
                pst.setString(4, ((Fabricante) o).getLugarSede());
                pst.setInt(5, ((Fabricante) o).getEmpleados());
                pst.setString(6, ((Fabricante) o).getSitioWeb());

                pst.executeUpdate();
                pst.close();
            } else if (o.getClass() == Producto.class) {
                PreparedStatement pst = con.prepareStatement("insert into producto values (?,?,?,?,?,?)");
                pst.setInt(1, ((Producto) o).getCodigo());
                pst.setInt(2, ((Producto) o).getCodigoFabricante());
                pst.setString(3, ((Producto) o).getNombre());
                pst.setString(4, ((Producto) o).getCategoria());
                pst.setString(5, ((Producto) o).getDisponibilidad());
                pst.setDouble(6, ((Producto) o).getPrecioVenta());

                pst.executeUpdate();
                pst.close();
            } else if (o.getClass() == Vendedor.class) {
                PreparedStatement pst = con.prepareStatement("insert into vendedor values (?,?,?,?,?,?)");
                pst.setInt(1, ((Vendedor) o).getCodigo());
                pst.setString(2, ((Vendedor) o).getNombre());
                pst.setString(3, ((Vendedor) o).getFechaAlta());
                pst.setString(4, ((Vendedor) o).getDomicilio());
                pst.setDouble(5, ((Vendedor) o).getSalario());
                pst.setDouble(6, ((Vendedor) o).getPorcentaje());

                pst.executeUpdate();
                pst.close();
            } else if (o.getClass() == Cliente.class) {
                PreparedStatement pst = con.prepareStatement("insert into cliente values (?,?,?,?,?,?)");
                pst.setInt(1, ((Cliente) o).getCodigo());
                pst.setString(2, ((Cliente) o).getNombre());
                pst.setString(3, ((Cliente) o).getFechaNacimiento());
                pst.setString(4, ((Cliente) o).getDireccionEnvio());
                pst.setString(5, ((Cliente) o).getTelefono());
                pst.setString(6, ((Cliente) o).getCorreo());

                pst.executeUpdate();
                pst.close();
            } else if (o.getClass() == Pedido.class) {
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?,?,?,?)");
                pst.setInt(1, ((Pedido) o).getCodigo());
                pst.setInt(2, ((Pedido) o).getCodigoVendedor());
                pst.setInt(3, ((Pedido) o).getCodigoCliente());
                pst.setString(4, ((Pedido) o).getFechaRealizacion());
                pst.setString(5, ((Pedido) o).getFechaEntrega());
                pst.setString(6, ((Pedido) o).getEstado());
                pst.setDouble(7, ((Pedido) o).getImporte());

                pst.executeUpdate();
                pst.close();
            } else if (o.getClass() == LineaPedido.class) {
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?)");
                pst.setInt(1, ((LineaPedido) o).getCodigoPedido());
                pst.setInt(2, ((LineaPedido) o).getCodigoProducto());
                pst.setInt(3, ((LineaPedido) o).getUnidadesCompradas());
                pst.setDouble(4, ((LineaPedido) o).getSubTotal());

                pst.executeUpdate();
                pst.close();
            } else {
                System.out.println("Esa clase no existe");
            }

            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean verificarExistenciaCodigo(int clase, int codigo) {
        boolean existe = false;

        try {
            conexionEstablecida();
            if (clase == 1) {
                PreparedStatement pst = con.prepareStatement("select codigo from fabricante where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                pst.close();

            } else if (clase == 2) {
                PreparedStatement pst = con.prepareStatement("select codigo from producto where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                pst.close();
            } else if (clase == 3) {
                PreparedStatement pst = con.prepareStatement("select codigo from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                pst.close();

            } else if (clase == 4) {
                PreparedStatement pst = con.prepareStatement("select codigo from cliente where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                pst.close();
            } else if (clase == 5) {
                PreparedStatement pst = con.prepareStatement("select codigo from pedido where codigo=? ");
                pst.setInt(1, codigo);
                ResultSet rs = pst.executeQuery();
                existe = rs.next();
                pst.close();
            } else {
                System.out.println("Esa clase no existe");
            }

            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existe;
    }

    public static boolean verificarExistenciaLineaPedido(int codPed, int codPro) {
        boolean existe = false;
        try {
            conexionEstablecida();
            PreparedStatement pst = con.prepareStatement("select codigoPedido, codigoProducto from lineaPedido where codigoPedido=? and codigoProducto=? ");
            pst.setInt(1, codPed);
            pst.setInt(2, codPro);
            ResultSet rs = pst.executeQuery();
            existe = rs.next();

            cierreDeConexion();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;

    }

    public static void actualizarFila(int clase, int codigo) {

        try {
            conexionEstablecida();
            if (clase == 1) {
                System.out.println("INSERTA NUEVO NOMBRE DEL FABRICANTE");
                String nombreFabri = teclado.nextLine();
                System.out.println("INSERTA NUEVO AÑO DE FUNDACION DEL FABRICANTE");
                int anyoFundacionFabri = teclado.nextInt();
                System.out.println("INSERTA NUEVO LUGAR SEDE DEL FABRICANTE ");
                String lugarSedeFabri = teclado.nextLine();
                System.out.println("INSERTA NUEVO NUMERO DE EMPLEADOS DEL FABRICANTE");
                int empleadosFabri = teclado.nextInt();
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
                System.out.println("INSERTA NUEVO NOMBRE DEL PRODUCTO");
                String nombre = teclado.nextLine();
                System.out.println("INSERTA NUEVA CATEGORIA DEL PRODUCTO");
                String categoria = teclado.nextLine();
                System.out.println("INSERTA NUEVO DISPONIBILIDAD DEL PRODUCTO");
                String disponibilidad = teclado.next();
                System.out.println("INSERTA NUEVO PRECIO DE VENTA DEL PRODUCTO");
                double precioVenta = teclado.nextDouble();

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
                System.out.println("INSERTA NUEVO PORCENTAJE DEL VENDEDOR");
                double porcentajeVen = teclado.nextDouble();

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
                String telCliente = teclado.next();
                System.out.println("INSERTA NUEVO CORREO DEL CLIENTE");
                String correoCli = teclado.next();

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
                System.out.println("INSERTA NUEVO CODIGO DEL CLIENTE");
                int codigoCli = teclado.nextInt();
                System.out.println("INSERTA NUEVA FECHA DE REALIZACION DEL PEDIDO");
                String fechaRea = teclado.next();
                System.out.println("INSERTA NUEVA FECHA DE ENTREGA DEL PEDIDO");
                String fechaEnt = teclado.next();
                System.out.println("INSERTA NUEVO ESTADO DEL PEDIDO");
                String estado = teclado.next();
                System.out.println("INSERTA NUEVO IMPORTE DEL PEDIDO");
                double importe = teclado.nextDouble();

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
            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void actualizarLineaPedido(int codigoped, int codpro) {
        try {
            conexionEstablecida();
            System.out.println("INSERTA LAS UNIDADES COMPRADAS");
            int unidadesCompradas = teclado.nextInt();
            System.out.println("INSERTA EL SUBTOTAL");
            double subTotal = teclado.nextDouble();
            PreparedStatement pst = con.prepareStatement("update  lineaPedido set unidadesCompradas=?, subTotal=? where codigoPedido=? and codigoProducto ");

            pst.setInt(1, unidadesCompradas);
            pst.setDouble(2, subTotal);
            pst.setInt(3, codigoped);
            pst.setInt(4, codigoped);
            pst.executeUpdate();
            pst.close();
            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarFila(int clase, int codigo) {

        try {
            conexionEstablecida();
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
            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarLineaPedido(int codPed, int codPro) {
        try {
            conexionEstablecida();
            PreparedStatement pst = con.prepareStatement("delete from lineaPedido  where codigoPedido=? and codigoProducto ");

            pst.setInt(1, codPed);
            pst.setInt(1, codPro);
            pst.executeUpdate();
            cierreDeConexion();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void consultarFila(int clase, int codigo) {

        try {
            conexionEstablecida();
            if (clase == 1) {
                PreparedStatement pst = con.prepareStatement("select * from fabricante where codigo=?");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 2) {
                PreparedStatement pst = con.prepareStatement("select * from producto  where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 3) {
                PreparedStatement pst = con.prepareStatement("select * from vendedor where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 4) {
                PreparedStatement pst = con.prepareStatement("select * from cliente where codigo=? ");
                pst.setInt(1, codigo);
                pst.executeUpdate();
                pst.close();
            } else if (clase == 5) {
                PreparedStatement pst = con.prepareStatement("select * from pedido where codigo=? ");
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

    public static void consultarLineaPedido(int codPed, int codPro) {
        try {
            conexionEstablecida();
            PreparedStatement pst = con.prepareStatement("select * from lineaPedido where where codigoPedido=? and codigoProducto ");

            pst.setInt(1, codPed);
            pst.setInt(1, codPro);
            pst.executeUpdate();
            pst.close();
            cierreDeConexion();

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void consultarTodasFila(int clase) {

        try {
            conexionEstablecida();
            if (clase == 1) {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM fabricante");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            "Codigo: " + rs.getInt("codigo")
                            + " | Nombre: " + rs.getString("nombre")
                    );
                    
                }
                
                pst.close();

                
            } else if (clase == 2) {
                PreparedStatement pst = con.prepareStatement("select * from producto ");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                }
                pst.close();
            } else if (clase == 3) {
                PreparedStatement pst = con.prepareStatement("select * from vendedor ");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                }
                pst.close();
            } else if (clase == 4) {
                PreparedStatement pst = con.prepareStatement("select * from cliente ");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                }
                pst.close();
            } else if (clase == 5) {
                PreparedStatement pst = con.prepareStatement("select * from pedido ");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                }
                pst.close();
            } else if (clase == 6) {
                PreparedStatement pst = con.prepareStatement("select * from lineaPedido ");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                }
                pst.close();
            } else {
                System.out.println("Clase no encontrada");
            }

            cierreDeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
