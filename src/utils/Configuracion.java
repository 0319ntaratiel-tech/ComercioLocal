/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 * Clase que contiene las diferentes constantes que usaremos a lo largo del proyecto
 * @author Natalia
 */
public class Configuracion {
    //CONSTANTES
    
    /**
     * Vector que almacena los diferentes estados de un pedido
     */
    public static String[] estado = {"realizado","cancelado", "enviado",
        "entregado","devuelto"};
    
    /**
     * Vector que almacena las diferentes disponibilidades de un producto
     */
    public static String[] disponibilidad = {"disponible","pocas unidades","no disponible"};
    
    /**
     * Constante String que guarda el url de la base de datos
     */
    public static String url = "jdbc:mysql://localhost:3306/comerciolocal";
    
    /**
     * Constante String que guarda el user de la base de datos
     */
    public static String user = "root";
    
    /**
     * Constante String que guarda la contraseña de la base de datos
     */
    public static String password = "root";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de fabricante
     */
    public static String nombreFicheroTextoFabri = "ficheroFabri.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de fabricante
     */
    public static String nombreFicheroBinarioFabri = "ficheroFabri.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de fabricante
     */
    public static String nombreFicheroCSVFabri = "ficheroFabri.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de fabricante
     */
    public static String nombreFicheroJSONFabri = "ficheroFabri.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de producto
     */
    public static String nombreFicheroTextoPro = "ficheroPro.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de producto
     */
    public static String nombreFicheroBinarioPro = "ficheroPro.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de producto
     */
    public static String nombreFicheroCSVPro = "ficheroPro.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de producto
     */
    public static String nombreFicheroJSONPro = "ficheroPro.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de vendedor
     */
    public static String nombreFicheroTextoVen = "ficheroVen.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de vendedor
     */
    public static String nombreFicheroBinarioVen = "ficheroVen.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de vendedor
     */
    public static String nombreFicheroCSVVen = "ficheroVen.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de vendedor
     */
    public static String nombreFicheroJSONVen = "ficheroVen.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de cliente
     */
    public static String nombreFicheroTextoCli = "ficheroCli.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de cliente
     */
    public static String nombreFicheroBinarioCli = "ficheroCli.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de cliente
     */
    public static String nombreFicheroCSVCli = "ficheroCli.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de cliente
     */
    public static String nombreFicheroJSONCli = "ficheroCli.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de pedido
     */
    public static String nombreFicheroTextoPed = "ficheroPed.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de pedido
     */
    public static String nombreFicheroBinarioPed = "ficheroPed.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de pedido
     */
    public static String nombreFicheroCSVPed = "ficheroPed.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de pedido
     */
    public static String nombreFicheroJSONPed = "ficheroPed.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de Linea Pedido
     */
    public static String nombreFicheroTextoLP = "ficheroLP.txt";
    
    /**
     * Constante String que guarda el nombre del fichero binario de Linea Pedido
     */
    public static String nombreFicheroBinarioLP = "ficheroLP.dat";
    
    /**
     * Constante String que guarda el nombre del fichero csv de Linea Pedido
     */
    public static String nombreFicheroCSVLP = "ficheroLP.csv";
    
    /**
     * Constante String que guarda el nombre del fichero json de Linea Pedido
     */
    public static String nombreFicheroJSONLP = "ficheroLP.json";
    
    /**
     * Constante String que guarda el nombre del fichero de texto de los informes multitabla
     */
    public static String nombreFicheroMultitabla = "ficheroInfomes.txt";
    
}
