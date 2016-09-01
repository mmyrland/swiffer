package utils;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class LoadUtil {
    public File loadFile(MultipartFile multipartFile){
        File textFile = new File(multipartFile.getOriginalFilename());

        if (!multipartFile.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(textFile));
                FileCopyUtils.copy(multipartFile.getInputStream(), stream);
                stream.close();
                return textFile;
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Unable to read stream.");
            }
        }
        else {
            throw new IllegalArgumentException("Empty file.");
        }
    }
}
