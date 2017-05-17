package com.obyhat.principal;

import javax.swing.*;

import com.obyhat.modelo.dao.UsuarioDAO;
import com.obyhat.modelo.dto.UsuarioDTO;

/**
 * Created by jeis on 14/05/17.
 */
public class MetodoUno {

    public static void main(String[] args) {

        String nombre = "Pepe";
        String pass   = "pepe";

        UsuarioDAO usuario = new UsuarioDAO();

        /**
         * Metodo 1: Ejecutas tu consulta enviandole el nombre de usuario como parametro al metodo consultar.
         */

        try {

            // Ejecutando la consulta y almacenando su resultado en una variable de tipo UsuarioDTO.
            UsuarioDTO resultado = usuario.Consultar(nombre);
            //Si nombre no existe va al Catch.
            //Si existe continua...

            /**
             * Si el nombre de usuario coincide con alguno de la base de datos se guarda el resultado de la
             * consulta en la variable "resultado".
             *
             *   Comparo lo que obtendria del formulario con lo que obtengo de la consulta a la Base de Datos.
             *
             * - nombre = resultado.getNombre()
             * - pass = resultado.getContrasena()
             *
             */

            if (nombre.equals(resultado.getNombre()) && pass.equals(resultado.getContrasena())){

                /**
                 * # Si las dos condiciones son verdaderas muestra el mensaje de Bienvenida.
                 */
                JOptionPane.showMessageDialog(null, "Bienvenido "+ resultado.getNombre());
            }
            else {

                /**
                 * # De lo contrario si alguna de las dos condiciones no se cumple muestra el mensaje de Incorrecto.
                 */
                JOptionPane.showMessageDialog(null, "Usuario o Contrasena incorrecto");
            }
        }
        catch (Exception e) {

            /**
             * Esta Exepcion la arrojaria si el nombre de usuario que le envio como parametro al metodo consultar
             * devuelve un null indicando que el no existe ninguna coincidencia en los registros.
             */

            JOptionPane.showMessageDialog(null, "Usuario no existe. Verifique!");
        }

    }
}
