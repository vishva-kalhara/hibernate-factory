package io.github.vishva_kalhara.hibernate_factory.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Utility class for sending standardized HTTP responses in a servlet environment.
 * <p>
 * This class provides helper methods to send successful and error responses
 * using {@link HttpServletResponse}, converting Java objects to JSON using Gson.
 * </p>
 *
 * <p>
 * Common usage includes sending success (OK), bad request, or internal server error responses
 * along with a custom DTO.
 * </p>
 *
 * @author Wishva
 */
public class ResponseHandler {

    /**
     * Sends a custom response with the given DTO and status code.
     *
     * @param dto        the data transfer object to be serialized and sent as JSON
     * @param statusCode the HTTP status code to be set on the response
     * @param resp       the {@link HttpServletResponse} to write the response to
     * @throws IOException if an I/O error occurs during writing
     */
    public static void send(Object dto, StatusCode statusCode, HttpServletResponse resp) throws IOException {
        resp.setStatus(statusCode.getCode());
        resp.getWriter().write(new Gson().toJson(dto));
    }

    /**
     * Sends a 200 OK response with the given DTO.
     *
     * @param dto  the data transfer object to be serialized and sent as JSON
     * @param resp the {@link HttpServletResponse} to write the response to
     * @throws IOException if an I/O error occurs during writing
     */
    public static void sendOk(Object dto, HttpServletResponse resp) throws IOException {
        resp.setStatus(StatusCode.OK.getCode());
        resp.getWriter().write(new Gson().toJson(dto));
    }

    /**
     * Sends a 400 Bad Request response with the specified message.
     *
     * @param msg  the error message to include in the response body
     * @param resp the {@link HttpServletResponse} to write the response to
     * @throws IOException if an I/O error occurs during writing
     */
    public static void sendBadRequest(String msg, HttpServletResponse resp) throws IOException {
        ResponseHandler.send(new ErrorDTO(msg, StatusCode.BAD_REQUEST), StatusCode.BAD_REQUEST, resp);
    }

    /**
     * Sends a 500 Internal Server Error response with the specified message.
     *
     * @param msg  the error message to include in the response body
     * @param resp the {@link HttpServletResponse} to write the response to
     * @throws IOException if an I/O error occurs during writing
     */
    public static void sendInternalServerError(String msg, HttpServletResponse resp) throws IOException {
        ResponseHandler.send(new ErrorDTO(msg, StatusCode.INTERNAL_SERVER_ERROR), StatusCode.INTERNAL_SERVER_ERROR, resp);
    }
}
