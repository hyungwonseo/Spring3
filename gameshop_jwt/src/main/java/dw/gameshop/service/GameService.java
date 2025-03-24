package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(long id) {
        return gameRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("해당 Game이 없습니다. ID : " + id));
    }

    public Game updateGameById(long id, Game game) {
        return gameRepository.findById(id)
                .map(g-> gameRepository.save(game))
                .orElseThrow(()->new ResourceNotFoundException("해당 Game이 없습니다. ID : " + id));
    }

    //제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        return gameRepository.getGameWithMaxPrice();
    }

    //제일 비싼 게임 Top 3
    public List<Game> getGameWithMaxPriceTop3() {
        return gameRepository.getGameWithMaxPriceTop3();
    }
}












