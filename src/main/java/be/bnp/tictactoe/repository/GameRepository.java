package be.bnp.tictactoe.repository;

import be.bnp.tictactoe.entity.Game;
import be.bnp.tictactoe.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    public Optional<Game> findByStatus(Status status);
}
