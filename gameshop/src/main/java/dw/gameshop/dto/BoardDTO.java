package dw.gameshop.dto;

import dw.gameshop.model.Board;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private UserDTO author;
    private LocalDateTime modifiedDate;

    public static BoardDTO toBoardDto(Board board) {
        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                UserDTO.toUserDto(board.getAuthor()),
                board.getModifiedDate()
        );
    }
}
