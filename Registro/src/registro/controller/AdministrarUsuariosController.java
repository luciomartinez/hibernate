/*
 * Copyright (C) 2014 Lucio Martinez <luciomartinez at openmailbox dot org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package registro.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import registro.dao.UsuariosDaoImpl;
import registro.entity.Usuarios;
import registro.util.HibernateUtil;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class AdministrarUsuariosController {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public Usuarios agregarUsuario(String nombre, String apellido) throws Exception {
        Usuarios u = new Usuarios(nombre, apellido);

        try {
            session.beginTransaction();

            new UsuariosDaoImpl(session).add(u);

            session.getTransaction().commit();

            return u;

        } catch(HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }

            throw new Exception("Error interno al intentar guardar el usuario.");
        }

    }

}
