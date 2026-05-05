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
import modelos.Fabricante;
import modelos.Producto;
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
                pst.setString(4, ((Fabricante)o).getLugarSeede());
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
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
