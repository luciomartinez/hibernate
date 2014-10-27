/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registro;

import registro.controller.AdministrarUsuariosController;
import registro.entity.Usuarios;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Registro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombre = "Jose", apellido = "Ramos";

        AdministrarUsuariosController usuariosCtrl = new AdministrarUsuariosController();

        /* Agregar el usuario */
        try {
            Usuarios u = usuariosCtrl.agregarUsuario(nombre, apellido);

            System.out.println("Se agregó el usuario '" + u.getNombre() + "' exitosamente!");

        } catch(Exception e) {
            System.out.println("No se agregó el usuario al sistema debido a un error interno.");
        }

        /* Sigue jugando con los otros métodos ya escritos! */

    }

}
