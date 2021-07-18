package ej.airport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    public String store(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("No file selected");
        }

        Path root = Paths.get(uploadPath);

        try {
            if (!Files.exists(root)) {
                init();
            }
            Files.deleteIfExists(root.resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        return root.resolve(file.getOriginalFilename()).toAbsolutePath().toString();
    }

}
