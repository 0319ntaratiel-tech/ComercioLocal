/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;

/**
 *
 * @author isard
 */
public class Comprobaciones {



    public static boolean comprobarFecha(String fecha) {
        boolean fechaIncorrecta = false;

        String[] fechaSeparada = fecha.split("-");

        try {
            if (fechaSeparada.length != 3) {
                System.err.println("Formato incorrecto. Debe ser YYYY-MM-DD");
                return true;
            }

            int ano = Integer.parseInt(fechaSeparada[0]);
            int mes = Integer.parseInt(fechaSeparada[1]);
            int dia = Integer.parseInt(fechaSeparada[2]);

            boolean esBisiesto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

            // validar año futuro
            if (ano > LocalDate.now().getYear()) {
                System.err.println("El año es superior al actual");
                fechaIncorrecta = true;
            }

            if (dia < 1 || mes < 1) {
                System.err.println("Día o mes inválido");
                fechaIncorrecta = true;
            }

            if (mes > 12) {
                System.err.println("El mes no puede ser superior a 12");
                fechaIncorrecta = true;
            }

            if (dia > 31) {
                System.err.println("No hay más de 31 días");
                fechaIncorrecta = true;
            }

            if (mes == 2) {
                if ((esBisiesto && dia > 29) || (!esBisiesto && dia > 28)) {
                    System.err.println("Febrero inválido");
                    fechaIncorrecta = true;
                }
            }

            if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                System.err.println("Ese mes tiene solo 30 días");
                fechaIncorrecta = true;
            }

        } catch (NumberFormatException e) {
            System.err.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD");
            fechaIncorrecta = true;
        }

        return fechaIncorrecta;
    }
}
