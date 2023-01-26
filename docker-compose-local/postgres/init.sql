\connect transport;

CREATE DATABASE keycloak;
CREATE USER keycloak_admin WITH password 'zxADqr';
GRANT ALL ON DATABASE keycloak TO keycloak_admin;
\connect keycloak;
CREATE SCHEMA keycloak_schema;
GRANT ALL ON SCHEMA keycloak_schema TO keycloak_admin;
