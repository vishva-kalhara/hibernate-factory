package io.github.vishva_kalhara.hibernate_factory.utils;

/**
 * A Data Transfer Object (DTO) representing error information.
 * <p>
 * This class encapsulates an error message and an associated {@link StatusCode}.
 * It is typically used to represent error responses in an application, such as in REST APIs.
 * </p>
 *
 * @author wishva
 */
public class ErrorDTO {

    /**
     * The status code representing the type or category of the error.
     */
    private final StatusCode status;

    /**
     * A descriptive message providing details about the error.
     */
    private final String message;

    /**
     * Constructs a new {@code ErrorDTO} with the specified message and status code.
     *
     * @param message    the error message
     * @param statusCode the status code representing the error type
     */
    public ErrorDTO(String message, StatusCode statusCode) {
        this.status = statusCode;
        this.message = message;
    }

    /**
     * Returns the status code of the error.
     *
     * @return the status code
     */
    public StatusCode getStatusCode() {
        return status;
    }

    /**
     * Returns the descriptive error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }
}
