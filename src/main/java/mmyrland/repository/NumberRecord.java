package mmyrland.repository;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NUMBER")
public class NumberRecord extends FileRecord {

    @Column(name = "number_val")
    private Double numberVal;

    public NumberRecord(String recordText, Double numberVal) {
        this.setRecordText(recordText);
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
