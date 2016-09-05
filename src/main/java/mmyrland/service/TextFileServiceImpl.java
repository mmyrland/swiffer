package mmyrland.service;

import mmyrland.controller.dto.FileResultsDto;
import mmyrland.domain.FileRecord;
import mmyrland.domain.NumberRecord;
import mmyrland.domain.StringRecord;
import mmyrland.domain.TextFile;
import mmyrland.repository.*;
import mmyrland.utils.FileRecordUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TextFileServiceImpl implements TextFileService {

    @Autowired
    TextFileRepository fileRepository;
    @Autowired
    FileRecordRepository fileRecordRepository;

    @Override
    public FileResultsDto process(File file) throws IOException {

        byte[] content = Files.readAllBytes(file.toPath());
        TextFile savedFile = fileRepository.save(new TextFile(file.getName(), content));

//        TextFile textFile = new TextFile(content);
        List<FileRecord> fileRecords = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.lines().forEach(l -> {
            if (FileRecordUtils.isNumberRecord(l)) {
                //sets file_record_type = NUMBER
                NumberRecord numberRecord = new NumberRecord();
                numberRecord.setTextFileId(savedFile.getTextFileId());
                numberRecord.setTextFile(savedFile);
                numberRecord.setRecordText(l);
                numberRecord.setNumberVal(Precision.round(Double.parseDouble(l),2));
                fileRecords.add(numberRecord);
            } else {
                //sets file_record_type = STRING
                StringRecord stringRecord = new StringRecord();
                stringRecord.setTextFileId(savedFile.getTextFileId());
                stringRecord.setTextFile(savedFile);
                stringRecord.setRecordText(l);
                fileRecords.add(stringRecord);
            }
        });

//        textFile.setFileRecords(fileRecords);
//        return textFile;
        fileRecordRepository.save(fileRecords);

        return getResults(savedFile.getTextFileName(),savedFile.getTextFileId());
    }

    @Override
    public FileResultsDto getResults(String filename,UUID textFileId){
        List<FileRecord> fileRecords = findByTextFileId(textFileId);

        Integer numberCount = extractDouble(fileRecords).size();
        Integer stringCount = extractStringRecords(fileRecords).size();

        FileResultsDto fileResultsDto = new FileResultsDto();
        fileResultsDto.setFileName(filename);
        fileResultsDto.setTextFileId(textFileId);
        fileResultsDto.setTotalRecordCount(fileRecords.size());
        fileResultsDto.setNumberRecordCount(numberCount);

        fileResultsDto.setNumberRecordPercentage(
                FileRecordUtils.getPercentage(
                        fileRecords.size(),numberCount
                )
        );
        fileResultsDto.setNumberRecordAvg(getAverageInFile(fileRecords));
        fileResultsDto.setNumberRecordMedian(getMedianInFile(fileRecords));
        fileResultsDto.setNumberRecordSum(getSumInFile(fileRecords));
        fileResultsDto.setStringRecordCount(stringCount);

        fileResultsDto.setStringRecordPercentage(
                FileRecordUtils.getPercentage(
                        fileRecords.size(),stringCount
                )
        );
        fileResultsDto.setDistinctStringRecords(
                FileRecordUtils.buildSortStringSet(
                        extractStringRecords(fileRecords)
                )
        );

        return fileResultsDto;

    }

    @Override
    public List<FileRecord> findByTextFileId(UUID textFileId) {
        return fileRecordRepository.findByTextFileId(textFileId);
    }

    protected Double getSumInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildSum(numberVals),2);
    }

    protected Double getMedianInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildMedian(numberVals),2);
    }

    protected Double getAverageInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildAverage(numberVals),2);
    }



    protected List<Double> extractDouble(List<FileRecord> numberRecords) {
        List<Double> doubles = new ArrayList<>();
        numberRecords.stream().forEach(n -> {
                    if (n instanceof NumberRecord) {
                        doubles.add(
                                ((NumberRecord) n).getNumberVal()
                        );
                    }
                }
        );

        return doubles;
    }

    protected List<String> extractStringRecords(List<FileRecord> stringRecords) {
        List<String> textLines = new ArrayList<>();
        stringRecords.stream().forEach(n -> {
                    if (n instanceof StringRecord) {
                        textLines.add(n.getRecordText());
                    }
                }
        );

        return textLines;
    }
}
