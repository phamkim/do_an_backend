package com.healer.do_an_backend.controller;


import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "api/v1/file_storage")
public class FileStorageController {

    @Autowired
    private IStorageService storageService;

    @PostMapping()
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String genFileName = storageService.storageFile(file);
            Map<String, String> jsonObject = new HashMap<>();
            jsonObject.put("image", genFileName);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", jsonObject));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }


    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContend(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseObject> getUploadFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        return MvcUriComponentsBuilder.fromMethodName(FileStorageController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                    }).collect(Collectors.toList());
            Map<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("images", urls);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", jsonObject));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
