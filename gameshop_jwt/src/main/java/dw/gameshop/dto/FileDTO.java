package dw.gameshop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {
    private String title;
    private String content;
    private List<MultipartFile> files;
}
