package io.github.vishva_kalhara.hibernate_factory.utils;

/**
 * Custom exception class used to represent exceptions that occur
 * within the Hibernate factory utility operations.
 * <p>
 * This exception can be thrown to signal a specific failure scenario
 * related to factory initialization, configuration, or usage.
 * </p>
 *
 * @author wishva
 */
public class FactoryException extends Exception {

    /**
     * Constructs a new {@code FactoryException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public FactoryException(String message) {
        super(message);
    }
}
