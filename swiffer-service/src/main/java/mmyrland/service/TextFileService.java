package mmyrland.service;

import mmyrland.domain.FileRecord;
import mmyrland.domain.TextFile;
import mmyrland.controller.dto.FileResultsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TextFileService {

    FileResultsDto process(File file) throws IOException;
    List<FileRecord> findRecordsByTextFileId(UUID textFileId);
    FileResultsDto getResults(String filename, UUID textFileId);
    TextFile findOneFile(UUID textFileId);
    Page<TextFile> findAllFiles(Pageable pageable);

}
