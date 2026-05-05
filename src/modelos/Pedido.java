/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import utils.Configuracion;
import java.time.LocalDate;

/**
 * La clase funciona para insertar un 
 * pedido a la tabla de datos con sus respectivos atributos
 * @author Natalia
 */
public class Pedido implements Lectora{
    //atributos
    private int codigo;
    private int codigoVendedor;
    private int codigoCliente;
    private LocalDate fechaRealizacion;
    private LocalDate fechaEntrega;
    private Configuracion estado;
    private double importe;
    
    //constructor

    public Pedido(int codigo, int codigoVendedor, int codigoCliente, LocalDate fechaRealizacion, LocalDate fechaEntrega, Configuracion estado, double importe) {
        this.codigo = codigo;
        this.codigoVendedor = codigoVendedor;
        this.codigoCliente = codigoCliente;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.importe = importe;
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
    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * metodo que devuelve la fecha de entrega del pedido
     * @return fechaEntrega
     */
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * metodo que devuelve el estado del pedido
     * @return estado
     */
    public Configuracion getEstado() {
        return estado;
    }

    /**
     * metodo que devuelve el importe del pedido
     * @return importe
     */
    public double getImporte() {
        return importe;
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
