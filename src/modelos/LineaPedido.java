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
    
    //constructor

    public LineaPedido(int codigoPedido, int codigoProducto, int unidadesCompradas, double subTotal) {
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
        this.unidadesCompradas = unidadesCompradas;
        this.subTotal = subTotal;
        
    }

    public LineaPedido() {
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

    
   
    
    
    //metodos
    
    /**
     * metodo para caalcular el precio total del pedido
     * @return subTotal
     */
    
    
    /**
     * metodo que muestra los datos de la linea pedido separada mediante punto y coma (;)
     * @return la informacion de la linea pedido
     */
    @Override
    public String mostrarDatosConPuntoComa(){
        return "LineaPedido" + ";" + getCodigoPedido()+ ";" + getCodigoProducto()+ ";" 
                + getUnidadesCompradas() + ";" + getSubTotal();
    }
    
    /**
     * metodo que muestra los datos de la linea del pedido separada mediante dos puntos (:)
     * @return la informacion de la linea de pedido
     */
    @Override
    public String mostrarDatosConDosPuntos(){
        return "LineaPedido" + ";" + getCodigoPedido()+ ";" + getCodigoProducto()+ ";" 
                + getUnidadesCompradas() + ":" + getSubTotal();
    }
    
    
}
