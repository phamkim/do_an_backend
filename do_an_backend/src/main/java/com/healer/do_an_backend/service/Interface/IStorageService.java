package com.healer.do_an_backend.service.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IStorageService {
    public String storageFile(MultipartFile file);

    public List<String> storageFiles(MultipartFile[] files);

    public Stream<Path> loadAll();

    public byte[] readFileContend(String fileName);

    public void deleteAllFiles();
}
