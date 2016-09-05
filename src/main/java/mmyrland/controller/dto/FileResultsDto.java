package mmyrland.controller.dto;

import org.springframework.format.annotation.NumberFormat;

import java.util.LinkedHashMap;
import java.util.UUID;

public class FileResultsDto {

    private String fileName;
    private UUID textFileId;
    private int totalRecordCount;
    private int numberRecordCount;
    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private Double numberRecordPercentage;
    private Double numberRecordSum;
    private Double numberRecordMedian;
    private Double numberRecordAvg;
    private int stringRecordCount;
    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private Double stringRecordPercentage;
    private LinkedHashMap<String, Long> distinctStringRecords;

    public FileResultsDto() {
    }

    public FileResultsDto(String fileName, UUID textFileId, int numberRecordCount, Double numberRecordSum, Double numberRecordMedian, Double numberRecordAvg, Double numberRecordPercentage, Double stringRecordPercentage, LinkedHashMap<String, Long> distinctStringRecords) {
        this.fileName = fileName;
        this.textFileId = textFileId;
        this.numberRecordCount = numberRecordCount;
        this.numberRecordSum = numberRecordSum;
        this.numberRecordMedian = numberRecordMedian;
        this.numberRecordAvg = numberRecordAvg;
        this.numberRecordPercentage = numberRecordPercentage;
        this.stringRecordPercentage = stringRecordPercentage;
        this.distinctStringRecords = distinctStringRecords;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UUID getTextFileId() {
        return textFileId;
    }

    public void setTextFileId(UUID textFileId) {
        this.textFileId = textFileId;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public int getNumberRecordCount() {
        return numberRecordCount;
    }

    public void setNumberRecordCount(int numberRecordCount) {
        this.numberRecordCount = numberRecordCount;
    }

    public Double getNumberRecordSum() {
        return numberRecordSum;
    }

    public void setNumberRecordSum(Double numberRecordSum) {
        this.numberRecordSum = numberRecordSum;
    }

    public Double getNumberRecordMedian() {
        return numberRecordMedian;
    }

    public void setNumberRecordMedian(Double numberRecordMedian) {
        this.numberRecordMedian = numberRecordMedian;
    }

    public Double getNumberRecordAvg() {
        return numberRecordAvg;
    }

    public void setNumberRecordAvg(Double numberRecordAvg) {
        this.numberRecordAvg = numberRecordAvg;
    }

    public Double getNumberRecordPercentage() {
        return numberRecordPercentage;
    }

    public void setNumberRecordPercentage(Double numberRecordPercentage) {
        this.numberRecordPercentage = numberRecordPercentage;
    }

    public int getStringRecordCount() {
        return stringRecordCount;
    }

    public void setStringRecordCount(int stringRecordCount) {
        this.stringRecordCount = stringRecordCount;
    }

    public Double getStringRecordPercentage() {
        return stringRecordPercentage;
    }

    public void setStringRecordPercentage(Double stringRecordPercentage) {
        this.stringRecordPercentage = stringRecordPercentage;
    }

    public LinkedHashMap<String, Long> getDistinctStringRecords() {
        return distinctStringRecords;
    }

    public void setDistinctStringRecords(LinkedHashMap<String, Long> distinctStringRecords) {
        this.distinctStringRecords = distinctStringRecords;
    }

    @Override
    public String toString() {
        return "FileResultsDto{" +
                "fileName='" + fileName + '\'' +
                ", textFileId=" + textFileId +
                ", numberRecordSum=" + numberRecordSum +
                ", numberRecordMedian=" + numberRecordMedian +
                ", numberRecordAvg=" + numberRecordAvg +
                ", numberRecordPercentage=" + numberRecordPercentage +
                ", stringRecordPercentage=" + stringRecordPercentage +
                ", distinctStringRecords=" + distinctStringRecords +
                '}';
    }
}
