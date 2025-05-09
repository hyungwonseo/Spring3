package dw.gameshop.controller;

import dw.gameshop.dto.FileDTO;
import dw.gameshop.model.User;
import dw.gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileUploadController {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute FileDTO fileDTO) {
        try {
            // 현재 세션의 userName을 사용하여 세션별로 폴더 저장위치를 구별함
            User currentUser = userService.getCurrentUser();
            // 업로드 폴더확인, 없으면 생성
            Path uploadPath = Paths.get(uploadDir, currentUser.getUsername());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 동일파일이 존재하면 "파일이름_1.확장자" 방식으로 카운트를 증가시켜서 중복저장을 회피함
            System.out.println(fileDTO.getFiles().stream().map((f)-> f.getOriginalFilename()).collect(Collectors.joining(", ")));
            fileDTO.getFiles().stream().forEach((f) -> {
                String originalFileName = f.getOriginalFilename();
                String fileName = originalFileName;
                int counter = 1;
                while (Files.exists(uploadPath.resolve(fileName))) { // 파일 이름 중복 검사
                    int dotIndex = originalFileName.lastIndexOf(".");
                    String name = dotIndex == -1 ? originalFileName : originalFileName.substring(0, dotIndex);
                    String extension = dotIndex == -1 ? "" : originalFileName.substring(dotIndex);
                    fileName = name + "_" + counter + extension;
                    counter++;
                }
                // 파일이름을 저장위치와 조합
                // normalize()는 파일명이 상대경로(예, ../filename.txt)일 경우, ..를 제거함
                Path copyLocation = uploadPath.resolve(fileName).normalize();
                // 파일저장
                try {
                    Files.copy(f.getInputStream(), copyLocation);
                } catch (IOException e) {
                    e.printStackTrace(); // 혹은 로깅
                }
            });
            return new ResponseEntity<>(
                    "File uploaded successfully: ",
                    HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    "Failed to upload file",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // 현재 세션의 userName을 사용하여 세션별로 폴더 저장위치를 구별함
            User currentUser = userService.getCurrentUser();
            Path filePath = Paths.get(uploadDir, currentUser.getUsername()).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; fileName=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        try {
            // 현재 세션의 userName을 사용하여 세션별로 폴더 저장위치를 구별함
            User currentUser = userService.getCurrentUser();
            Path filePath = Paths.get(uploadDir, currentUser.getUsername()).resolve(fileName).normalize();
            File file = filePath.toFile();

            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    // 삭제 성공 시 HTTP 200 응답
                    return ResponseEntity.ok().build();
                } else {
                    // 파일이 존재하지만 삭제에 실패한 경우 (예: 권한 문제 등)
                    return ResponseEntity.internalServerError().build();
                }
            } else {
                // 파일이 존재하지 않거나 파일이 아닌 경우
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
