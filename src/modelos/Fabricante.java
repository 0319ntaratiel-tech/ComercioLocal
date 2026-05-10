/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import java.io.Serializable;

/**
 * La clase fabricante tiene la funcion de crear un fabricante con sus atributos
 *
 * @author Gabriela Gomez
 */
public class Fabricante implements Lectora, Serializable {
    //Atributos

    private int codigo;
    private String nombre;
    private int anyoFundacion;
    private String lugarSede;
    private int empleados;
    private String sitioWeb;

    //Constructor
    public Fabricante(int codigo, String nombre, int anyoFundacion, String lugarSede, int empleados, String sitioWeb) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.lugarSede = lugarSede;
        this.empleados = empleados;
        this.sitioWeb = sitioWeb;
    }

    public Fabricante() {
    }
    
    

    // Metodos
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
     * Metodo que devuelve el año de fundacion
     *
     * @return anyoFundacion
     */
    public int getAnyoFundacion() {
        return anyoFundacion;
    }

    /**
     * Metodo que devuelve el lugar de la sede
     *
     * @return lugarsede
     */
    public String getLugarSede() {
        return lugarSede;
    }

    /**
     * Metodo que devuelve numero de empleados
     *
     * @return empleados
     */
    public int getEmpleados() {
        return empleados;
    }

    /**
     * Metodo que devuelve el sitio web
     *
     * @return sitioweb
     */
    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnyoFundacion(int anyoFundacion) {
        this.anyoFundacion = anyoFundacion;
    }

    public void setLugarSede(String lugarSede) {
        this.lugarSede = lugarSede;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    /**
     * Metodo que devuelve una cadena de texto con los atributos de la clase con
     * punto y coma
     *
     * @return String
     */
    @Override
    public String mostrarDatosConPuntoComa() {
        return "Fabricante" + ";" + getCodigo() + ";"
                + getNombre() + ";" + getAnyoFundacion() + ";"
                + getLugarSede() + ";" + getEmpleados() + ";"
                + getSitioWeb();
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
                + ":" + getAnyoFundacion() + ":" + getLugarSede()
                + ":" + getEmpleados() + ":" + getSitioWeb();
    }

}
