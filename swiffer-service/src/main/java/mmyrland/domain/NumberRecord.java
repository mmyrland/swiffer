package mmyrland.domain;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("NUMBER")
public class NumberRecord extends FileRecord {

    @Column(name = "number_val")
    private Double numberVal;

    public NumberRecord(UUID fileRecordId,DateTime dateCreated, Double numberVal) {
        this.setFileRecordId(fileRecordId);
        this.setDateCreated(dateCreated);
        this.numberVal = numberVal;
    }

    public NumberRecord() {
        }

    public Double getNumberVal() {
        return numberVal;
    }

    public void setNumberVal(Double numberVal) {
        this.numberVal = numberVal;
    }




}
