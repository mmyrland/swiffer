package domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "text_file")
public class TextFile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "text_file_id")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID textFileId;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "date_created")
    private DateTime dateCreated;
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST},mappedBy = "textFileId")
    @OrderColumn(name = "record_text")
    private List<FileRecord> fileRecords;
    private byte[] content;

    public TextFile(DateTime dateCreated, List<FileRecord> fileRecords, byte[] content) {
        this.dateCreated = dateCreated;
        this.fileRecords = fileRecords;
        this.content = content;
    }

    public TextFile(List<FileRecord> fileRecords, byte[] content) {
        this.fileRecords = fileRecords;
        this.content = content;
    }

    public TextFile() {
    }

    public UUID getTextFileId() {
        return textFileId;
    }

    public void setTextFileId(UUID textFileId) {
        this.textFileId = textFileId;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<FileRecord> getFileRecords() {
        return fileRecords;
    }

    public void setFileRecords(List<FileRecord> fileRecords) {
        this.fileRecords = fileRecords;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
