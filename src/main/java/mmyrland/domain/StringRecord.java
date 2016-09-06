package mmyrland.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("STRING")
public class StringRecord extends FileRecord {

    @Column(name = "record_text")
    private String recordText;

    public StringRecord(UUID fileRecordId, DateTime dateCreated,String recordText) {
        this.setFileRecordId(fileRecordId);
        this.setDateCreated(dateCreated);
        this.recordText =recordText;
    }

    public StringRecord() {
        this.setFileRecordId(UUID.randomUUID());
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }
}
