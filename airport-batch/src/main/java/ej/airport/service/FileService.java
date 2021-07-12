package ej.airport.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileService {

    public File store(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IOException("No file selected");
        }

        String path = new ClassPathResource("tmp/").getURL().getPath();
        File file = new File(path + multipartFile.getOriginalFilename());
        OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(multipartFile.getInputStream(), outputStream);
        outputStream.flush();
        outputStream.close();

        return file;
    }

}
