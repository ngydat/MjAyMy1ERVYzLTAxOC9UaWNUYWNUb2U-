package be.bnp.tictactoe.entity;

import be.bnp.tictactoe.enums.Player;
import be.bnp.tictactoe.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column
    private Player winner;

    @Transient
    private Player currentPlayer;

    @Transient
    private Player[][] board;

    public Game(){
        this.status = Status.WAITING;
        this.currentPlayer = Player.X;
        board = new Player[3][3];
    }

    public void validateMove(int row, int col){
        if (this.board[row][col] != null){
            throw new IllegalArgumentException("This space has already been played. Please put your X or O elsewhere");
        }
        this.board[row][col] = this.currentPlayer;
        this.rotate();
    }

    private void rotate() {
        this.currentPlayer = this.currentPlayer == Player.X ? Player.O: Player.X;
    }




}
