package com.example.demo.service.upload;

import com.example.demo.service.random.RandomStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUploaderService {

    private static final String FOLDER_TO_PROCESS = "fileStorage";

    @Autowired
    private RandomStringService randomStringService;

    public void uploadFile(MultipartFile file) {
        try {
            File uploadRootDir = new File(FOLDER_TO_PROCESS);
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }
            String clientFileName = file.getOriginalFilename();
            int clientFileNameSize = clientFileName.length();
            String imageFormat = clientFileName.substring(clientFileNameSize - 5, clientFileNameSize);
            String imageName = randomStringService.generateRandomString(45) + imageFormat.substring(imageFormat.indexOf("."));
            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + imageName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String file) {
        try {
            if (!(file == null || file.isEmpty())) {
                File[] listFiles = new File(FOLDER_TO_PROCESS).listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File aFile : listFiles) {
                        if (aFile.getName().contains(file)) {
                            Files.delete(Paths.get(FOLDER_TO_PROCESS + "/" + aFile.getName()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
