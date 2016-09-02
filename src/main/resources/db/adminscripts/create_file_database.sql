CREATE DATABASE filedb
  WITH OWNER = file_admin;

-- allow application to connect to database
GRANT CONNECT ON DATABASE filedb to file_application;