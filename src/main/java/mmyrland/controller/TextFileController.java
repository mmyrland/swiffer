package mmyrland.controller;

import mmyrland.repository.TextFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import mmyrland.service.TextFileService;
import mmyrland.utils.LoadUtil;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
public class TextFileController {

    @Autowired
    TextFileService textFileService;

    @Autowired
    LoadUtil loadUtil;

    @RequestMapping(value = "/load",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<TextFile> loadFile(@RequestParam("file") MultipartFile inputFile) throws IOException{

            File file = loadUtil.loadFile(inputFile);
            TextFile response = textFileService.process(file);


        return ResponseEntity.accepted().body(response);
    }

//    @RequestMapping(value = "/gettotal/{textFileId}",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity loadFile(@PathVariable UUID textFileId){
//
//        try {
//            textFileService.getTotal(UUID textFileId);
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//        }
//
//        return ResponseEntity.accepted().build();
//    }
}
