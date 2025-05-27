package com.example.Appointment.Booking.System.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageHandle {

    public static String uploadImage(MultipartFile file, String whichDir) {
        if(file == null || file.isEmpty()) return null;
        String uploadDir;
        if(whichDir.equals(AppConstants.LAB_IMAG)) uploadDir = AppConstants.LAB_IMAGE_UPLOAD_DIR;
        else if(whichDir.equals(AppConstants.LAB_TEST_IMAG)) uploadDir = AppConstants.LAB_TEST_IMAGE_UPLOAD_DIR;
        else if(whichDir.equals(AppConstants.USER_IMAG)) uploadDir = AppConstants.USER_IMAGE_UPLOAD_DIR;
        else return null;
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        int dotIndex;
        if(originalFileName != null) dotIndex = originalFileName.lastIndexOf(".");
        else return null;
        if (dotIndex >= 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String uniqueFileName = UUID.randomUUID() + fileExtension; // make unique file name

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                System.out.println("Exception from image handle = "+e.getMessage());
                return null;
            }
        }
        Path filePath = uploadPath.resolve(uniqueFileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/"+uploadDir+"/"+uniqueFileName;
        } catch (IOException e) {
            System.out.println("Exception from image handle = "+e.getMessage());
            return null;
        }
    }
}
