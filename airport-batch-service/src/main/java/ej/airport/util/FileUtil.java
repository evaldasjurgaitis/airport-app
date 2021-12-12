package ej.airport.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
public class FileUtil {

    public static boolean deleteFile(String path) {
        try {
            Files.delete(Path.of(path));
            return !Files.exists(Path.of(path));
        } catch (IOException e) {
            log.error("Error while deleting file: {} error: {}", path, e.getStackTrace());
            return false;
        }
    }

}
