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
    public static void conexionEstablecida(){
        try {
            con = DriverManager.getConnection(Configuracion.url, Configuracion.user, Configuracion.password);
            System.out.println("Conexion exitosa");
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void cierreDeConexion(){
        try {
            con.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertarDatos(Object o){
        try {
            if(o.getClass()== Fabricante.class.getClass()){
                PreparedStatement pst = con.prepareStatement("insert into fabricante values (?,?,?,?,?,?)" );
                pst.setInt(1, ((Fabricante)o).getCodigo());
                pst.setString(2, ((Fabricante)o).getNombre());
                pst.setInt(3, ((Fabricante)o).getAnyoFundacion());
                pst.setString(4, ((Fabricante)o).getLugarSede());
                pst.setInt(5, ((Fabricante)o).getEmpleados());
                pst.setString(6, ((Fabricante)o).getSitioWeb());
            }
            
            if(o.getClass()== Producto.class.getClass()){
                PreparedStatement pst = con.prepareStatement("insert into producto values (?,?,?,?,?,?)" );
                pst.setInt(1, ((Producto)o).getCodigo());
                pst.setInt(2, ((Producto)o).getCodigoFabricante());
                pst.setString(3, ((Producto)o).getNombre());
                pst.setString(4, ((Producto)o).getCategoria());
                pst.setString(5, ((Producto)o).getDisponibilidad());
                pst.setDouble(6, ((Producto)o).getPrecioVenta() );
            }
            
            if(o.getClass()== Vendedor.class.getClass()){
                 PreparedStatement pst = con.prepareStatement("insert into vendedor values (?,?,?,?,?,?)" );
                 pst.setInt(1, ((Vendedor)o).getCodigo());
                 pst.setString(2, ((Vendedor)o).getNombre());
                 pst.setString(3, ((Vendedor)o).getFechaAlta());
                 pst.setString(4, ((Vendedor)o).getDomicilio());
                 pst.setDouble(5, ((Vendedor)o).getSalario());
                 pst.setDouble(6, ((Vendedor)o).getPorcentaje());
            }
            
            if(o.getClass() == Cliente.class.getClass()){
                PreparedStatement pst = con.prepareStatement("insert into cliente values (?,?,?,?,?,?)");
                pst.setInt(1, ((Cliente)o).getCodigo());
                pst.setString(2, ((Cliente)o).getNombre());
                pst.setString(3, ((Cliente)o).getFechaNacimiento());
                pst.setString(4, ((Cliente)o).getDireccionEnvio());
                pst.setString(5, ((Cliente)o).getTelefono());
                pst.setString(6, ((Cliente)o).getCorreo());
            }
            
            if(o.getClass() == Pedido.class.getClass()){
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?,?,?,?)");
                pst.setInt(1, ((Pedido)o).getCodigo());
                pst.setInt(2, ((Pedido)o).getCodigoVendedor());
                pst.setInt(3, ((Pedido)o).getCodigoCliente());
                pst.setString(4, ((Pedido)o).getFechaRealizacion());
                pst.setString(5, ((Pedido)o).getFechaEntrega());
                pst.setString(6, ((Pedido)o).getEstado());
                pst.setDouble(7, ((Pedido)o).getImporte());
            }
            
            if(o.getClass() == LineaPedido.class.getClass()){
                PreparedStatement pst = con.prepareStatement("insert into pedido values (?,?,?,?)");
                pst.setInt(1, ((LineaPedido)o).getCodigoPedido());
                pst.setInt(2, ((LineaPedido)o).getCodigoProducto());
                pst.setInt(3, ((LineaPedido)o).getUnidadesCompradas());
                pst.setDouble(4, ((LineaPedido)o).getSubTotal());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
