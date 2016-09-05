package mmyrland.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "text_file")
public class TextFile {
    @Id
    @Column(name = "text_file_id",unique = true)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID textFileId;
    @Column(name = "text_file_name")
    private String textFileName;
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST}, mappedBy = "textFile")
    @OrderColumn(name = "record_text")
    private List<FileRecord> fileRecords;
    private byte[] content;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "date_created")
    private DateTime dateCreated;

    public TextFile(String textFileName,List<FileRecord> fileRecords, byte[] content, DateTime dateCreated) {
        this.textFileId = UUID.randomUUID();
        this.textFileName = textFileName;
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
        this.fileRecords = fileRecords;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public TextFile(String textFileName, List<FileRecord> fileRecords, byte[] content) {
        this.textFileId = UUID.randomUUID();
        this.textFileName = textFileName;
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
        this.fileRecords = fileRecords;
        this.content = content;
    }

    public TextFile(String textFileName, byte[] content) {
        this.textFileId = UUID.randomUUID();
        this.textFileName = textFileName;
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
        this.content = content;
    }

    public TextFile() {
        this.textFileId = UUID.randomUUID();
        this.setDateCreated(DateTime.now(DateTimeZone.UTC));
    }

    public UUID getTextFileId() {
        return textFileId;
    }

    public void setTextFileId(UUID textFileId) {
        this.textFileId = textFileId;
    }

    public String getTextFileName() {
        return textFileName;
    }

    public void setTextFileName(String textFileName) {
        this.textFileName = textFileName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TextFile)) return false;

        TextFile textFile = (TextFile) o;

        return new EqualsBuilder()
                .append(textFileId, textFile.textFileId)
                .append(textFileName, textFile.textFileName)
                .append(fileRecords, textFile.fileRecords)
                .append(content, textFile.content)
                .append(dateCreated, textFile.dateCreated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(textFileId)
                .append(textFileName)
                .append(fileRecords)
                .append(content)
                .append(dateCreated)
                .toHashCode();
    }
}
