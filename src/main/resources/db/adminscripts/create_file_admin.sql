-- run these as superuser (e.g., postgres) on your database

-- client_application is the user that can do CRUD operations on the database
CREATE ROLE file_application LOGIN
PASSWORD 'file_application';

-- client_admin is the user that performs schema changes. Flyway uses this account.
CREATE ROLE file_admin LOGIN
PASSWORD 'file_admin';

-- NOTE! On RDS databases you need to run the following
-- assuming "postgres" is the user you've logged into postgres with
GRANT file_admin to postgres;