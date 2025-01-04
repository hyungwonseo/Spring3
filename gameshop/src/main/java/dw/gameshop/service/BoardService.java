package dw.gameshop.service;

import dw.gameshop.dto.BoardDTO;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Board;
import dw.gameshop.repository.BoardRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .filter(Board::getIsActive)
                .map(Board::toDto)
                .toList();
    }

    public BoardDTO saveBoard(BoardDTO boardDTO) {
        return boardRepository.findById(boardDTO.getId())
                .map((board)->{
                    board.setModifiedDate(LocalDateTime.now());
                    return boardRepository.save(board).toDto();
                })
                .orElseGet(()-> {
                    Board board = new Board(
                            null,
                            boardDTO.getTitle(),
                            boardDTO.getContent(),
                            userRepository.findById(boardDTO.getAuthorName())
                                    .orElseThrow(()->new ResourceNotFoundException("No UserName")),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            true);
                    return boardRepository.save(board).toDto();
                });
    }

    public String deleteBoard(long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setIsActive(false);
                    boardRepository.save(board);
                    return "successfully deleted";
                })
                .orElseThrow(() -> new ResourceNotFoundException("게시판 글이 없습니다. ID : " + id));
    }
}
