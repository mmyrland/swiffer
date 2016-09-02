package mmyrland.service;

import mmyrland.repository.FileRecord;
import mmyrland.repository.NumberRecord;
import mmyrland.repository.StringRecord;
import mmyrland.repository.TextFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mmyrland.repository.FileRecordRepository;
import mmyrland.repository.TextFileRepository;
import mmyrland.utils.FileRecordUtils;

import java.io.*;
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

    public TextFile process(File file) throws IOException {
        List<FileRecord> fileRecords = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.lines().forEach(l -> {
            if (FileRecordUtils.isNumberRecord(l)) {
                //sets file_record_type = NUMBER
                NumberRecord numberRecord = new NumberRecord();
                numberRecord.setRecordText(l);
                numberRecord.setNumberVal(Double.parseDouble(l));
                fileRecords.add(numberRecord);
            } else {
                //sets file_record_type = STRING
                StringRecord stringRecord = new StringRecord();
                stringRecord.setRecordText(l);
                fileRecords.add(stringRecord);
            }
        });

        byte[] content = Files.readAllBytes(file.toPath());

        return fileRepository.save(
                new TextFile(fileRecords, content)
        );
    }

    public List<FileRecord> findByTextFileId(UUID textFileId) {
        return fileRecordRepository.findByTextFileId(textFileId);
    }

    public Double getSumInFile(UUID textFileId){
        List<Double> numberVals = this.extractDouble(this.findByTextFileId(textFileId));

        return FileRecordUtils.buildSum(numberVals);
    }

    public int getCountNumbers(UUID textFileId){
        List<Double> numberVals =
                this.extractDouble(
                        this.findByTextFileId(textFileId)
                );
        return numberVals.size();
    }

    public List<Double> extractDouble(List<FileRecord> numberRecords) {
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
}
