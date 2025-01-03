package dw.gameshop.dto;

import dw.gameshop.model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
