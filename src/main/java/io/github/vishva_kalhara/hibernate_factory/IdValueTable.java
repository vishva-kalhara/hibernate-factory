package io.github.vishva_kalhara.hibernate_factory;

import javax.persistence.*;

/**
 * A base class representing a table with an {@code id} and a {@code value} column.
 * <p>
 * This class is annotated with {@link MappedSuperclass}, making it suitable for inheritance
 * in JPA entity classes. It provides a common structure for entities that require
 * an auto-generated primary key and a non-null textual value.
 * </p>
 *
 * <p>Example subclasses: Category, Role, Tag, etc.</p>
 *
 * @author Wishva
 */
@MappedSuperclass
public class IdValueTable {

    /**
     * The primary key of the entity.
     * <p>
     * Auto-generated using {@link GenerationType#IDENTITY}.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * A non-null string value associated with the entity.
     */
    @Column(nullable = false)
    private String value;

    /**
     * Default constructor required by JPA.
     */
    public IdValueTable() {
    }

    /**
     * Constructs a new {@code IdValueTable} with the specified value.
     *
     * @param value the value to be set
     */
    public IdValueTable(String value) {
        this.value = value;
    }

    /**
     * Constructs a new {@code IdValueTable} with the specified ID.
     * Primarily used when referencing or mocking existing records.
     *
     * @param id the ID of the record
     */
    public IdValueTable(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the entity.
     *
     * @return the primary key
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the entity.
     *
     * @param id the primary key to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value associated with the entity.
     *
     * @return the value field
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value associated with the entity.
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
