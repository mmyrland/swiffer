package mmyrland.service;

import mmyrland.controller.dto.FileResultsDto;
import mmyrland.domain.FileRecord;
import mmyrland.domain.NumberRecord;
import mmyrland.domain.StringRecord;
import mmyrland.domain.TextFile;
import mmyrland.repository.FileRecordRepository;
import mmyrland.repository.TextFileRepository;
import mmyrland.utils.FileRecordUtils;
import org.apache.commons.math3.util.Precision;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//NOTE: file size cannot exceed 2GB; need input stream for large files.
        byte[] content = Files.readAllBytes(file.toPath());
        TextFile savedFile = fileRepository.save(
                new TextFile(UUID.randomUUID(),DateTime.now(DateTimeZone.UTC), file.getName(), content)
        );

        List<FileRecord> fileRecords = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader
                .lines()
                .forEach(l -> {
                    if (FileRecordUtils.isNumberRecord(l)) {
//sets file_record_type = NUMBER per @DescriminatorType annotation on entities
                        NumberRecord numberRecord = new NumberRecord();
                        numberRecord.setFileRecordId(UUID.randomUUID());
                        numberRecord.setDateCreated(DateTime.now(DateTimeZone.UTC));
                        numberRecord.setTextFileId(savedFile.getTextFileId());
                        numberRecord.setTextFile(savedFile);
                        numberRecord.setNumberVal(Precision.round(Double.parseDouble(l), 2));
                        fileRecords.add(numberRecord);
                    } else {
                        //sets file_record_type = STRING
                        StringRecord stringRecord = new StringRecord();
                        stringRecord.setFileRecordId(UUID.randomUUID());
                        stringRecord.setDateCreated(DateTime.now(DateTimeZone.UTC));
                        stringRecord.setTextFileId(savedFile.getTextFileId());
                        stringRecord.setTextFile(savedFile);
                        stringRecord.setRecordText(l);
                        fileRecords.add(stringRecord);
                    }
                });

        fileRecordRepository.save(fileRecords);

        return getResults(savedFile.getTextFileName(), savedFile.getTextFileId());
    }

    @Override
    public FileResultsDto getResults(String filename, UUID textFileId) {


 //retrieves records for saved file by file id
        List<FileRecord> fileRecords = findRecordsByTextFileId(textFileId);

        Integer numberCount = extractDouble(fileRecords).size();
        Integer stringCount = extractStringRecords(fileRecords).size();

        FileResultsDto fileResultsDto = new FileResultsDto();
        fileResultsDto.setFileName(filename);
        fileResultsDto.setTextFileId(textFileId);
        fileResultsDto.setTotalRecordCount(fileRecords.size());
        fileResultsDto.setNumberRecordCount(numberCount);

        fileResultsDto.setNumberRecordPercentage(
                FileRecordUtils.getPercentage(
                        fileRecords.size(), numberCount
                )
        );
        fileResultsDto.setNumberRecordAvg(getAverageInFile(fileRecords));
        fileResultsDto.setNumberRecordMedian(getMedianInFile(fileRecords));
        fileResultsDto.setNumberRecordSum(getSumInFile(fileRecords));
        fileResultsDto.setStringRecordCount(stringCount);

        fileResultsDto.setStringRecordPercentage(
                FileRecordUtils.getPercentage(
                        fileRecords.size(), stringCount
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
    public List<FileRecord> findRecordsByTextFileId(UUID textFileId) {
        return fileRecordRepository.findByTextFileId(textFileId);
    }

    @Override
    public TextFile findOneFile(UUID textFileId){
        return fileRepository.findOne(textFileId);
    }

    @Override
    public Page<TextFile> findAllFiles(Pageable pageable){
        return fileRepository.findAll(pageable);
    }

    protected Double getSumInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildSum(numberVals), 2);
    }

    protected Double getMedianInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildMedian(numberVals), 2);
    }

    protected Double getAverageInFile(List<FileRecord> fileRecords) {
        List<Double> numberVals = this.extractDouble(fileRecords);

        return Precision.round(FileRecordUtils.buildAverage(numberVals), 2);
    }


    protected List<Double> extractDouble(List<FileRecord> numberRecords) {
        List<Double> doubles = new ArrayList<>();
        numberRecords.stream().forEach(n -> {
                    if (n instanceof NumberRecord) {
//Only checks NumberRecord objects
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
//Only adds StringRecord objects
                    if (n instanceof StringRecord) {
                        textLines.add(((StringRecord)n).getRecordText());
                    }
                }
        );

        return textLines;
    }
}
