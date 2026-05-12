/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author isard
 */
public class Vendedor implements Lectora, Serializable{
    // Atributos

    private int codigo;
    private String nombre;
    private String fechaAlta;
    private String domicilio;
    private double salario;
    private double porcentaje;

    //Constructor
    public Vendedor(int codigo, String nombre, String fechaAlta, String domicilio, double salario, double porcentaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
        this.salario = salario;
        this.porcentaje = porcentaje;
    }

    public Vendedor() {
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
     * Metodo que devuelve el salario
     * @return salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Metodo que devuelve el porcentaje
     * @return porcentaje
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     *
     * @return String
     */
    @Override
    public String mostrarDatosConPuntoComa() {
        return "Fabricante" + ";" + getCodigo() + ";" + getNombre()
                +  ";" + getFechaAlta() + ";" + getDomicilio() + ";" + getSalario()  + ";" + getPorcentaje();
    }

    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     *
     * @return String
     */
    @Override
    public String mostrarDatosConDosPuntos() {
        return "Fabricante" + ":" + getCodigo() + ":" + getNombre()
                + ":" + getFechaAlta() + ":" + getDomicilio()
                 + ":" + getSalario()  + ":" + getPorcentaje();
    }

}
