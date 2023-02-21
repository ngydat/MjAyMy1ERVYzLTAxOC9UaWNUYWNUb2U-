package be.bnp.tictactoe.service;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import be.bnp.tictactoe.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    @Override
    public Game findByStatus(Status status) {
        return gameRepository.findByStatus(status).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
}
