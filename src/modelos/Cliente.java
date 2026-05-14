/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import interfaces.Lectora;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * La clase funciona para insertar un 
 * cliente a la tabla de datos con sus respectivos atributos
 * @author Natalia
 */
public class Cliente implements Lectora, Serializable{
    //atributos
    private int codigo;
    private String nombre;
    private String fechaNacimiento;
    private String direccionEnvio;
    private String telefono;
    private String correo;
    
    //constructor

    public Cliente(int codigo, String nombre, String fechaNacimiento, String direccionEnvio, String telefono, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Cliente() {
    }
    
    
    
    
    //getter

    /**
     * metodo que devuelve el codigo del cliente
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * metodo que devuelve el nombre del cliente
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * metodo que devuelve la fecha de Nacimiento del cliente
     * @return fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * metodo que devuelve la direccion de envio del cliente
     * @return direccionEnvio
     */
    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * metodo que devuelve el telefono del cliente
     * @return cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * metodo que devuelve el correo del cliente
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
    //metodos

    /**
     * metodo que muestra los datos del cliente separada mediante punto y coma (;)
     * @return la informacion del cliente
     */
    @Override
    public String mostrarDatosConPuntoComa() {
        return  getCodigo() + ";" + getNombre() + ";" 
                + getFechaNacimiento() + ";" + getDireccionEnvio() + ";" +
                        getTelefono() + ";" + getCorreo();
    }

    /**
     * metodo que muestra los datos del cliente separada mediante dos puntos (:)
     * @return la informacion del cliente
     */
    @Override
    public String mostrarDatosConDosPuntos() {
        return  getCodigo() + ":" + getNombre() + ":" 
                + getFechaNacimiento() + ":" + getDireccionEnvio() + ":" +
                        getTelefono() + ":" + getCorreo();
    }
    
    
   
    
    
    
}
