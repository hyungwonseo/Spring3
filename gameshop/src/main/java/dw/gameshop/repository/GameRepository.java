package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("select g from Game g where g.price = (select max(g.price) from Game g)")
    public Game getGameWithMaxPrice();

    @Query("select g from Game g order by g.price desc limit 3")
    public List<Game> getGameWithMaxPriceTop3();
}






