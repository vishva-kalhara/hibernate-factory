package io.github.vishva_kalhara.hibernate_factory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.vishva_kalhara.hibernate_factory.utils.FactoryException;
import io.github.vishva_kalhara.hibernate_factory.utils.ResponseHandler;
import io.github.vishva_kalhara.hibernate_factory.utils.StatusCode;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract servlet controller that handles HTTP requests for entities extending {@link IdValueTable}.
 *
 * This factory is responsible for:
 * <ul>
 *   <li>Creating new entities via POST request</li>
 *   <li>Fetching all entities via GET request</li>
 * </ul>
 * Subclasses must provide the concrete {@link ServiceFactory} and the JSON key used to extract the entity value.
 *
 * <p>This class uses {@link Gson} for JSON parsing and {@link ResponseHandler} for standardized response formatting.</p>
 *
 * @param <T> the type of the entity that extends {@link IdValueTable}
 * @author Wishva
 */

public abstract class ControllerFactory<T extends IdValueTable> extends HttpServlet {

    /**
     * Gson instance used for parsing JSON request bodies.
     */
    private final Gson gson = new Gson();

    /**
     * Provides the service layer to be used by this controller.
     *
     * @return a {@link ServiceFactory} for entity {@code T}
     */
    protected abstract ServiceFactory<T> getService();

    /**
     * Specifies the key in the incoming JSON request body that holds the value to be saved.
     *
     * @return the JSON key to extract the entity value
     */
    protected abstract String getRequestValueKey();

    /**
     * Handles HTTP POST requests for creating a new entity.
     * <p>
     * Expects a JSON request body with a key defined by {@link #getRequestValueKey()}.
     * Sends a 201 CREATED response on success, 400 BAD REQUEST for validation errors,
     * and 500 INTERNAL SERVER ERROR for unexpected failures.
     * </p>
     *
     * @param req  the HTTP request
     * @param resp the HTTP response
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);
            if (body == null || !body.has(getRequestValueKey())) {
                throw new FactoryException("Missing required field: " + getRequestValueKey());
            }

            String value = body.get(getRequestValueKey()).getAsString().trim();
            if (value.isEmpty()) {
                throw new FactoryException(getRequestValueKey() + " cannot be empty.");
            }

            T created = getService().create(value);
            ResponseHandler.send(created, StatusCode.CREATED, resp);

        } catch (FactoryException e) {
            ResponseHandler.sendBadRequest(e.getMessage(), resp);
        } catch (Exception e) {
            ResponseHandler.sendInternalServerError(e.getMessage(), resp);
        }
    }

    /**
     * Handles HTTP GET requests for retrieving all entities.
     * <p>
     * Responds with a list of all available entities in JSON format.
     * Sends a 200 OK response on success and a 500 INTERNAL SERVER ERROR on failure.
     * </p>
     *
     * @param req  the HTTP request
     * @param resp the HTTP response
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ResponseHandler.send(getService().getAll(), StatusCode.OK, resp);
        } catch (Exception e) {
            ResponseHandler.sendInternalServerError(e.getMessage(), resp);
        }
    }
}
