/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import java.time.LocalDate;

/**
 *
 * @author isard
 */
public class Vendedor implements Lectora{
    // Atributos

    private int codigo;
    private String nombre;
    private String fechaAlta;
    private String domicilio;

    //Constructor
    public Vendedor(int codigo, String nombre, String fechaAlta, String domicilio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
    }

    //Metodos
    /**
     * Metodo que devuelve el codigo
     *
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
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
     * Metodo que devuelve la fecha
     *
     * @return fechaAlta
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Metodo que devuelve el domicilio
     *
     * @return domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     *
     * @return String
     */
    @Override
    public String mostrarDatosConPuntoComa() {
        return "Fabricante{" + ";" + "codigo:" + ";" + getCodigo() + ";" + ";" + "nombre:" + ";" + getNombre()
                + "fecha alta:" + ";" + getFechaAlta() + ";" + "domicilio:" + ";" + getDomicilio() + '}';
    }

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     *
     * @return String
     */
    @Override
    public String mostrarDatosConDosPuntos() {
        return "Fabricante{" + " codigo: " + getCodigo() + " nombre: " + getNombre()
                + " fecha alta: " + getFechaAlta() + " domicilio: " + getDomicilio()
                + +'}';
    }

}
