/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import contenedores.ContenedorProducto;
import interfaces.Lectora;
import java.io.Serializable;
import java.util.ArrayList;
import servicios.Conexiones;

/**
 * La clase funciona para insertar una
 * linea de pedido a la tabla de datos con sus respectivos atributos
 * @author Natalia
 */
public class LineaPedido implements Lectora, Serializable{
    //atributos
    private int codigoPedido;
    private int codigoProducto;
    private int unidadesCompradas;
    private double subTotal;
    private static ArrayList<LineaPedido> lineas = new ArrayList<>();
    
    //constructor

    public LineaPedido(int codigoPedido, int codigoProducto, int unidadesCompradas, double subTotal) {
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
        this.unidadesCompradas = unidadesCompradas;
        this.subTotal = subTotal;
        
    }

    public LineaPedido() {
    }

    public LineaPedido(int unidadesCompradas, double subTotal) {
        this.unidadesCompradas = unidadesCompradas;
        this.subTotal = subTotal;
    }
    
    
    
    
    //getter

    /**
     * metodo que devuelve el codigo del pedido 
     * @return codigoPedido
     */
    public int getCodigoPedido() {
        return codigoPedido;
    }

    /**
     * metodo que devuelve el codigo del producto 
     * @return codigoProducto
     */
    public int getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * metodo que devuelve el las unidades compradas 
     * @return unidadesCompradas
     */
    public int getUnidadesCompradas() {
        return unidadesCompradas;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setUnidadesCompradas(int unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    
    public static void agregarLinea(LineaPedido lp){
        lineas.add(lp);
    }
    
    
    //metodos
    
    
    
    
    /**
     * metodo que muestra los datos de la linea pedido separada mediante punto y coma (;)
     * @return la informacion de la linea pedido
     */
    @Override
    public String mostrarDatosConPuntoComa(){
        return getCodigoPedido()+ ";" + getCodigoProducto()+ ";" 
                + getUnidadesCompradas() + ";" + getSubTotal();
    }
    
    /**
     * metodo que muestra los datos de la linea del pedido separada mediante dos puntos (:)
     * @return la informacion de la linea de pedido
     */
    @Override
    public String mostrarDatosConDosPuntos(){
        return  getCodigoPedido()+ ":" + getCodigoProducto()+ ":" 
                + getUnidadesCompradas() + ":" + getSubTotal();
    }

    public static ArrayList<LineaPedido> getLineas() {
        return lineas;
    }
    
    
    
}
