package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

public class BoardTest {

    /* CP1 Normal Strong Equivalence Testing (comprueba entradas válidas)*/
    @Test
    @DisplayName("Debería inicializar la partida correctamente")
    void testGameInit() {
        Board board = new Board();

        // CP1.1 Verificar que los aliens están inicializados correctamente
        assertNotNull(board.getAliens());
        assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getAliens().size()); // 4 filas x 6 columnas = 24 aliens
;
        // CP1.2 Verifica el alien de la posicion 0 (primer alien i=0, j=0)
        Alien firstAlien = board.getAliens().get(0);
        assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getX());
        assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getY());

        // CP1.3 Verifica el alien de la posicion 23 (último alien i=3, j=5)
        Alien lastAlien = board.getAliens().get(23);
        assertEquals(Commons.ALIEN_INIT_Y + 18 * 5, lastAlien.getX());
        assertEquals(Commons.ALIEN_INIT_Y + 18 * 3, lastAlien.getY());

        // CP1.4 Verificar que el jugador está inicializado correctamente
        assertNotNull(board.getPlayer());
        assertTrue(board.getPlayer().isVisible());

        // CP1.5 Verificar que el disparo está inicializado correctamente
        assertNotNull(board.getShot());
    }
}