/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import contenedores.ContenedorProducto;
import interfaces.Lectora;
import java.io.Serializable;
import java.util.ArrayList;

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
    private  ArrayList<ContenedorProducto> productos ;
    
    //constructor

    public LineaPedido(int codigoPedido, int codigoProducto, int unidadesCompradas) {
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
        this.unidadesCompradas = unidadesCompradas;
      
        
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

    public void setProductos(ArrayList<ContenedorProducto> productos) {
        this.productos = productos;
    }

   
    
    
    //metodos
    
    /**
     * metodo para caalcular el precio total del pedido
     * @return subTotal
     */
    public  double calcularSubTotal(){
        double subTotal = 0;
        for (ContenedorProducto p : productos) {
            for (Producto productos : p.getAlmacenProductos()) {
                subTotal = getUnidadesCompradas() * p.mostrarPrecioVenta();
            }
            
        }
      
        return subTotal;
        
    }
    
    /**
     * metodo que muestra los datos de la linea pedido separada mediante punto y coma (;)
     * @return la informacion de la linea pedido
     */
    @Override
    public String mostrarDatosConPuntoComa(){
        return "LineaPedido" + ";" + getCodigoPedido()+ ";" + getCodigoProducto()+ ";" 
                + getUnidadesCompradas()+ ";" + calcularSubTotal();
    }
    
    /**
     * metodo que muestra los datos de la linea del pedido separada mediante dos puntos (:)
     * @return la informacion de la linea de pedido
     */
    @Override
    public String mostrarDatosConDosPuntos(){
        return "LineaPedido" + ";" + getCodigoPedido()+ ";" + getCodigoProducto()+ ";" 
                + getUnidadesCompradas()+ ";" + calcularSubTotal();
    }
    
    
}
