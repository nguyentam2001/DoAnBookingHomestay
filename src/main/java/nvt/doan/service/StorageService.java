package nvt.doan.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    public String uploadImageToFileSystem(MultipartFile file) throws IOException;
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException;

}
