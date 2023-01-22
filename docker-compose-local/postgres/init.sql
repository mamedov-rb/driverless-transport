\connect chat_db;
CREATE SCHEMA chat_app_schema;

CREATE DATABASE keycloak_chat_db;
CREATE USER keycloak_chat_db_admin WITH password 'zxADqr';
GRANT ALL ON DATABASE keycloak_chat_db TO keycloak_chat_db_admin;
\connect keycloak_chat_db;
CREATE SCHEMA keycloak_chat_schema;
GRANT ALL ON SCHEMA keycloak_chat_schema TO keycloak_chat_db_admin;
