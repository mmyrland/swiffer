package domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "file_record")
@DiscriminatorColumn(name = "file_record_type", discriminatorType = DiscriminatorType.STRING)
public abstract class FileRecord {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "file_record_id")
    private UUID fileRecordId;
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "text_file_id")
    @JoinColumn(name = "text_file_id",insertable = false,updatable = false,referencedColumnName = "text_file_id")
    private TextFile textFile;
    @Column(name = "record_text")
    private String recordText;

    public TextFile getTextFile() {
        return textFile;
    }

    public void setTextFile(TextFile textFile) {
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
}
