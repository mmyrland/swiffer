package mmyrland.service;

import mmyrland.repository.FileRecord;
import mmyrland.repository.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TextFileService {

    TextFile process(File file) throws IOException;
    List<FileRecord> findByTextFileId(UUID textFileId);
    Double getSumInFile(UUID textFileId);
    int getCountNumbers(UUID textFileId);

}
