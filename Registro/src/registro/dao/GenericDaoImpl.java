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

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Session;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public abstract class GenericDaoImpl <T, PK extends Serializable> implements GenericDao <T, PK> {

    private final Class<T> type;
    protected final Session session;

    public Class<T> getType() {
        return type;
    }

    public GenericDaoImpl(Session session) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.session = session;
    }

    @Override
    public PK add(T newInstance) {
        return (PK) session.save(newInstance);
    }

    @Override
    public T get(PK id) {
        return (T) session.get(getType(), id);
    }

    @Override
    public void update(T transientObject) {
        session.update(transientObject);
    }

    @Override
    public void delete(T persistentObject) {
        session.delete(persistentObject);
    }

}
