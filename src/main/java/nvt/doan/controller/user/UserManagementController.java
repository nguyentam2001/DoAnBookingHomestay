package nvt.doan.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nvt.doan.dto.RoomDTO;
import nvt.doan.entities.User;
import nvt.doan.service.UserService;
import nvt.doan.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static nvt.doan.utils.Constant.FOLDER_PATH;

@RestController
@RequestMapping("/api/v1/users/account")
public class UserManagementController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/saveUser",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getPagePaymentSuccess(@RequestPart("file[]") MultipartFile[] files,
                                        @RequestPart("applicant") String applicant) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ObjectMapper objectMapper = new ObjectMapper();
        User request = objectMapper.readValue(applicant,User.class);
        uploadImageToFileSystem(files[0]);
        User oldUser= (User) accountService.loadUserByUsername(auth.getName());
        oldUser.setFullName(request.getFullName());
        oldUser.setGender(request.getGender());
        oldUser.setEmail(request.getEmail());
        oldUser.setAddress(request.getAddress());
        oldUser.setUserImageUrl(files[0].getOriginalFilename());
        userService.save(oldUser);
        return ResponseEntity.noContent().build();
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+"/"+file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);
        return "file uploaded successfully : " + filePath;
    }
}
