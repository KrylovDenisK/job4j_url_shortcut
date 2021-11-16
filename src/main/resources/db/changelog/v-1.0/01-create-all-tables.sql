CREATE TABLE sites (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    login           VARCHAR(255),
    password        VARCHAR(255),
    registration    BOOLEAN DEFAULT TRUE
);

CREATE TABLE urls (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    code        VARCHAR(10),
    site_id     INTEGER REFERENCES sites(id),
    total       INTEGER DEFAULT 0
)