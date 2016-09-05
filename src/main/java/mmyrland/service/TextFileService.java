package mmyrland.service;

import mmyrland.controller.dto.FileResultsDto;
import mmyrland.domain.FileRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TextFileService {

    FileResultsDto process(File file) throws IOException;
    List<FileRecord> findByTextFileId(UUID textFileId);
    FileResultsDto getResults(String filename, UUID textFileId);

}
