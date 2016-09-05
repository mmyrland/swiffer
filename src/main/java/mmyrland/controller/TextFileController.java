package mmyrland.controller;

import mmyrland.controller.dto.FileResultsDto;
import mmyrland.service.TextFileService;
import mmyrland.utils.LoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
public class TextFileController {

    @Autowired
    TextFileService textFileService;

    @Autowired
    LoadUtil loadUtil;

    @RequestMapping(value = "/load", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileResultsDto> loadFile(@RequestParam("file") MultipartFile inputFile) throws IOException {

        File file = loadUtil.loadFile(inputFile);
        FileResultsDto response = textFileService.process(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
