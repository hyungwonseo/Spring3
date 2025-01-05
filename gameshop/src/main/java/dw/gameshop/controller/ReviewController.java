package dw.gameshop.controller;

import dw.gameshop.dto.ReviewDTO;
import dw.gameshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(
                reviewService.saveReview(reviewDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getReviewAll() {
        return new ResponseEntity<>(
                reviewService.getReviewAll(),
                HttpStatus.OK);
    }

    @GetMapping("/game/id/{gameId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByGameId(@PathVariable long gameId) {
        return new ResponseEntity<>(
                reviewService.getReviewsByGameId(gameId),
                HttpStatus.OK);
    }
}












