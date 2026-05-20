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
 *
 * @author isard
 */
public class Comprobaciones {

    public static boolean comprobarFecha(String fecha) {

        if (fecha == null) {
            System.err.println("La fecha no puede ser null");
            return true;
        }

        String[] fechaSeparada = fecha.split("-");

        try {
            if (fechaSeparada.length != 3) {
                System.err.println("Formato incorrecto. Debe ser YYYY-MM-DD");
                return true;
            }

            int ano = Integer.parseInt(fechaSeparada[0]);
            int mes = Integer.parseInt(fechaSeparada[1]);
            int dia = Integer.parseInt(fechaSeparada[2]);

            boolean esBisiesto
                    = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

            if (ano > LocalDate.now().getYear()) {
                System.err.println("El año no puede ser superior al actual");
                return true;
            }

            if (mes < 1 || mes > 12) {
                System.err.println("Mes inválido");
                return true;
            }

            if (dia < 1) {
                System.err.println("Día inválido");
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
                System.err.println("El día no es válido para ese mes");
                return true;
            }

        } catch (NumberFormatException e) {
            System.err.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD");
            return true;
        }

        return false;
    }

    public static boolean verificarDisponibilidad(String texto) {

        for (String opcion : Configuracion.disponibilidad) {

            if (opcion.equalsIgnoreCase(texto)) {
                return true;
            }
        }

        System.out.println("opcion no disponible");
        return false;
    }

    public static boolean verificarEstado(String texto) {

        for (String opcion : Configuracion.estado) {

            if (opcion.equalsIgnoreCase(texto)) {
                return true;
            }
        }

        System.out.println("opcion no disponible");
        return false;
    }

    public static boolean comprobarAnyoFundacion(int anyo) {

        int anyoActual = java.time.LocalDate.now().getYear();

        if (anyo < 1800) {
            System.err.println("El año de fundación no puede ser menor de 1800");
            return false;
        }

        if (anyo > anyoActual) {
            System.err.println("El año de fundación no puede ser mayor que el año actual");
            return false;
        }

        return true;
    }

    public static boolean comprobarStringValido(String texto) {

        if (texto == null || texto.isEmpty()) {
            System.err.println("El valor no puede estar vacío");
            return false;
        }
        if (texto.length() > 45) {
            System.err.println("El valor no puede tener más de 50 caracteres");
            return false;
        }

        return true;
    }

    public static boolean comprobarCarcateresDireccion(String texto) {

        if (texto == null || texto.isEmpty()) {
            System.err.println("El valor no puede estar vacío");
            return false;
        } else if (texto.length() > 50) {
            System.err.println("El valor no puede tener más de 50 caracteres");
            return false;
        }

        return true;
    }

    public static String validarNumero(double numero) {
        if (numero <= 0) {
            return "Número inválido";
        }
        return "Número válido";
    }

}
