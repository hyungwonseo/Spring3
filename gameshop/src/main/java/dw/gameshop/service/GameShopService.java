package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameShopRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameShopService {
    GameShopRepository gameShopRepository;
    UserRepository userRepository;

    public GameShopService(GameShopRepository gameShopRepository, UserRepository userRepository) {
        this.gameShopRepository = gameShopRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameShopRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> gameOptional = gameShopRepository.findById(id);
        if(gameOptional.isPresent()) {
            return gameOptional.get();
        }else {
            throw new ResourceNotFoundException("해당 Game이 없습니다. ID : " + id);
        }
    }

    public Game updateGameById(long id, Game game) {
        Optional<Game> gameOptional = gameShopRepository.findById(id);
        if(gameOptional.isPresent()) {
            Game temp = gameOptional.get();
            temp.setTitle(game.getTitle());
            temp.setGenre(game.getGenre());
            temp.setPrice(game.getPrice());
            temp.setImage(game.getImage());
            temp.setText(game.getText());
            gameShopRepository.save(temp);
            return temp;
        }else {
            throw new ResourceNotFoundException("해당 Game이 없습니다. ID : " + id);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        return gameShopRepository.getGameWithMaxPrice();
    }

    //제일 비싼 게임 Top 3
    public List<Game> getGameWithMaxPriceTop3() {
        return gameShopRepository.getGameWithMaxPriceTop3()
                .stream().limit(3).collect(Collectors.toList());
    }
}












