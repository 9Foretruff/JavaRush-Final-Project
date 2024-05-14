package com.javarush.jira.bugtracking.attachment;

import com.javarush.jira.common.error.IllegalRequestDataException;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@UtilityClass
public class FileUtil {
    private static final String ATTACHMENT_PATH_PATTERN = "./attachments/%s/";

    public static void upload(MultipartFile multipartFile, String directoryPath, String fileName) {
        Objects.requireNonNull(multipartFile, "Multipart file must not be null");
        Objects.requireNonNull(directoryPath, "Directory path must not be null");
        Objects.requireNonNull(fileName, "File name must not be null");

        if (multipartFile.isEmpty()) {
            throw new IllegalRequestDataException("Select a file to upload.");
        }

        Path directoryPathObj = Paths.get(directoryPath);
        Path filePathObj = directoryPathObj.resolve(fileName);

        try {
            Files.createDirectories(directoryPathObj);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, filePathObj, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalRequestDataException("Failed to upload file " + multipartFile.getOriginalFilename(), ex);
        }
    }

    public static Resource download(String fileLink) {
        Objects.requireNonNull(fileLink, "File link must not be null");

        Path path = Paths.get(fileLink);

        try {
            if (Files.exists(path) && Files.isReadable(path)) {
                return new UrlResource(path.toUri());
            } else {
                throw new IllegalRequestDataException("Failed to download file " + path.getFileName());
            }
        } catch (IOException ex) {
            throw new IllegalRequestDataException("Failed to download file " + path.getFileName(), ex);
        }
    }

    public static void delete(String fileLink) {
        Objects.requireNonNull(fileLink, "File link must not be null");

        Path path = Paths.get(fileLink);

        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            throw new IllegalRequestDataException("File " + fileLink + " deletion failed.", ex);
        }
    }

    public static String getPath(String titleType) {
        Objects.requireNonNull(titleType, "Title type must not be null");
        return String.format(ATTACHMENT_PATH_PATTERN, titleType.toLowerCase());
    }
}
