package mmyrland.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "file_record")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_record_type", discriminatorType = DiscriminatorType.STRING)
public abstract class FileRecord {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "file_record_id")
    private UUID fileRecordId;
    @ManyToOne
    @JoinColumn(name = "text_file_id",insertable = false,updatable = false,referencedColumnName = "text_file_id")
    private TextFile textFile;
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "text_file_id",nullable = false)
    private UUID textFileId;
    @Column(name = "record_text")
    private String recordText;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "date_created")
    private DateTime dateCreated;

    public UUID getFileRecordId() {
        return fileRecordId;
    }

    public void setFileRecordId(UUID fileRecordId) {
        this.fileRecordId = fileRecordId;
    }

    public TextFile getTextFile() {
        return textFile;
    }

    public void setTextFile(TextFile textFile) {
        this.textFile = textFile;
    }


    public UUID getTextFileId() {
        return textFileId;
    }

    public void setTextFileId(UUID textFileId) {
        this.textFileId = textFileId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FileRecord)) return false;

        FileRecord that = (FileRecord) o;

        return new EqualsBuilder()
                .append(fileRecordId, that.fileRecordId)
                .append(textFile, that.textFile)
                .append(textFileId, that.textFileId)
                .append(recordText, that.recordText)
                .append(dateCreated, that.dateCreated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fileRecordId)
                .append(textFile)
                .append(textFileId)
                .append(recordText)
                .append(dateCreated)
                .toHashCode();
    }
}
