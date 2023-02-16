package be.bnp.tictactoe.entity;

import be.bnp.tictactoe.enums.Player;
import be.bnp.tictactoe.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tic_tac_toe")
@Getter
@Setter
public class TicTacToe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    private Player currentPlayer;

    public TicTacToe(){
        this.status = Status.WAITING;
        this.currentPlayer = Player.X;
    }


}
