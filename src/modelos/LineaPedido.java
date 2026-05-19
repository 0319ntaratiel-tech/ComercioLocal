/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;


import interfaces.Lectora;
import java.io.Serializable;


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
   
    
    //constructor

    public LineaPedido(int codigoPedido, int codigoProducto, int unidadesCompradas, double subTotal) {
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
        this.unidadesCompradas = unidadesCompradas;
        this.subTotal = subTotal;
        
    }

    public LineaPedido() {
    }

    
    
    //getter and setter

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

    /**
     * metodo que modifica el codigoPedido
     * @param codigoPedido 
     */
    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    /**
     * metodo que modifica el codigoProducto
     * @param codigoProducto 
     */
    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * metodo que modifica la unidades compradas
     * @param unidadesCompradas 
     */
    public void setUnidadesCompradas(int unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }

    /**
     * metodo que devuelve el subTotal
     * @return subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * metodo que modifica el subTotal
     * @param subTotal 
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    
    
    
    
    //metodos

    /**
     * metodo que devuelve los datos de las lineas Pedido
     * @return String  datos LineaPedido
     */
    @Override
    public String toString(){
        return "LineaPedido{" + "codigoPedido=" + codigoPedido + ", codigoProducto="+ codigoProducto + ", unidadesCompradas="+ unidadesCompradas
                + ", subTotal=" + subTotal + '}';
    }

    /**
     * metodo que muestra los datos de la linea pedido separada mediante punto y coma (;)
     * @return la informacion de la linea pedido
     */
    @Override
    public String mostrarDatosConPuntoComa() {
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

   

   
    
    
    
}
