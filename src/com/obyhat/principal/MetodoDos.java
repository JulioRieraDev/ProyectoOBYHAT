package com.obyhat.principal;

import javax.swing.*;

import com.obyhat.modelo.dao.UsuarioDAO;
import com.obyhat.modelo.dto.UsuarioDTO;

/**
 * Created by jeis on 14/05/17.
 */
public class MetodoDos {

    public static void main(String[] args) {

        String nombre = "Pepe";
        String pass   = "pepe";

        UsuarioDAO usuario = new UsuarioDAO();

        /**
         * Metodo 2: Ejecutas tu consulta enviandole el nombre de usuario como parametro al metodo consultar.
         */

        // Ejecutando la consulta y almacenando su resultado en una variable de tipo UsuarioDTO.
            UsuarioDTO resultado = usuario.Consultar(nombre);

        /**
         * Si el nombre de usuario que envias como parametro al metodo consultar no existe te devolvera
         * un "null" (ver metodo Consultar en UsuarioDAO).
         */
        if (resultado != null) {

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
            else {

                /**
                 * Si resultado == null, mostrar esto...
                 */
                JOptionPane.showMessageDialog(null, "Usuario no existe. Verifique!");
            }
    }
}
