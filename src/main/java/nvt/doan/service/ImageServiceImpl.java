package nvt.doan.service;

import nvt.doan.entities.FileData;
import nvt.doan.entities.Room;
import nvt.doan.repository.FileDataRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    FileDataRepository fileDataRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public String uploadImageToFileSystem(MultipartFile file, FileData fileData) throws IOException {
        fileDataRepository.save(fileData);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fileData.getFilePath());
        Files.write(path, bytes);
        return "file uploaded successfully : " + fileData.getFilePath();
    }

    @Override
    public void deleteFile(Integer id) {
       Optional<FileData> fileData =fileDataRepository.findById(id);
       fileDataRepository.delete(fileData.get());
    }

    @Override
    public List<FileData> uploadImageForRoom(Room room, MultipartFile[] files) throws IOException {
        // Make sure directory exists!
        File uploadDir = new File(Constant.FOLDER_PATH);
        uploadDir.mkdirs();
        List<FileData> filesData = new ArrayList<>();
        for (MultipartFile file : files) {
            String filePath= Constant.FOLDER_PATH +"/"+file.getOriginalFilename();
            FileData fileData=FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .room(room)
                    .filePath(filePath).build();
            filesData.add(fileData);
            uploadImageToFileSystem(file,fileData);
        }
        return filesData;
    }
}
