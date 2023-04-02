package nvt.doan.service;

import nvt.doan.entities.FileData;
import nvt.doan.entities.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
   String  uploadImageToFileSystem(MultipartFile file, FileData fileData) throws IOException;

    void deleteFile(Integer id);

    List<FileData> uploadImageForRoom(Room room, MultipartFile[] files) throws IOException;
}
