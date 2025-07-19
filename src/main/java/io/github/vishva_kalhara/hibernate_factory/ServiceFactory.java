package io.github.vishva_kalhara.hibernate_factory;

import io.github.vishva_kalhara.hibernate_factory.utils.FactoryException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * A generic abstract service factory for creating and retrieving entities that extend {@link IdValueTable}.
 * <p>
 * This factory provides methods for:
 * <ul>
 *     <li>Creating a new entity (if it doesn't already exist by value)</li>
 *     <li>Retrieving an entity by its value</li>
 *     <li>Fetching all entities of the specified type</li>
 * </ul>
 * <p>
 * It uses Hibernate's legacy {@link Criteria} API and requires a Hibernate {@link Session} and a class reference.
 * </p>
 *
 * <p>
 * Intended to be extended for domain-specific factories (e.g., CategoryFactory, RoleFactory, etc.)
 * where the entity class has a constructor that accepts a single {@code String value}.
 * </p>
 *
 * @param <T> the entity type extending {@link IdValueTable}
 *
 * @author Wishva
 */
public abstract class ServiceFactory<T extends IdValueTable> {

    /**
     * The Hibernate session used for database operations.
     */
    private final Session session;

    /**
     * The class type of the entity.
     */
    private final Class<T> clazz;

    /**
     * Constructs a new {@code ServiceFactory} with the given session and entity class.
     *
     * @param session the Hibernate session
     * @param clazz   the class type of the entity
     */
    public ServiceFactory(Session session, Class<T> clazz) {
        this.session = session;
        this.clazz = clazz;
    }

    /**
     * Creates and saves a new entity with the given value, if it doesn't already exist.
     *
     * @param value the value to be assigned to the new entity
     * @return the created or fetched entity
     * @throws FactoryException if an entity with the same value already exists
     * @throws Exception if the entity could not be instantiated or saved
     */
    public T create(String value) throws Exception {
        if (getByValue(value) != null) {
            throw new FactoryException(clazz.getSimpleName() + " already exists!");
        }

        T entity;
        try {
            entity = clazz.getDeclaredConstructor(String.class).newInstance(value);
        } catch (Exception e) {
            throw new Exception("Could not instantiate class: " + clazz.getSimpleName(), e);
        }

        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();

        return getByValue(value);
    }

    /**
     * Retrieves an entity by its {@code value} field.
     *
     * @param value the value to search for
     * @return the matching entity, or {@code null} if not found
     */
    public T getByValue(String value) {
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.eq("value", value));
        return (T) criteria.uniqueResult();
    }

    /**
     * Retrieves all entities of type {@code T}.
     *
     * @return a list of all entities
     */
    public List<T> getAll() {
        return session.createCriteria(clazz).list();
    }
}
