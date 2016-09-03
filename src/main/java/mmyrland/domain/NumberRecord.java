package mmyrland.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("NUMBER")
public class NumberRecord extends FileRecord {

    @Column(name = "number_val")
    private Double numberVal;

    public NumberRecord(String recordText, Double numberVal) {
        this.setFileRecordId(UUID.randomUUID());
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
        this.setRecordText(recordText);
        this.numberVal = numberVal;
    }

    public NumberRecord() {
        this.setFileRecordId(UUID.randomUUID());
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
    }

    public Double getNumberVal() {
        return numberVal;
    }

    public void setNumberVal(Double numberVal) {
        this.numberVal = numberVal;
    }




}
