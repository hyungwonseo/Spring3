package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(
                gameService.getAllGames(),
                HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(
                gameService.getGameById(id),
                HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return new ResponseEntity<>(
                gameService.updateGameById(id, game),
                HttpStatus.OK);
    }

    @GetMapping("/maxprice")
    public ResponseEntity<Game> getGameWithMaxPrice() {
        return new ResponseEntity<>(
                gameService.getGameWithMaxPrice(),
                HttpStatus.OK);
    }

    @GetMapping("/maxpricetop3")
    public ResponseEntity<List<Game>> getGameWithMaxPriceTop3() {
        return new ResponseEntity<>(
                gameService.getGameWithMaxPriceTop3(),
                HttpStatus.OK);
    }
}





