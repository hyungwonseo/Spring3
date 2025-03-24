package dw.gameshop.service;

import dw.gameshop.dto.ReviewDTO;
import dw.gameshop.enums.GameRating;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Review;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.ReviewRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    UserRepository userRepository;

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = new Review(
                0,
                gameRepository.findByTitle(reviewDTO.getGameTitle())
                    .orElseThrow(()->new ResourceNotFoundException("No Game Title")),
                userRepository.findById(reviewDTO.getUserName())
                    .orElseThrow(()->new ResourceNotFoundException("No Username")),
                GameRating.valueOf(reviewDTO.getReviewPoint()),
                reviewDTO.getReviewText(),
                LocalDateTime.now());
        return reviewRepository.save(review).toDto();
    }

    public List<ReviewDTO> getReviewAll() {
        return reviewRepository.findAll().stream()
                .map(Review::toDto).toList();
    }

    public List<ReviewDTO> getReviewsByGameId(long gameId) {
        return reviewRepository.getReviewsByGameId(gameId).stream()
                .map(Review::toDto).toList();
    }
}













