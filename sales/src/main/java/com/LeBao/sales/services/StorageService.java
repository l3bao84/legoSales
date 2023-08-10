package com.LeBao.sales.services;


import com.LeBao.sales.models.Product;
import com.LeBao.sales.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Autowired
    private ProductRepository productRepository;
    private final String FOLDER_PATH = "C:\\Users\\HELLO\\OneDrive\\Documents\\Projects\\sales\\sales\\src\\main\\resources\\images\\";

    public String generateNewFileName(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return UUID.randomUUID().toString() + "." + extension;
    }

    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + generateNewFileName(file);
        file.transferTo(new File(filePath));
        return filePath;
    }

    public byte[] downloadImageFromFileSystem(Long productId) throws IOException {
        Product dbImageData = productRepository.findById(productId).get();
        String filePath = dbImageData.getImage();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public byte[] downloadImageFromFileSystemByFileName(String fileName) throws IOException {
        String filePath = FOLDER_PATH + fileName;
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}