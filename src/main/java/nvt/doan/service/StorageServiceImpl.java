package nvt.doan.service;

import lombok.Setter;
import nvt.doan.entities.FileData;
import nvt.doan.repository.FileDataRepository;
import nvt.doan.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageServiceImpl implements  StorageService {
    @Autowired
    private FileDataRepository fileDataRepository;
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath= Constant.FOLDER_PATH +file.getOriginalFilename();
        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());
        file.transferTo(new File(filePath));
        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
