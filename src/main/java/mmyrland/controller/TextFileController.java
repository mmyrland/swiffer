package mmyrland.controller;

import mmyrland.controller.dto.FileResultsDto;
import mmyrland.domain.TextFile;
import mmyrland.service.TextFileService;
import mmyrland.utils.LoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/file")
public class TextFileController {

    @Autowired
    TextFileService textFileService;

    @Autowired
    LoadUtil loadUtil;

    @RequestMapping(value = "/load", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity loadFile(@RequestParam("file") MultipartFile inputFile) throws IOException {

    // Should strongly typed response and
    // use global exception handler or internal exception library
    // if data is consumed downstream

        if(!inputFile.getOriginalFilename().endsWith(".txt")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type; please try again with .txt file.");
        }
        File file = loadUtil.loadFile(inputFile);
        FileResultsDto response = textFileService.process(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @RequestMapping(value = "/{textFileId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TextFile> findOneFile(@PathVariable UUID textFileId) throws IOException {

        TextFile response = textFileService.findOneFile(textFileId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<TextFile>> findAllFiles(Pageable pageable) throws IOException {

        Page<TextFile> response = textFileService.findAllFiles(pageable);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
