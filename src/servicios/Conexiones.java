/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    
}
