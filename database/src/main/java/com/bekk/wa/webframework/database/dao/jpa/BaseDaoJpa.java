/*
 * Borrowed from Dudney.Net 
 */
package com.bekk.wa.webframework.database.dao.jpa;

import com.bekk.wa.webframework.database.dao.BaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
public class BaseDaoJpa<T, PK extends Serializable> implements BaseDao<T, PK> {
    private static final Log log = LogFactory.getLog(BaseDaoJpa.class);

    private EntityManager entityManager;

    private Class<T> type = null;

    public BaseDaoJpa(final Class<T> aType) {
        this.type = aType;
    }

    public BaseDaoJpa() {
    }

    public void delete(PK id) {
        T t = getEntityManager().find(type, id);
        getEntityManager().remove(t);
    }

    public List<T> find(Map<String, Object> propertyValues) {
        return find(propertyValues, null, null);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(Map<String, Object> propertyValues, String[] orderBy) {
        boolean ascending[] = null;
        if (null != orderBy) {
            ascending = new boolean[orderBy.length];
            for (int i = 0; i < orderBy.length; i++) {
                ascending[i] = true;
            }
        }
        return find(propertyValues, orderBy, ascending);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(Map<String, Object> propertyValues, String[] orderBy,
                        boolean[] ascending) {
        String entityName = getEntityName();
        String queryString = constructQueryString(entityName, propertyValues,
                orderBy, ascending);
        if (log.isDebugEnabled()) {
            log.debug("query = " + queryString);
        }
        Query query = constructQuery(propertyValues, queryString, entityManager);
        for (String key : propertyValues.keySet()) {
            query.setParameter(key, propertyValues.get(key));
        }
        return query.getResultList();
    }

    public List<T> find(Map<String, Object> propertyValues, String orderBy,
                        boolean ascending) {
        return find(propertyValues, new String[]{orderBy},
                new boolean[]{ascending});
    }

    public List<T> find(String property, Object value) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(property, value);
        return find(properties);
    }

    public T read(PK id) {
        return getEntityManager().find(type, id);
    }

    public T update(T o) {
        return getEntityManager().merge(o);
    }

    private Query constructQuery(Map<String, Object> propertyValues,
                                 String queryString, EntityManager entityManager) {
        try {
            Query query = null;
            try {
                query = entityManager.createQuery(queryString);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            for (String propName : propertyValues.keySet()) {
                if (propertyValues.get(propName) != null) {
                    query.setParameter(propName, propertyValues.get(propName));
                }
            }
            return query;
        } catch (Exception ex) {
            throw new RuntimeException("Could not create query :" + queryString, ex);
        }
    }

    protected String getEntityName(Class<T> clazz) {
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        if (entity == null) {
            return clazz.getSimpleName();
        }
        String entityName = entity.name();

        if (entityName == null) {
            return clazz.getSimpleName();
        } else if (!(entityName.length() > 0)) {
            return clazz.getSimpleName();
        } else {
            return entityName;
        }

    }

    protected String getEntityName() {
        return getEntityName(type);
    }

    protected String getPKFieldName(Class<T> clazz) {
        // traverse the fields looking for the field marked with an @Id
        // annotation
        String fieldName = getIdFieldName(clazz);
        // next try the methods
        if (fieldName == null) {
            fieldName = getIdFieldMethod(clazz);
        }
        // return the field name
        return fieldName;
    }

    private String getIdFieldName(Class clazz) {
        String fieldName = null;
        for (Field field : clazz.getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                fieldName = field.getName();
            }
        }
        if (null == fieldName && null != clazz.getSuperclass()) {
            fieldName = getIdFieldName(clazz.getSuperclass());
        }
        return fieldName;
    }

    private String getIdFieldMethod(Class clazz) {
        String fieldName = null;
        for (Method method : clazz.getDeclaredMethods()) {
            Id id = method.getAnnotation(Id.class);
            if (id != null) {
                fieldName = method.getName().substring(3);
                String firstChar = fieldName.substring(0, 1).toLowerCase();
                fieldName = firstChar + fieldName.substring(1);
            }
        }
        if (null == fieldName && null != clazz.getSuperclass()) {
            fieldName = getIdFieldMethod(clazz.getSuperclass());
        }
        return fieldName;
    }

    protected String getPKFieldName() {
        return getPKFieldName(type);
    }

    protected String constructQueryString(String entityName,
                                          Map<String, Object> propertyValues, String[] orderBy, boolean[] ascending) {
        if ((null != orderBy && null != ascending)
                && orderBy.length != ascending.length) {
            throw new IllegalArgumentException(
                    "orderBy.length must match ascending.length");
        }
        String instanceName = "o";
        StringBuilder query = new StringBuilder("SELECT " + instanceName + " FROM ");
        query.append(entityName);
        query.append(" ");
        query.append(instanceName);
        if (propertyValues.size() > 0) {
            query.append(" WHERE ");
            // keep the property names in a list to make sure we get them out in
            // the right order
            Iterator<String> propItr = propertyValues.keySet().iterator();
            if (propItr.hasNext()) {
                String propName = propItr.next();
                appendValueRepresentation(query, instanceName, propName, propertyValues);
                for (; propItr.hasNext();) {
                    propName = propItr.next();
                    query.append(" AND ");
                    appendValueRepresentation(query, instanceName, propName,
                            propertyValues);
                }
            }
        }
        if (null != orderBy && orderBy.length > 0) {
            query.append(" ORDER BY ");
            for (int i = 0; i < orderBy.length; i++) {
                query.append(instanceName);
                query.append(".");
                query.append(orderBy[i]);
                if (i + 1 < orderBy.length) {
                    query.append(", ");
                }
            }
        }
        return query.toString();
    }

    private void appendValueRepresentation(StringBuilder query,
                                           String instanceName, String propName, Map<String, Object> propertyValues) {
        Object value = propertyValues.get(propName);
        query.append(instanceName);
        query.append(".");
        if (value == null) {
            query.append(propName);
            query.append(" IS NULL ");
        } else {
            query.append(propName);
            query.append(" = :");
            query.append(propName);
        }
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        log.debug("setting entityManager - open: " + entityManager.isOpen());
        this.entityManager = entityManager;
    }

}