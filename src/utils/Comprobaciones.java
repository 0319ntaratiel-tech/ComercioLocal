/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Clase que contiene diferentes metodos de comprobacion para verificar el correcto funcionamiento del programa
 * @author Gabriela Gomez
 */
public class Comprobaciones {

    /**
     * Metodo que comprueba que fecha coincida con el formato de las base de datos YYY-MM-DD
     * @param fecha fecha que se comprueba
     * @return si la fecha es correcta es true, de lo contrario false
     */
    public static boolean comprobarFecha(String fecha) {

        if (fecha == null) {
            System.out.println("LA FECHA NO PUEDE SER NULA");
            return true;
        }

        String[] fechaSeparada = fecha.split("-");

        try {
            if (fechaSeparada.length != 3) {
                System.out.println("ERROR, FORMATO INCORRECTO. DEBE SER YYYY-MM-DD");
                return true;
            }

            int ano = Integer.parseInt(fechaSeparada[0]);
            int mes = Integer.parseInt(fechaSeparada[1]);
            int dia = Integer.parseInt(fechaSeparada[2]);

            boolean esBisiesto
                    = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

            if (ano > LocalDate.now().getYear()) {
                System.out.println("ERROR, EL ANYO INGRESADO NO PUEDE SER MAYOR AL ACTUAL");
                return true;
            }

            if (mes < 1 || mes > 12) {
                System.out.println("ERROR,MES INVALIDO");
                return true;
            }

            if (dia < 1) {
                System.out.println("ERROR,DIA INVALIDO");
                return true;
            }

            int[] diasPorMes = {
                31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31
            };

            // ajuste febrero bisiesto
            if (esBisiesto) {
                diasPorMes[1] = 29;
            }

            if (dia > diasPorMes[mes - 1]) {
                System.out.println("ERROR, EL DIA ES INVALIDO");
                return true;
            }

        } catch (NumberFormatException e) {
            System.out.println("ERROR,FORMATO DE FECHA INCORRECTO. DEBE SER YYYY-MM-DD");
            return true;
        }

        return false;
    }

    /**
     * Metodo que verificar si el texto ingresado coincide con alguna de las opciones
     * @param texto texto a validar
     * @return si el texto coincide con alguna opcion valida true, sino false
     */
    public static boolean verificarDisponibilidad(String texto) {

        for (String opcion : Configuracion.disponibilidad) {

            if (opcion.equalsIgnoreCase(texto)) {
                return true;
            }
        }

        System.out.println("OPCION NO DISPONIBLE");
        return false;
    }

    /**
     * Metodo que verificar si el texto ingresado coincide con alguna de las opciones
     * @param texto texto a validar
     * @return si el texto coincide con alguna opcion valida true, sino false
     */
    public static boolean verificarEstado(String texto) {

        for (String opcion : Configuracion.estado) {

            if (opcion.equalsIgnoreCase(texto)) {
                return true;
            }
        }

        System.out.println("OPCION NO DISPONIBLE");
        return false;
    }

   /**
    * Metodo para comprobar el año de fundacion no es menor a 1800 ni mayor al año actual
    * @param anyo año a validar
    * @return si el año es correcto true, sino false
    */
    public static boolean comprobarAnyo(int anyo) {

        int anyoActual = java.time.LocalDate.now().getYear();

        if (anyo < 1800) {
            System.out.println("ERROR, EL ANYO INGRESADO ES INCORRECTO");
            return false;
        }

        if (anyo > anyoActual) {
            System.out.println("ERROR, EL ANYO INGRESADO NO PUEDE SER MAYOR AL ACTUAL");
            return false;
        }

        return true;
    }

    /**
     * Metodo que verifica si el texto ingresado no esta vacio, nulo o mayor a 45 caracteres
     * @param texto texto a validar
     * @return c
     */
    public static boolean comprobarStringValido(String texto) {

        if (texto == null || texto.isEmpty()) {
            System.out.println("ERROR,EL TEXTO NO PUEDE ESTAR VACIO");
            return false;
        }
        if (texto.length() > 45) {
            System.out.println("ERROR, EL TEXTO NO PUEDE TENER MAS DE 45 CARCATERES");
            return false;
        }

        return true;
    }

    /**
     * Metodo que verifica si el texto ingresado no esta vacio, nulo o mayor a 50 caracteres
     * @param texto texto a validar
     * @return texto a validar
     */
    public static boolean comprobarCarcateresDireccion(String texto) {

        if (texto == null || texto.isEmpty()) {
            System.out.println("ERROR,EL TEXTO NO PUEDE ESTAR VACIO");
            return false;
        } else if (texto.length() > 50) {
            System.out.println("ERROR, EL TEXTO NO PUEDE TENER MAS DE 50 CARCATERES");
            return false;
        }

        return true;
    }

    /**
     * Metodo que valida si el numero ingresado es mayor a 0
     * @param numero numero a validar
     * @return si el numero es correcto dveuelve un texto de numero valido, sino texto invalido
     */
    public static boolean  validarNumero(double numero) {
        if (numero >= 0) {
            return true;
        }
        System.out.println("ERROR, EL NUMERO INGRESADO DEBE SER MAYOR DE 0");
        return false;
    }

}
