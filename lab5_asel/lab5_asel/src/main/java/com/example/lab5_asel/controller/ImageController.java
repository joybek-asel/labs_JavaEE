//package com.example.lab5_asel.controller;
//
//
//import com.example.lab5_asel.model.Image;
//import com.example.lab5_asel.repository.ImageRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.ByteArrayInputStream;
//
//@RestController
//@RequiredArgsConstructor
//public class ImageController {
//    private final ImageRepository imageRepository;
//
//    @GetMapping("/image/{id}")
//    private ResponseEntity<?> getImageById(@PathVariable long id) {
//        Image image = imageRepository.findById(id).orElse(null);
//        return ResponseEntity.ok()
//                .header("filname", image.getOriginalFileName())
//                .contentType(MediaType.valueOf(image.getContentType()))
//                .contentLength(image.getSize())
//                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
//    }
//}
