/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import utils.Configuracion;

/**
 * La clase Producto tiene la funcion de crear un producto con sus atributos
 *
 * @author Gabriela Gomez
 */
public class Producto implements Lectora{
    //Atributos

    private int codigo;
    private int codigoFabricante;
    private String nombre;
    private String categoria;
    private String disponibilidad;
    private double precioVenta;

    //Constructor
    public Producto(int codigo, int codigoFabricante, String nombre, String categoria, String disponibilidad, double precioVenta) {
        this.codigo = codigo;
        this.codigoFabricante = codigoFabricante;
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
        this.precioVenta = precioVenta;
    }

    /**
     * Metodo que devuelve el codigo
     *
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo que devuelve el codgio del fabricante
     *
     * @return codigoFabricante
     */
    public int getCodigoFabricante() {
        return codigoFabricante;
    }

    /**
     * Metodo que devuelve el nombre
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que devuelve la categoria
     *
     * @return cateoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Metodo que devuelve la disponibilidad del producto
     *
     * @return disponibilidad
     */
    public String getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Metodo que devuelve el precio de venta  del producto
     * @return precioVenta
     */
    public double getPrecioVenta() {
        return precioVenta;
    }
    
    

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     * @return String
     */
    @Override
    public String mostrarDatosConPuntoComa() {
        return "Fabricante{" + ";" + "codigo:" + ";" + getCodigo() + ";" + ";" + "codigoFabricante:" + ";" + getCodigoFabricante()
                + "nombre:" + ";" + getNombre() + ";" + "categoria:" + ";" + getCategoria() + ";" + "disponibilidad:" + ";" + getDisponibilidad() + '}';
    }

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     * @return String
     */
    @Override
    public String mostrarDatosConDosPuntos() {
        return "Fabricante{" + " codigo: " + getCodigo() + " codigoFabricante: " + getCodigoFabricante()
                + " nombre: " + getNombre() + " categoria: " + getCategoria()
                + " disponibilidad: " + getDisponibilidad() + '}';
    }
}