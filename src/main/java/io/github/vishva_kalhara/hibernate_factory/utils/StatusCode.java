package io.github.vishva_kalhara.hibernate_factory.utils;

/**
 * Enumeration of standard HTTP status codes used for response handling.
 * <p>
 * This enum maps commonly used HTTP status codes to meaningful names for use in application logic.
 * It provides a method to retrieve the corresponding numeric code.
 * </p>
 *
 * <ul>
 *     <li>{@code OK} - 200: Successful request</li>
 *     <li>{@code CREATED} - 201: Resource created successfully</li>
 *     <li>{@code NO_CONTENT} - 204: Successful with no content</li>
 *     <li>{@code BAD_REQUEST} - 400: Malformed or invalid request</li>
 *     <li>{@code UNAUTHORIZED} - 401: Authentication required</li>
 *     <li>{@code FORBIDDEN} - 403: Access denied</li>
 *     <li>{@code NOT_FOUND} - 404: Resource not found</li>
 *     <li>{@code METHOD_NOT_ALLOWED} - 405: HTTP method not allowed</li>
 *     <li>{@code INTERNAL_SERVER_ERROR} - 500: Server-side error</li>
 * </ul>
 *
 * @author Wishva
 */
public enum StatusCode {

    /**
     * 200 OK - Request was successful.
     */
    OK(200),

    /**
     * 201 Created - Resource was created successfully.
     */
    CREATED(201),

    /**
     * 204 No Content - Request was successful but there is no content to return.
     */
    NO_CONTENT(204),

    /**
     * 400 Bad Request - The request was invalid or cannot be otherwise served.
     */
    BAD_REQUEST(400),

    /**
     * 401 Unauthorized - Authentication is required and has failed or not been provided.
     */
    UNAUTHORIZED(401),

    /**
     * 403 Forbidden - The request is understood, but it has been refused or access is not allowed.
     */
    FORBIDDEN(403),

    /**
     * 404 Not Found - The requested resource could not be found.
     */
    NOT_FOUND(404),

    /**
     * 405 Method Not Allowed - A request was made of a resource using a request method not supported by that resource.
     */
    METHOD_NOT_ALLOWED(405),

    /**
     * 500 Internal Server Error - An unexpected condition was encountered.
     */
    INTERNAL_SERVER_ERROR(500);

    /**
     * The numeric HTTP status code.
     */
    private final int code;

    /**
     * Constructs a {@code StatusCode} enum with the specified HTTP status code.
     *
     * @param code the numeric HTTP status code
     */
    StatusCode(int code) {
        this.code = code;
    }

    /**
     * Returns the numeric HTTP status code.
     *
     * @return the HTTP status code as an integer
     */
    public int getCode() {
        return code;
    }
}
