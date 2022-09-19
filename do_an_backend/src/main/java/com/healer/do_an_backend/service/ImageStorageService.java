package com.healer.do_an_backend.service;

import com.healer.do_an_backend.service.Interface.IStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class ImageStorageService implements IStorageService {

    private final Path storageFolder = Paths.get("uploads");

    public ImageStorageService() {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException e) {
            throw new RuntimeException("Cannot initialize storage", e);
        }
    }

    public boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert fileExtension != null;
        return Arrays.asList(new String[]{"png", "ipg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storageFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }

            if (!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file");
            }

            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <= 5mb");
            }

            // rename file
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String genFileName = UUID.randomUUID().toString().replace("-", "");
            genFileName = genFileName + "." + fileExtension;

            Path destinationFilePath = this.storageFolder.resolve(
                    Paths.get(genFileName)).normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return genFileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public List<String> storageFiles(MultipartFile[] files) {
        List<String> arr = new ArrayList<>();
        for (MultipartFile file : files) {
            String genFileName = storageFile(file);
            arr.add(genFileName);
        }
        return arr;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.storageFolder, 1)
                    .filter(path -> {
                        // path.toString().contains("product")==true
                        return !path.equals(this.storageFolder);
                    })
                    .map(this.storageFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload storage file", e);
        }
    }

    @Override
    public byte[] readFileContend(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file:" + fileName, e);
        }

    }

    @Override
    public void deleteAllFiles() {

    }
}
