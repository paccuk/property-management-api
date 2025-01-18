CREATE SCHEMA IF NOT EXISTS property_management;

CREATE TABLE property
(
    property_id          UUID PRIMARY KEY,                               -- Auto-incremented ID (assuming it's unique)
    owner_id             VARCHAR(255) NOT NULL,                          -- Reference to owner, assuming it could be a string
    name                 VARCHAR(255) NOT NULL,                          -- Name of the property
    location_address     VARCHAR(255) NOT NULL,                          -- Address of the property
    location_city        VARCHAR(255) NOT NULL,                          -- City for the location
    location_postal_code VARCHAR(20)  NOT NULL,                          -- Postal code
    type                 VARCHAR(50)  NOT NULL,                          -- Type of the property (e.g., residential, commercial)
    status               VARCHAR(50)  NOT NULL,                          -- Property status (e.g., available, rented)
    rental_limit         INTEGER,                                        -- Rental limit, can be null if not set
    created_date         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP -- Timestamp for creation, default to current date
);

CREATE TABLE auth_user
(
    user_id  UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

CREATE TABLE user
(
    user_id      UUID PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    email VARCHAR(255) NOT NULL,
    phone        VARCHAR(255) NOT NULL,
    created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);