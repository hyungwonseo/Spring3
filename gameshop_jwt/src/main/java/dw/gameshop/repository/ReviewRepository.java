package dw.gameshop.repository;

import dw.gameshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.game.id = :gameId")
    List<Review> getReviewsByGameId(long gameId);
}
