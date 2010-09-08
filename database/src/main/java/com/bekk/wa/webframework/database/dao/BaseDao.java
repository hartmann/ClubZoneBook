/*
 * Borrowed from Dudney.Net 
 */
package com.bekk.wa.webframework.database.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK extends Serializable> {

    /**
     * Pull a single instance with id as its primary key.
     *
     * @returns null if the object can't be found, otherwise the object.
     */
    T read(PK id);

    /**
     * Update the object <code>o</code> in the database. If <code>o</code> is
     * not already a managed object it will become so and then persisted. The
     * newly persisted object is returned, it is not guarenteed to be the same
     * object so you should discard your reference to <code>o</code> after
     * calling this method i.e. reassign o to the instance returned from this
     * method.
     *
     * @param o
     */
    T update(T o);

    /**
     * Remove the object identified by <code>id</code> from the db. If the
     * object does not exist this is equivalent to a no-op.
     *
     * @param id the identifier of the object to delete.
     */
    void delete(PK id);

    /**
     * Find the object based on the property values in the map. The keys must be
     * the name of persistent properties of the type <code>T</code>. This is
     * conceptually equavalent to SELECT t FROM T t WHERE t.key1 =
     * propertyValues.get(key1) AND t.key2 = propertyValues.get(key2) AND ...
     * t.keyN = propertyValues.get(keyN) in JPQL.
     *
     * @param propertyValues
     * @return the set of all objects that match the criteria.
     * @throws IllegalArgumentException if one of the properties is not a property of the type
     *                                  <code>T</code>
     */
    List<T> find(Map<String, Object> propertyValues);

    /**
     * Find the object based on the property values in the map. The keys must be
     * the name of persistent properties of the type <code>T</code>. The
     * results are orderd according the <code>assending</code> flag
     * cooresponding to the <code>orderBy</code> property.
     *
     * @param propertyValues properties of VO to use as filters
     * @param orderBy        fields to order by in descending order assumes ascending order
     * @return the list of all objects that match the criteria ordered as
     *         specified
     * @throws IllegalArgumentException if one of the properties (in the <code>propertyValues</code> or
     *                                  <code>orderBy</code>) is not a property of the type
     *                                  <code>T</code>
     */
    List<T> find(Map<String, Object> propertyValues, String[] orderBy);

    /**
     * Find the object based on the property values in the map. The keys must be
     * the name of persistent properties of the type <code>T</code>. The
     * results are orderd according the <code>assending</code> flag
     * cooresponding to the <code>orderBy</code> property.
     *
     * @param propertyValues properties of VO to use as filters
     * @param orderBy        fields to order by in descending order
     * @param ascending      flags indicating asscending or decending order
     * @return the list of all objects that match the criteria ordered as
     *         specified
     * @throws IllegalArgumentException if one of the properties (in the <code>propertyValues</code> or
     *                                  <code>orderBy</code>) is not a property of the type
     *                                  <code>T</code>. Also throws this exception if the
     *                                  <code>assending</code> and <code>orderBy</code> arrays are
     *                                  not of the same length.
     */
    List<T> find(Map<String, Object> propertyValues, String[] orderBy,
                 boolean ascending[]);

    /**
     * Find the object based on the property values in the map. The keys must be
     * the name of persistent properties of the type <code>T</code>. The
     * results are orderd by the <code>orderBy</code> property according the
     * <code>assending</code> flag.
     *
     * @param propertyValues properties of VO to use as filters
     * @param orderBy        fields to order by in descending order
     * @param ascending      flags indicating asscending or decending order
     * @return the list of all objects that match the criteria ordered as
     *         specified
     * @throws IllegalArgumentException if one of the properties (in the <code>propertyValues</code> or
     *                                  <code>orderBy</code>) is not a property of the type
     *                                  <code>T</code>.
     */
    List<T> find(Map<String, Object> propertyValues, String orderBy,
                 boolean ascending);

    /**
     * Find the object based on the <code>property</code> and <code>value</code>
     * specified. The property must be one of the persistent properties on type
     * <code>T</code>. This is equavalent to SELECT t FROM T t WHERE t.property =
     * value in JPQL.
     *
     * @param property persistent property of <code>T</code>
     * @param value    value to match
     * @return objects of type <code>T</code> matching the <code>property</code>
     *         and <code>value</code>.
     * @throws IllegalArgumentException if the property is not a property of the type <code>T</code>.
     */
    List<T> find(String property, Object value);

}
