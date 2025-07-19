# Hibernate Factory

**Release Date:** 2025-07-19

## Overview

Hibernate Factory v1.0.0 is the inaugural release of a simple yet powerful Java library designed to streamline the creation and management of reference data entities using Hibernate ORM. This library targets common use cases such as handling categories, roles, tags, and other entities characterized by an auto-generated ID and a unique string value.

The library provides:

-   A generic **ServiceFactory** class for CRUD operations with Hibernate.
-   An abstract **ControllerFactory** servlet to handle HTTP requests with JSON payloads.
-   Standardized HTTP response handling with customizable status codes and JSON serialization.
-   Custom exception handling via **FactoryException**.
-   Built-in HTTP status code enumeration with **StatusCode**.
-   JSON utility via Gson for request and response processing.

This release emphasizes extensibility, ease of use, and clean integration into Java web applications using servlets and Hibernate.

---

## Features

-   Generic factory pattern for reusable service layers.
-   Automatic checking for duplicate entries on creation.
-   Exception-driven error reporting with meaningful messages.
-   Convenient servlet controller base class to handle POST and GET requests.
-   JSON serialization and deserialization using Gson.
-   Standard HTTP response formatting, including OK, Created, Bad Request, and Internal Server Error responses.
-   Enum-based HTTP status codes for improved readability.

---

## Usage Examples

### 1. Define an Entity Class

Create a domain entity extending `IdValueTable`:

```java
@Entity
@Table(name = "categories")
public class Category extends IdValueTable {
    public Category() {
        super();
    }

    public Category(String value) {
        super(value);
    }
}
```

### 2. Implement a Service Factory

Extend `ServiceFactory` for your entity:

```java
public class CategoryServiceFactory extends ServiceFactory<Category> {
    public CategoryServiceFactory(Session session) {
        super(session, Category.class);
    }
}
```

### 3. Create a Controller

Extend `ControllerFactory` and specify the service and JSON key:

```java
public class CategoryController extends ControllerFactory<Category> {

    private final CategoryServiceFactory serviceFactory;

    public CategoryController(Session session) {
        this.serviceFactory = new CategoryServiceFactory(session);
    }

    @Override
    protected ServiceFactory<Category> getService() {
        return serviceFactory;
    }

    @Override
    protected String getRequestValueKey() {
        return "categoryName";
    }
}
```
