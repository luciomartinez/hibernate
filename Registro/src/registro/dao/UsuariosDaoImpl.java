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

package registro.dao;

import java.util.List;
import org.hibernate.Session;
import registro.entity.Usuarios;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class UsuariosDaoImpl extends GenericDaoImpl<Usuarios, Integer> implements UsuariosDao {

    public UsuariosDaoImpl(Session session) {
        super(session);
    }

    @Override
    public List<Usuarios> fetchAll() {
        String hql = "FROM Usuarios";
        return session.createQuery(hql).list();
    }

    @Override
    public Usuarios getElementByName(String name) {
        Usuarios i;
        String hql = "FROM Usuarios WHERE username = :name";

        List l = session.createQuery(hql).setParameter("name", name).list();
        i = (l.size() > 0) ? (Usuarios)l.get(0) : null;

        return i;
    }

}
