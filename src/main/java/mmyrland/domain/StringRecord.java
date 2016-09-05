package mmyrland.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("STRING")
public class StringRecord extends FileRecord {

    public StringRecord(String recordText) {
        this.setFileRecordId(UUID.randomUUID());
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
        this.setRecordText(recordText);
    }

    public StringRecord() {
        this.setFileRecordId(UUID.randomUUID());
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
    }
}
