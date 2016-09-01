package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STRING")
public class StringRecord extends FileRecord {

    public StringRecord(TextFile textFile, String recordText) {
        this.setTextFile(textFile);
        this.setRecordText(recordText);
    }

    public StringRecord() {
    }
}
