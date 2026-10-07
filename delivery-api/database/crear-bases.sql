-- Una base de datos por microservicio.
-- Ejecutar con un usuario con permisos (por defecto: postgres).
--
-- Opcion A (terminal, recomendada):
--   psql -U postgres -f database/crear-bases.sql
--
-- Opcion B (pgAdmin): ejecutar cada CREATE DATABASE por separado
--   (PostgreSQL no permite varios CREATE DATABASE en una misma ejecucion).
--
-- Si alguna ya existe, ese CREATE mostrara un error inofensivo; las demas se crean igual.

CREATE DATABASE usuarios_db;
CREATE DATABASE comercios_db;
CREATE DATABASE pedidos_db;
