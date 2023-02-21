package be.bnp.tictactoe.service;


import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import be.bnp.tictactoe.repository.GameRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    private final GameRepository repo = Mockito.mock(GameRepository.class);
    private GameService gameService;

    @BeforeEach
    void init(){
        gameService = new GameServiceImpl(repo);
    }

    @Test
    void save(){
        Game game = new Game();
        gameService.saveGame(game);
        verify(repo).save(game);
    }

    @Test
    void findWithIdAndTestingDefaultStatus(){

        when(repo.findById(any(Long.class))).thenAnswer(i -> {
            Long id = (Long)i.getArguments()[0];
            return Optional.of(new Game(id));
        });

        Game foundGame = gameService.findById(Long.valueOf("1"));

        Assertions.assertEquals(foundGame.getId(), Long.valueOf("1"));
        Assertions.assertEquals(foundGame.getStatus(), Status.WAITING);
    }
}
