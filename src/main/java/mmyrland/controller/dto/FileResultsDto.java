package mmyrland.controller.dto;

import java.util.HashSet;

public class FileResultsDto {

    private String fileName;
    private Double numberRecordSum;
    private Double numberRecordMedian;
    private Double numberRecordAvg;
    private Double numberRecordPercentage;
    private Double stringRecordPercentage;
    private HashSet<String> distinctStringRecords;
}
