CREATE TABLE IF NOT EXISTS accounts
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    username     VARCHAR(100) NOT NULL,
    password     VARCHAR(100)  NOT NULL,
    role         VARCHAR(20)  NOT NULL,
    phone        VARCHAR(100) NOT NULL,
    created_date TIMESTAMP    default CURRENT_TIMESTAMP,
    updated_date TIMESTAMP    default CURRENT_TIMESTAMP,
    CONSTRAINT UK_user_username UNIQUE (username),
    CONSTRAINT UK_user_phone UNIQUE (phone),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    start_location VARCHAR(100)   NOT NULL,
    end_location   VARCHAR(100)   NOT NULL,
    created_date   TIMESTAMP      default CURRENT_TIMESTAMP,
    updated_date   TIMESTAMP      default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS trips
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    price          NUMERIC(10, 2) NOT NULL,
    order_id       BIGINT         NOT NULL,
    offer_uuid     UUID           NOT NULL,
    created_date   TIMESTAMP      default CURRENT_TIMESTAMP,
    updated_date   TIMESTAMP      default CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT FK_trip_order FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS account_trips
(
    account_id BIGINT NOT NULL,
    trip_id BIGINT NOT NULL,
    PRIMARY KEY (account_id, trip_id),
    CONSTRAINT FK_account_trips_account FOREIGN KEY (account_id) REFERENCES accounts (id),
    CONSTRAINT FK_account_trips_trip FOREIGN KEY (trip_id) REFERENCES trips (id)
);
