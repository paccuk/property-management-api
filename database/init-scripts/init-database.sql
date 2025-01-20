CREATE SCHEMA IF NOT EXISTS property_management;

CREATE TABLE IF NOT EXISTS "property"
(
    property_id          UUID PRIMARY KEY,
    owner_id             VARCHAR(255) NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    location_address     VARCHAR(255) NOT NULL,
    location_city        VARCHAR(255) NOT NULL,
    location_postal_code VARCHAR(20)  NOT NULL,
    type                 VARCHAR(50)  NOT NULL,
    status               VARCHAR(50)  NOT NULL,
    rental_limit         INTEGER,
    created_date         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "auth_user"
(
    user_id  UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS "user"
(
    user_id      UUID PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    phone        VARCHAR(255) NOT NULL,
    created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);