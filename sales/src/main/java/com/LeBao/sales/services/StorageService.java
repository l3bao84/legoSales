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
import java.util.ArrayList;
import java.util.List;
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

    public List<String> uploadFileToFileSystem(MultipartFile[] files) throws IOException {
        if(files != null && files.length > 1) {
            List<String> filePaths = new ArrayList<>();
            for (MultipartFile file:files) {
                String filePath = FOLDER_PATH + generateNewFileName(file);
                file.transferTo(new File(filePath));
                filePaths.add(filePath);
            }
            return filePaths;
        }else {
            return null;
        }
    }

    public byte[] downloadImageFromFileSystem(Long productId) throws IOException {
        Product dbImageData = productRepository.findById(productId).get();
        String filePath = dbImageData.getImages().get(0);
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


    public byte[] downloadImageFromFileSystemByFileName(String fileName) throws IOException {
        String filePath = FOLDER_PATH + fileName;
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
