package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTitle(String title);

    @Query("select g from Game g where g.price = (select max(g.price) from Game g)")
    Game getGameWithMaxPrice();

    @Query("select g from Game g order by g.price desc limit 3")
    List<Game> getGameWithMaxPriceTop3();
}






