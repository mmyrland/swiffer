package mmyrland.repository;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "file_record")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_record_type", discriminatorType = DiscriminatorType.STRING)
public abstract class FileRecord {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "file_record_id")
    private UUID fileRecordId;
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @ManyToOne
    @JoinColumn(name = "text_file_id",insertable = false,updatable = false)
    @MapsId
    private TextFile textFile;
    @Column(name = "record_text")
    private String recordText;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "date_created")
    @CreatedDate
    private DateTime dateCreated;

    public void setTextFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public TextFile getTextFile() {
        return textFile;
    }

    public void setTextFile(DateTime dateCreated, TextFile textFile) {
        this.textFile = textFile;
    }

    public UUID getFileRecordId() {
        return fileRecordId;
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
