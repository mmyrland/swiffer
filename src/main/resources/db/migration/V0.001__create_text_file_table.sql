CREATE TABLE text_file (
  text_file_id UUID NOT NULL,
  text_file_name TEXT NOT NULL,
  date_created TIMESTAMP NOT NULL,
  content           BYTEA     NULL,
  PRIMARY KEY (text_file_id)
);

-- records in this table can be inserted and read by the application
GRANT SELECT, INSERT, UPDATE ON TABLE text_file to file_application;
-- but not changed
REVOKE DELETE, TRUNCATE ON TABLE text_file FROM file_application;