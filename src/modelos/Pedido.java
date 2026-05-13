/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import java.io.Serializable;
import utils.Configuracion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * La clase funciona para insertar un 
 * pedido a la tabla de datos con sus respectivos atributos
 * @author Natalia
 */
public class Pedido implements Lectora, Serializable{
    //atributos
    private int codigo;
    private int codigoVendedor;
    private int codigoCliente;
    private String fechaRealizacion;
    private String fechaEntrega;
    private String estado;
    private double importe;
    
    
    
    //constructor

    public Pedido(int codigo, int codigoVendedor, int codigoCliente, String fechaRealizacion, String fechaEntrega, String estado, double importe) {
        this.codigo = codigo;
        this.codigoVendedor = codigoVendedor;
        this.codigoCliente = codigoCliente;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.importe = importe;
        
    }

    public Pedido() {
    }
    
    
    
    //getter

    /**
     * metodo que devuelve el codigo del pedido
     * @return el codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * metodo que devuelve el codigo del Vendedor que prepara el pedido
     * @return codigoVendedor
     */
    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    /**
     * metodo que devuelve el codigo del Cliente que realiza el pedido
     * @return codigoCliente
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * metodo que devuelve la fecha de Realizacion del pedido
     * @return fechaRealizacion
     */
    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * metodo que devuelve la fecha de entrega del pedido
     * @return fechaEntrega
     */
    public String getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * metodo que devuelve el estado del pedido
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    
    

    
    //metodos
   
    
    /**
     * metodo que muestra los datos del pedido separada mediante punto y coma (;)
     * @return la informacion del pedido
     */
    @Override
    public String mostrarDatosConPuntoComa(){
        return "Pedido" + ";" + getCodigo() + ";" + getCodigoVendedor()+ ";" 
                + getCodigoCliente()+ ";" + getFechaRealizacion()+ ";" +
                        getFechaEntrega()+ ";" + getEstado() + ";" + getImporte();
    }
    
    /**
     * metodo que muestra los datos del pedido separada mediante dos puntos (:)
     * @return la informacion del pedido
     */
    @Override
    public String mostrarDatosConDosPuntos(){
        return "Pedido" + ":" + getCodigo() + ":" + getCodigoVendedor()+ ":" 
                + getCodigoCliente()+ ":" + getFechaRealizacion()+ ":" +
                        getFechaEntrega()+ ":" + getEstado() + ":" + getImporte();
    }
}
