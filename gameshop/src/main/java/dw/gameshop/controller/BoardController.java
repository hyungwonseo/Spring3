package dw.gameshop.controller;

import dw.gameshop.dto.BoardDTO;
import dw.gameshop.model.Board;
import dw.gameshop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return new ResponseEntity<>(
                boardService.getAllBoards(),
                HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<BoardDTO> saveBoard(@RequestBody Board board) {
        return new ResponseEntity<>(
                boardService.saveBoard(board),
                HttpStatus.CREATED);
    }

    @PostMapping("/board/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable long id) {
        return new ResponseEntity<>(
                boardService.deleteBoard(id),
                HttpStatus.OK);
    }
}
