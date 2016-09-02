package mmyrland.repository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STRING")
public class StringRecord extends FileRecord {

    public StringRecord(String recordText) {
        this.setRecordText(recordText);
    }

    public StringRecord() {
    }
}
