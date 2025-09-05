package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    // Настройка игроков и регистрация перед каждым тестом
    @BeforeEach
    public void setUp() {
        game = new Game();
        Player player1 = new Player(1, "Player1", 10);
        Player player2 = new Player(2, "Player2", 20);
        Player player3 = new Player(3, "Player3", 10);

        game.register(player1);
        game.register(player2);
        game.register(player3);
    }

    @Test
    public void round_FirstPlayerWins_ShouldReturn1() {
        assertEquals(1, game.round("Player2", "Player1"));
    }

    @Test
    public void round_SecondPlayerWins_ShouldReturn2() {
        assertEquals(2, game.round("Player1", "Player2"));
    }

    @Test
    public void round_Draw_ShouldReturn0() {
        assertEquals(0, game.round("Player1", "Player3"));
    }

    @Test
    public void round_FirstPlayerNotRegistered_ShouldThrowException() {
        NotRegisteredException exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("UnknownPlayer", "Player1");
        });
        assertTrue(exception.getMessage().contains("UnknownPlayer"));
    }

    @Test
    public void round_SecondPlayerNotRegistered_ShouldThrowException() {
        NotRegisteredException exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Player1", "UnknownPlayer");
        });
        assertTrue(exception.getMessage().contains("UnknownPlayer"));
    }

    @Test
    public void testGetId() {
        Player player = new Player(5, "TestName", 10);
        assertEquals(5, player.getId());
    }
}
