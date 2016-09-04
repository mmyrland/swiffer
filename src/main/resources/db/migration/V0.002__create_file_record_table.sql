CREATE TABLE file_record (
  file_record_id UUID NOT NULL,
  text_file_id UUID NOT NULL REFERENCES text_file,
  file_record_type VARCHAR(10) NOT NULL CHECK (file_record_type in ('STRING', 'NUMBER')),
  record_text TEXT NULL,
  number_val DOUBLE PRECISION NULL,
  date_created TIMESTAMP NOT NULL,
  PRIMARY KEY (file_record_id)
);

-- records in this table can be inserted and read by the application
GRANT SELECT, INSERT, UPDATE ON TABLE file_record to file_application;
-- but not changed
REVOKE DELETE, TRUNCATE ON TABLE file_record FROM file_application;